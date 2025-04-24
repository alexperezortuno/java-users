package com.glign.backend.util;

import lombok.Getter;

@Getter
public enum ResponseCode {
    USER_DELETED(1, Constant.USER_DELETED),
    EMAIL_REQUIRED(-1, Constant.EMAIL_REQUIRED),
    EMAIL_EXIST(-2, Constant.EMAIL_ALREADY_EXIST),
    PASSWORD_REQUIRED(-3, Constant.PASSWORD_REQUIRED),
    INVALID_PASSWORD(-4, Constant.INVALID_PASSWORD),
    INTERNAL_SERVER_ERROR(-5, Constant.INTERNAL_SERVER_ERROR),
    AUTHENTICATION_FAILED(-6, Constant.AUTHENTICATION_FAILED),
    INVALID_TOKEN(-7, Constant.INVALID_TOKEN),
    USER_NOT_FOUND(-8, Constant.USER_NOT_FOUND),
    PHONE_REQUIRED(-9, Constant.PHONE_REQUIRED),
    PHONE_UPDATED(-10, Constant.PHONE_UPDATED),
    PHONE_ADDED(-11, Constant.PHONE_ADDED),
    NUMBER_REQUIRED(-12, Constant.NUMBER_REQUIRED),
    PHONE_NOT_FOUND(-13, Constant.PHONE_NOT_FOUND),
    PHONE_DELETED(-14, Constant.PHONE_DELETED),
    PHONE_EMPTY(-15, Constant.PHONE_EMPTY),
    LOGIN_PROBLEM(-16, Constant.LOGIN_PROBLEM),
    USERNAME_AND_PASS_REQUIRED(-17, Constant.USERNAME_AND_PASS_REQUIRED),
    USERNAME_OR_PASSWORD_NOT_FOUND(-18, Constant.USERNAME_OR_PASSWORD_NOT_FOUND);

    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
