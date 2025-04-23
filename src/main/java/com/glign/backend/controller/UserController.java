package com.glign.backend.controller;

import com.glign.backend.dto.UserCreateRequestDto;
import com.glign.backend.dto.UserUpdateRequestDto;
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

    /**
     * Create a new user
     *
     * @param request UserCreateRequestDto
     * @return ResponseEntity<UserResponseDto>
     */
    @PostMapping("")
    public ResponseEntity<?> getUser(@RequestBody UserCreateRequestDto request) throws ApiException {
        var responseMessage = userService.createUser(request);
        return UserHttpResponseBuilder.buildResponse(responseMessage);
    }

    /**
     * Get user by UUID
     *
     * @param id User id
     * @return ResponseEntity<UserResponseDto>
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) throws ApiException {
        var responseMessage = userService.getUserById(id);
        return UserHttpResponseBuilder.buildResponse(responseMessage);
    }

    /**
     * Remove a user by UUID
     *
     * @param id User id
     * @return ResponseEntity<UserResponseDto>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeUserById(@PathVariable String id) throws ApiException {
        var responseMessage = userService.removeUserById(id);
        return UserHttpResponseBuilder.buildResponse(responseMessage);
    }

    /**
     * Update a user
     *
     * @param request UserCreateRequestDto
     * @return ResponseEntity<UserResponseDto>
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UserUpdateRequestDto request) throws ApiException {
        var responseMessage = userService.updateUser(id, request);
        return UserHttpResponseBuilder.buildResponse(responseMessage);
    }
}
