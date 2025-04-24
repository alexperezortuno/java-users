package com.glign.backend.service;

import com.glign.backend.dto.LoginRequestDto;
import com.glign.backend.dto.RegisterRequestDto;
import com.glign.backend.exception.ApiException;
import com.glign.backend.util.ResponseMessage;

public interface IAuthService {
    ResponseMessage<?> login(LoginRequestDto request) throws ApiException;
    ResponseMessage<?> register(RegisterRequestDto request) throws ApiException;
    ResponseMessage<?> refreshToken(String authHeader) throws ApiException;
    ResponseMessage<?> logout(String authHeader) throws ApiException;
    // TODO: Implement the following methods
    ResponseMessage<?> forgotPassword(String email) throws ApiException;
    ResponseMessage<?> resetPassword(String token, String password, String confirmPassword) throws ApiException;
    ResponseMessage<?> changePassword(String token, String password, String confirmPassword) throws ApiException;
    ResponseMessage<?> verifyEmail(String token) throws ApiException;
    ResponseMessage<?> resendVerificationEmail(String email) throws ApiException;
}
