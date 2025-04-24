package com.glign.backend.controller;

import com.glign.backend.dto.LoginRequestDto;
import com.glign.backend.dto.RegisterRequestDto;
import com.glign.backend.exception.ApiException;
import com.glign.backend.service.IAuthService;
import com.glign.backend.util.HttpResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private IAuthService authService;

    @Autowired
    private void setAuthService(IAuthService authService) {
        this.authService = authService;
    }

    /**
     * Login a user
     *
     * @param request UserLoginRequestDto
     * @return ResponseEntity<UserFullResDto>
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto request) throws ApiException {
        var responseMessage = authService.login(request);
        return HttpResponseBuilder.buildResponse(responseMessage);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDto request) throws ApiException {
        var responseMessage = authService.register(request);
        return HttpResponseBuilder.buildResponse(responseMessage);
    }

    /**
     * Logout a user
     *
     * @param authHeader UserLogoutRequestDto
     * @return ResponseEntity<UserFullResDto>
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authHeader) throws ApiException {
        var responseMessage = authService.logout(authHeader);
        return HttpResponseBuilder.buildResponse(responseMessage);
    }

    /**
     * Refresh token
     *
     * @param authHeader UserLogoutRequestDto
     * @return ResponseEntity<UserFullResDto>
     */
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String authHeader) throws ApiException {
        var responseMessage = authService.refreshToken(authHeader);
        return HttpResponseBuilder.buildResponse(responseMessage);
    }
}
