package com.swiftselect.payload.response;

import com.swiftselect.utils.DateUtils;

import java.time.LocalDateTime;

public class APIResponse<T> {
    private String message;
    private T data;
    private String responseTime;

    public APIResponse(String message, T data) {
        this.message = message;
        this.data = data;
        this.responseTime = DateUtils.dateToString(LocalDateTime.now());
    }

    public APIResponse(String message) {
        this.message = message;
//        this.data = null;
        this.responseTime = DateUtils.dateToString(LocalDateTime.now());
    }
}
