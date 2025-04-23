package com.glign.backend.controller;

import com.glign.backend.dto.UserRequestDto;
import com.glign.backend.exception.ApiException;
import com.glign.backend.service.IUserService;
import com.glign.backend.util.UserHttpResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private IUserService userService;

    @Autowired
    private void setUserService(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<?> getUser(@RequestBody UserRequestDto request) throws ApiException {
        var responseMessage = userService.createUser(request);
        return UserHttpResponseBuilder.buildResponse(responseMessage);
    }
}
