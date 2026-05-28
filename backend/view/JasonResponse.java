package com.courierms.view;

public class JasonResponse {
    private boolean status;
    private String msg;
    private Object result;
    public JasonResponse(boolean status,String msg,Object result){
        this.status=status;
        this.msg=msg;
        this.result=result;
    }
    public static JasonResponse done(String msg,Object result){
        return new JasonResponse(true,msg,result);
    }
    public static JasonResponse failed(String msg){
        return new JasonResponse(false,msg,null);
    }

    public String toJson() {
        String json = "{";
        json += "\"status\":" + status + ",";
        json += "\"msg\":\"" + msg + "\",";
        json += "\"result\":\"" + result + "\"";
        json += "}";
        return json;
    }
    public boolean getStatus(){
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public Object getResult() {
        return result;
    }
}
