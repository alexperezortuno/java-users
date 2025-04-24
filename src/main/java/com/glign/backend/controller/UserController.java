package com.glign.backend.controller;

import com.glign.backend.dto.UserCreateRequestDto;
import com.glign.backend.dto.UserUpdateReqDto;
import com.glign.backend.exception.ApiException;
import com.glign.backend.service.IUserService;
import com.glign.backend.util.HttpResponseBuilder;
import jakarta.validation.Valid;
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
     * @return ResponseEntity<UserFullResDto>
     */
    @PostMapping("")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateRequestDto request) throws ApiException {
        var responseMessage = userService.createUser(request);
        return HttpResponseBuilder.buildResponse(responseMessage);
    }

    /**
     * Get user by UUID
     *
     * @param authHeader Authorization id
     * @return ResponseEntity<UserFullResDto>
     */
    @GetMapping("")
    public ResponseEntity<?> getUserById(@RequestHeader("Authorization") String authHeader) throws ApiException {
        var responseMessage = userService.getUserById(authHeader);
        return HttpResponseBuilder.buildResponse(responseMessage);
    }

    /**
     * Remove a user by UUID
     *
     * @param authHeader Authorization id
     * @return ResponseEntity<UserFullResDto>
     */
    @DeleteMapping("")
    public ResponseEntity<?> removeUserById(@RequestHeader("Authorization") String authHeader) throws ApiException {
        var responseMessage = userService.removeUserById(authHeader);
        return HttpResponseBuilder.buildResponse(responseMessage);
    }

    /**
     * Update a user partially
     *
     * @param request UserCreateRequestDto
     * @return ResponseEntity<UserFullResDto>
     */
    @PatchMapping("")
    public ResponseEntity<?> updateUser(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody UserUpdateReqDto request) throws ApiException {
        var responseMessage = userService.updateUser(authHeader, request);
        return HttpResponseBuilder.buildResponse(responseMessage);
    }

    /**
     * Update a user completely
     *
     * @param authHeader Authorization id
     *
     */
    @PutMapping("")
    public ResponseEntity<?> updateUserCompletely(@RequestHeader("Authorization") String authHeader, @Valid @RequestBody UserUpdateReqDto request) throws ApiException {
        var responseMessage = userService.updateUser(authHeader, request);
        return HttpResponseBuilder.buildResponse(responseMessage);
    }

    /**
     * Get all users
     *
     * @return ResponseEntity<UserFullResDto>
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers(@RequestHeader("Authorization") String authHeader) throws ApiException {
        var responseMessage = userService.getAll(authHeader);
        return HttpResponseBuilder.buildResponse(responseMessage);
    }
}
