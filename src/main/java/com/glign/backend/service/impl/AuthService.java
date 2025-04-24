package com.glign.backend.service.impl;

import com.glign.backend.component.JwtTokenProvider;
import com.glign.backend.dto.LoginRequestDto;
import com.glign.backend.dto.TokenDto;
import com.glign.backend.exception.ApiException;
import com.glign.backend.repository.UserRepository;
import com.glign.backend.service.IAuthService;
import com.glign.backend.service.ITokenService;
import com.glign.backend.util.ResponseCode;
import com.glign.backend.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private JwtTokenProvider tokenProvider;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private void setJwtUtil(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public ResponseMessage<?> login(LoginRequestDto request) throws ApiException {
        try {
            if (request.getUsername() == null || request.getPassword() == null) {
                throw new ApiException(ResponseCode.USERNAME_AND_PASS_REQUIRED.getMessage(), HttpStatus.BAD_REQUEST);
            }

            if (!userRepository.existsByEmail(request.getUsername())) {
                throw new ApiException(ResponseCode.USERNAME_OR_PASSWORD_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
            }

            var user = userRepository.findByEmail(request.getUsername());
            if (user == null) {
                throw new ApiException(ResponseCode.USERNAME_OR_PASSWORD_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
            }

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                throw new ApiException(ResponseCode.USERNAME_AND_PASS_REQUIRED.getMessage(), HttpStatus.UNAUTHORIZED);
            }

            var token = tokenProvider.generateToken(user);
            var response = new TokenDto(token);
            return new ResponseMessage<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new ApiException(ResponseCode.LOGIN_PROBLEM.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
