package com.glign.backend.controller;

import com.glign.backend.dto.LoginRequestDto;
import com.glign.backend.exception.ApiException;
import com.glign.backend.service.IAuthService;
import com.glign.backend.util.HttpResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
