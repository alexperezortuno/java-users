package com.glign.backend.service.impl;

import com.glign.backend.dto.LoginRequestDto;
import com.glign.backend.exception.ApiException;
import com.glign.backend.service.IAuthService;
import com.glign.backend.util.ResponseMessage;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    @Override
    public ResponseMessage<?> login(LoginRequestDto request) throws ApiException {
        // Implementación del método de inicio de sesión
        return null;
    }

    @Override
    public ResponseMessage<?> register(String name, String email, String password, String confirmPassword) throws ApiException {
        // Implementación del método de registro
        return null;
    }

    @Override
    public ResponseMessage<?> forgotPassword(String email) throws ApiException {
        // Implementación del método de recuperación de contraseña
        return null;
    }

    @Override
    public ResponseMessage<?> resetPassword(String token, String password, String confirmPassword) throws ApiException {
        // Implementación del método de restablecimiento de contraseña
        return null;
    }

    @Override
    public ResponseMessage<?> changePassword(String token, String password, String confirmPassword) throws ApiException {
        // Implementación del método de cambio de contraseña
        return null;
    }

    @Override
    public ResponseMessage<?> verifyEmail(String token) throws ApiException {
        // Implementación del método de verificación de correo electrónico
        return null;
    }

    @Override
    public ResponseMessage<?> resendVerificationEmail(String email) throws ApiException {
        // Implementación del método para reenviar el correo electrónico de verificación
        return null;
    }
}
