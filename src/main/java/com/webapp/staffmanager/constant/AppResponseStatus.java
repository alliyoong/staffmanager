package com.webapp.staffmanager.constant;

public enum AppResponseStatus {
    APP_200("APP-200", "OK"),
    APP_201("APP-201", "CREATED"),
    APP_204("APP-204", "NO CONTENT"),
    APP_500("APP-500", "INTERNAL SERVER ERROR"),
    APP_400("APP-400", "BAD REQUEST"),
    APP_404_STAFF("APP-404-STAFF", "THIS STAFF DOESN'T EXIST"),
    APP_404_DEPT("APP-404-DEPT", "THIS DEPARTMENT DOESN'T EXIST"),
    APP_400_DEPT("APP-400-DEPT", "CAN'T DELETE DEPARTMENT WITH STAFF"),
    APP_404_STAFF_LIST("APP-404-STAFF-LIST", "STAFF LIST IS EMPTY"),
    APP_404_DEPT_LIST("APP-404-DEPT-LIST", "DEPT LIST IS EMPTY");

    String code;
    String message;
    
    AppResponseStatus(String code, String message){
        this.code = code;
        this.message = message;
    }

    public String getCode(){
        return code;
    }
    public String getMessage(){
        return message;
    }
}
