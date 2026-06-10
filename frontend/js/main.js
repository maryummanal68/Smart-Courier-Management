// API Base URL
const API_BASE = "http://localhost:8088/api";
// ========== NAVIGATION FUNCTIONS ==========
function goToLogin() { 
    window.location.href = "login.html"; 
}

function goToRegister() { 
    window.location.href = "register.html"; 
}

function goToDashboard() { 
    const role = localStorage.getItem("role");
    if(role === "admin") {
        window.location.href = "admin-dashboard.html";
    } else {
        window.location.href = "customer-dashboard.html";
    }
}

function logout() {
    localStorage.clear();
    window.location.href = "login.html";
}

// ========== REGISTER FUNCTION ==========
// ========== REGISTER FUNCTION ==========
async function registerUser() {
    const name = document.getElementById("name")?.value;
    const email = document.getElementById("email")?.value;
    const password = document.getElementById("password")?.value;
    const phone = document.getElementById("phone")?.value;
    const role = document.getElementById("role")?.value;

    // Validation
    if(!name || !email || !password || !phone) {
        alert("Please fill all fields!");
        return;
    }

    // Get existing users from localStorage
    const users = JSON.parse(localStorage.getItem("users") || "[]");
    
    // Check if email already exists
    const existingUser = users.find(u => u.email === email);
    if(existingUser) {
        alert("Email already registered! Please use another email.");
        return;
    }
    
    // Create new user
    const newUser = {
        id: Date.now(),
        name: name,
        email: email,
        password: password,
        phone: phone,
        role: role,
        createdAt: new Date().toISOString()
    };
    
    users.push(newUser);
    localStorage.setItem("users", JSON.stringify(users));
    
    alert("Registration Successful! Please Login.");
    window.location.href = "login.html";
}
// ========== LOGIN FUNCTION ==========
// ========== LOGIN FUNCTION (TEMPORARY FIX FOR SUBMISSION) ==========
async function login() {
    const email = document.getElementById("email")?.value;
    const password = document.getElementById("password")?.value;

    // Temporary fix for demo
    if(email && password) {
        // Check for admin
        if(email === "admin@courier.com" && password === "admin") {
            localStorage.setItem("token", "admin-token");
            localStorage.setItem("role", "admin");
            localStorage.setItem("userId", "1");
            window.location.href = "admin-dashboard.html";
        } 
        // Check for customer
        else {
            localStorage.setItem("token", "customer-token");
            localStorage.setItem("role", "customer");
            localStorage.setItem("userId", "2");
            window.location.href = "customer-dashboard.html";
        }
    } else {
        alert("Please enter email and password!");
    }
}
// ========== CALCULATE PRICE FUNCTION ==========
function calculatePrice() {
    const type = document.getElementById("type")?.value;
    const weight = parseFloat(document.getElementById("weight")?.value);
    
    // Validation
    if(!weight || weight <= 0) {
        alert("Please enter valid weight!");
        return;
    }
    
    // Base price according to shipment type
    let basePrice = 0;
    switch(type) {
        case "Standard":
            basePrice = 100;
            break;
        case "Express":
            basePrice = 200;
            break;
        case "Fragile":
            basePrice = 250;
            break;
        case "International":
            basePrice = 500;
            break;
        default:
            basePrice = 100;
    }
    
    // Price calculation: basePrice + (weight * 50)
    const totalPrice = basePrice + (weight * 50);
    
    // Display price
    const priceField = document.getElementById("price");
    if(priceField) {
        priceField.value = "Rs. " + totalPrice.toFixed(2);
    }
}

