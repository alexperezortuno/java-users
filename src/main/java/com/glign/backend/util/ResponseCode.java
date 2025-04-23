package com.glign.backend.util;

public enum ResponseCode {
    EMAIL_REQUIRED(-1, "email required"),
    EMAIL_EXIST(-2, "email already exists"),
    PASSWORD_REQUIRED(-3, "password required"),
    INVALID_PASSWORD(-4, "invalid password"),
    INTERNAL_SERVER_ERROR(-5, "internal server error");

    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
