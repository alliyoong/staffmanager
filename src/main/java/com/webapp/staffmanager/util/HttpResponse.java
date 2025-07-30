package com.webapp.staffmanager.util;
import java.time.LocalDateTime;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.webapp.staffmanager.constant.AppResponseStatus;

import lombok.Getter;
import lombok.Setter;

import static com.webapp.staffmanager.constant.AppResponseStatus.*;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter @Setter
public class HttpResponse<T> {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
    private LocalDateTime timeStamp;
    private String statusCode;
    private String statusMessage;
    private T data;

    private HttpResponse(AppResponseStatus status, T data) {
        this.timeStamp = LocalDateTime.now();
        this.statusCode = status.getCode();
        this.statusMessage = status.getMessage();
        this.data = data;
    }
    private HttpResponse(AppResponseStatus status) {
        this.timeStamp = LocalDateTime.now();
        this.statusCode = status.getCode();
        this.statusMessage = status.getMessage();
    }
    private HttpResponse(String code, String message) {
        this.timeStamp = LocalDateTime.now();
        this.statusCode = code;
        this.statusMessage = message;
    }

    private HttpResponse(){}

    public static <T> HttpResponse ok(T data) {
        return new HttpResponse(APP_200,  data);
    }
    public static <T> HttpResponse created() {
        return new HttpResponse(APP_201);
    }
    public static <T> HttpResponse noContent() {
        return new HttpResponse(APP_204);
    }
    public static HttpResponse error(AppResponseStatus status) {
        return new HttpResponse(status);
    }
    public static HttpResponse error(int code, String message) {
        return new HttpResponse(String.valueOf(code), message);
    }
}
