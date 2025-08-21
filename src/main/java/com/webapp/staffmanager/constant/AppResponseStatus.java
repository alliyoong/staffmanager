package com.webapp.staffmanager.constant;

public enum AppResponseStatus {
    APP_200("APP-200", "OK"),
    APP_201("APP-201", "CREATED"),
    APP_204("APP-204", "NO CONTENT"),
    APP_500("APP-500", "INTERNAL SERVER ERROR"),
    APP_400("APP-400", "BAD REQUEST"),
    APP_400_CHECKIN("APP-400-CHECKIN", "YOU CAN'T CHECK IN NOW"),
    APP_400_CHECKOUT("APP-400-CHECKOUT", "YOU CAN'T CHECK OUT NOW"),

    APP_404_STAFF("APP-404-STAFF", "THIS STAFF DOESN'T EXIST"),
    APP_404_DEPT("APP-404-DEPT", "THIS DEPARTMENT DOESN'T EXIST"),
    APP_400_DEPT("APP-400-DEPT", "CAN'T DELETE DEPARTMENT WITH STAFF"),
    APP_404_STAFF_LIST("APP-404-STAFF-LIST", "STAFF LIST IS EMPTY"),
    APP_404_DEPT_LIST("APP-404-DEPT-LIST", "DEPT LIST IS EMPTY"),
    APP_404_URL("APP-404-URL", "PAGE NOT FOUND"),

    APP_404_ATTENDANCE("APP-404-ATTENDANCE", "THIS ATTENDANCE RECORD DOESN'T EXIST"),
    APP_404_ACCOUNT("APP-404-ACCOUNT", "THIS ACCOUNT DOESN'T EXIST");

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