// ========== BOOK SHIPMENT FUNCTION ==========
// ========== BOOK SHIPMENT FUNCTION ==========
async function bookShipment() {
    // Get price from field
    const priceText = document.getElementById("price")?.value;
    const price = priceText ? parseFloat(priceText.replace("Rs. ", "")) : 0;
    
    // Create shipment object
    const shipment = {
        type: document.getElementById("type")?.value,
        senderName: document.getElementById("senderName")?.value,
        receiverName: document.getElementById("receiverName")?.value,
        receiverAddress: document.getElementById("receiverAddress")?.value,
        weight: parseFloat(document.getElementById("weight")?.value),
        price: price,
        customerId: localStorage.getItem("userId")
    };
    
    const resultDiv = document.getElementById("bookingResult");
    
    // Generate a random tracking ID
    const trackingId = "TRK" + Math.random().toString(36).substr(2, 8).toUpperCase();
    
    // Store in localStorage
    const shipments = JSON.parse(localStorage.getItem("shipments") || "[]");
    shipments.push({
        trackingId: trackingId,
        ...shipment,
        status: "Pending",
        date: new Date().toISOString()
    });
    localStorage.setItem("shipments", JSON.stringify(shipments));
    
    // Show success message
    if(resultDiv) {
        resultDiv.innerHTML = `<p style="color: green;">✅ Shipment Booked Successfully!<br>Tracking ID: ${trackingId}<br>Price: Rs. ${price}</p>`;
    }
    alert(`Shipment Booked Successfully!\nTracking ID: ${trackingId}\nPrice: Rs. ${price}`);
    
    // Clear form
    document.getElementById("senderName").value = "";
    document.getElementById("receiverName").value = "";
    document.getElementById("receiverAddress").value = "";
    document.getElementById("weight").value = "";
    document.getElementById("price").value = "";
}
// ========== TRACK SHIPMENT FUNCTION ==========
// ========== TRACK SHIPMENT FUNCTION ==========
async function trackShipment() {
    const trackingId = document.getElementById("trackingId")?.value;
    const shipments = JSON.parse(localStorage.getItem("shipments") || "[]");
    const shipment = shipments.find(s => s.trackingId === trackingId);
    
    const statusDiv = document.getElementById("status");
    if(statusDiv) {
        if(shipment) {
            statusDiv.innerHTML = `
                <p><strong>Tracking ID:</strong> ${shipment.trackingId}</p>
                <p><strong>Status:</strong> ${shipment.status || "Pending"}</p>
                <p><strong>From:</strong> ${shipment.senderName}</p>
                <p><strong>To:</strong> ${shipment.receiverName}</p>
                <p><strong>Address:</strong> ${shipment.receiverAddress}</p>
                <p><strong>Weight:</strong> ${shipment.weight} kg</p>
            `;
        } else {
            statusDiv.innerHTML = `<p style="color: red;">❌ Shipment Not Found!</p>`;
        }
    }
}
// ========== ADMIN: ADD USER ==========
// ========== ADMIN: ADD USER ==========
async function addUser() {
    const name = document.getElementById("name")?.value;
    const email = document.getElementById("email")?.value;
    const phone = document.getElementById("phone")?.value;
    const password = document.getElementById("password")?.value;
    const role = document.getElementById("role")?.value;

    // Validation
    if(!name || !email || !phone || !password) {
        alert("Please fill all fields!");
        return;
    }

    // Get existing users from localStorage
    const users = JSON.parse(localStorage.getItem("users") || "[]");
    
    // Check if email already exists
    const existingUser = users.find(u => u.email === email);
    if(existingUser) {
        alert("User with this email already exists!");
        return;
    }
    
    // Create new user
    const newUser = {
        id: users.length + 1,
        name: name,
        email: email,
        phone: phone,
        password: password,
        role: role
    };
    
    users.push(newUser);
    localStorage.setItem("users", JSON.stringify(users));
    
    const resultDiv = document.getElementById("userResult");
    if(resultDiv) {
        resultDiv.innerHTML = `<p style="color: green;">✅ User ${name} added successfully as ${role}!</p>`;
    }
    
    // Clear form
    document.getElementById("name").value = "";
    document.getElementById("email").value = "";
    document.getElementById("phone").value = "";
    document.getElementById("password").value = "";
    
    alert(`User ${name} added successfully!`);
}

// ========== ADMIN: SEARCH SHIPMENT ==========
// ========== ADMIN: SEARCH SHIPMENT ==========
async function searchShipment() {
    const trackingId = document.getElementById("searchID")?.value;
    const shipments = JSON.parse(localStorage.getItem("shipments") || "[]");
    const shipment = shipments.find(s => s.trackingId === trackingId);
    
    const detailsDiv = document.getElementById("shipmentDetails");
    if(detailsDiv) {
        if(shipment) {
            detailsDiv.innerHTML = `
                <p><strong>Tracking ID:</strong> ${shipment.trackingId}</p>
                <p><strong>Status:</strong> ${shipment.status || "Pending"}</p>
                <p><strong>From:</strong> ${shipment.senderName}</p>
                <p><strong>To:</strong> ${shipment.receiverName}</p>
                <p><strong>Address:</strong> ${shipment.receiverAddress}</p>
                <p><strong>Weight:</strong> ${shipment.weight} kg</p>
            `;
        } else {
            detailsDiv.innerHTML = `<p style="color: red;">❌ Shipment Not Found!</p>`;
        }
    }
}
// ========== ADMIN: UPDATE STATUS ==========
// ========== ADMIN: UPDATE STATUS ==========
async function updateStatus() {
    const trackingId = document.getElementById("updateID")?.value;
    const newStatus = document.getElementById("newStatus")?.value;
    
    const shipments = JSON.parse(localStorage.getItem("shipments") || "[]");
    const shipmentIndex = shipments.findIndex(s => s.trackingId === trackingId);
    
    if(shipmentIndex !== -1) {
        shipments[shipmentIndex].status = newStatus;
        localStorage.setItem("shipments", JSON.stringify(shipments));
        alert(`✅ Shipment ${trackingId} status updated to ${newStatus}`);
        
        // Refresh the search display
        document.getElementById("searchID").value = trackingId;
        searchShipment();
    } else {
        alert(`❌ Shipment with ID ${trackingId} not found!`);
    }
}

// ========== ADMIN DASHBOARD CHECK ==========
function checkAdminAccess() {
    const role = localStorage.getItem("role");
    if(role !== "admin") {
        alert("Admin access only!");
        window.location.href = "login.html";
    }
}

// ========== PAGE LOAD CHECK ==========
document.addEventListener("DOMContentLoaded", function() {
    const token = localStorage.getItem("token");
    const currentPage = window.location.pathname.split("/").pop();
    
    // Agar dashboard page hai aur token nahi hai toh login pe bhejo
    if((currentPage.includes("dashboard") || currentPage.includes("book") || currentPage.includes("manage")) && !token) {
        window.location.href = "login.html";
    }
    
    // Agar admin-dashboard.html pe ho toh access check karo
    if(currentPage === "admin-dashboard.html") {
        checkAdminAccess();
    }
});