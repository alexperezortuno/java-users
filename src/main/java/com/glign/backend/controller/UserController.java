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
     * @param id User id
     * @return ResponseEntity<UserFullResDto>
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) throws ApiException {
        var responseMessage = userService.getUserById(id);
        return HttpResponseBuilder.buildResponse(responseMessage);
    }

    /**
     * Remove a user by UUID
     *
     * @param id User id
     * @return ResponseEntity<UserFullResDto>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeUserById(@PathVariable String id) throws ApiException {
        var responseMessage = userService.removeUserById(id);
        return HttpResponseBuilder.buildResponse(responseMessage);
    }

    /**
     * Update a user partially
     *
     * @param request UserCreateRequestDto
     * @return ResponseEntity<UserFullResDto>
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @Valid @RequestBody UserUpdateReqDto request) throws ApiException {
        var responseMessage = userService.updateUser(id, request);
        return HttpResponseBuilder.buildResponse(responseMessage);
    }

    /**
     * Update a user completely
     *
     * @param id User id
     *
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserCompletely(@PathVariable String id, @Valid @RequestBody UserUpdateReqDto request) throws ApiException {
        var responseMessage = userService.updateUser(id, request);
        return HttpResponseBuilder.buildResponse(responseMessage);
    }

    /**
     * Get all users
     *
     * @return ResponseEntity<UserFullResDto>
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() throws ApiException {
        var responseMessage = userService.getAll();
        return HttpResponseBuilder.buildResponse(responseMessage);
    }
}
