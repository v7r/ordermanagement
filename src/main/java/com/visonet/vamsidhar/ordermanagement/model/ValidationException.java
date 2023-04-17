package com.visonet.vamsidhar.ordermanagement.model;

import lombok.Data;

@Data
public class ValidationException extends RuntimeException {

    private String msgKey;
    public ValidationException(String error, String msgKey) {
        super(error);
        this.msgKey = msgKey;
    }

    public ValidationException(String error, String msgKey, Throwable cause) {
        super(error, cause);
        this.msgKey = msgKey;
    }
}
