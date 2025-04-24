package com.glign.backend.util;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseCode {
    USER_DELETED(HttpStatus.OK.value(), Constant.USER_DELETED),
    USER_CREATED(HttpStatus.CREATED.value(), Constant.USER_CREATED),
    EMAIL_REQUIRED(HttpStatus.BAD_REQUEST.value(), Constant.EMAIL_REQUIRED),
    EMAIL_EXIST(HttpStatus.CONFLICT.value(), Constant.EMAIL_ALREADY_EXIST),
    PASSWORD_REQUIRED(HttpStatus.BAD_REQUEST.value(), Constant.PASSWORD_REQUIRED),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST.value(), Constant.INVALID_PASSWORD),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), Constant.INTERNAL_SERVER_ERROR),
    AUTHENTICATION_FAILED(HttpStatus.PRECONDITION_FAILED.value(), Constant.AUTHENTICATION_FAILED),
    INVALID_TOKEN(HttpStatus.BAD_REQUEST.value(), Constant.INVALID_TOKEN),
    USER_NOT_FOUND(HttpStatus.NO_CONTENT.value(), Constant.USER_NOT_FOUND),
    PHONE_REQUIRED(HttpStatus.BAD_REQUEST.value(), Constant.PHONE_REQUIRED),
    PHONE_UPDATED(HttpStatus.OK.value(), Constant.PHONE_UPDATED),
    PHONE_ADDED(HttpStatus.OK.value(), Constant.PHONE_ADDED),
    NUMBER_REQUIRED(HttpStatus.BAD_REQUEST.value(), Constant.NUMBER_REQUIRED),
    PHONE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), Constant.PHONE_NOT_FOUND),
    PHONE_DELETED(HttpStatus.OK.value(), Constant.PHONE_DELETED),
    PHONE_EMPTY(HttpStatus.NO_CONTENT.value(), Constant.PHONE_EMPTY),
    LOGIN_PROBLEM(HttpStatus.INTERNAL_SERVER_ERROR.value(), Constant.LOGIN_PROBLEM),
    USERNAME_AND_PASS_REQUIRED(HttpStatus.BAD_REQUEST.value(), Constant.USERNAME_AND_PASS_REQUIRED),
    USERNAME_OR_PASSWORD_NOT_FOUND(HttpStatus.PRECONDITION_FAILED.value(), Constant.USERNAME_OR_PASSWORD_NOT_FOUND),
    REGISTRATION_PROBLEM(HttpStatus.PRECONDITION_FAILED.value(), Constant.REGISTRATION_PROBLEM);

    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
