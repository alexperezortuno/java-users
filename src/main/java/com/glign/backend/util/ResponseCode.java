package com.glign.backend.util;

import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.Getter;

@Getter
public enum ResponseCode {
    USER_DELETED(1, "user deleted"),
    EMAIL_REQUIRED(-1, "email required"),
    EMAIL_EXIST(-2, "email already exists"),
    PASSWORD_REQUIRED(-3, "password required"),
    INVALID_PASSWORD(-4, "invalid password"),
    INTERNAL_SERVER_ERROR(-5, "internal server error"),
    AUTHENTICATION_FAILED(-6, "authentication failed"),
    INVALID_TOKEN(-7, "invalid token"),
    USER_NOT_FOUND(-8, "user not found");

    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
