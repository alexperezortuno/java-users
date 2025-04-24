package com.glign.backend.service;

import com.glign.backend.dto.LoginRequestDto;
import com.glign.backend.exception.ApiException;
import com.glign.backend.util.ResponseMessage;

public interface IAuthService {
    ResponseMessage<?> login(LoginRequestDto request) throws ApiException;
    ResponseMessage<?> register(LoginRequestDto request) throws ApiException;
    ResponseMessage<?> forgotPassword(String email) throws ApiException;
    ResponseMessage<?> resetPassword(String token, String password, String confirmPassword) throws ApiException;
    ResponseMessage<?> changePassword(String token, String password, String confirmPassword) throws ApiException;
    ResponseMessage<?> verifyEmail(String token) throws ApiException;
    ResponseMessage<?> resendVerificationEmail(String email) throws ApiException;
}
