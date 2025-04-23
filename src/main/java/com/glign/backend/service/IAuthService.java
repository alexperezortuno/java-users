package com.glign.backend.service;

import com.glign.backend.dto.LoginRequestDto;
import com.glign.backend.util.ResponseMessage;

public interface IAuthService {
    ResponseMessage<?> login(LoginRequestDto request) throws Exception;
    ResponseMessage<?> register(String name, String email, String password, String confirmPassword) throws Exception;;
    ResponseMessage<?> forgotPassword(String email) throws Exception;;
    ResponseMessage<?> resetPassword(String token, String password, String confirmPassword) throws Exception;;
    ResponseMessage<?> changePassword(String token, String password, String confirmPassword) throws Exception;;
    ResponseMessage<?> verifyEmail(String token) throws Exception;;
    ResponseMessage<?> resendVerificationEmail(String email) throws Exception;;
}
