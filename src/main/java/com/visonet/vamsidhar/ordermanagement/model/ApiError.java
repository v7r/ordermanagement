package com.visonet.vamsidhar.ordermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class ApiError {
    private Date timestamp = new Date();
    private String error;
    private String message;

    private String uri;

    public ApiError(ValidationException e) {
        this.timestamp = new Date();
        this.error = e.getMessage();
        this.message = e.getMsgKey();
    }
}


