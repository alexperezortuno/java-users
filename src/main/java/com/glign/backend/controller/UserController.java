package com.glign.backend.controller;

import com.glign.backend.dto.PhoneUpdateRequestDto;
import com.glign.backend.dto.UserCreateRequestDto;
import com.glign.backend.dto.UserUpdateReqDto;
import com.glign.backend.exception.ApiException;
import com.glign.backend.service.IUserService;
import com.glign.backend.util.UserHttpResponseBuilder;
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
     * @return ResponseEntity<UserResDto>
     */
    @PostMapping("")
    public ResponseEntity<?> getUser(@Valid @RequestBody UserCreateRequestDto request) throws ApiException {
        var responseMessage = userService.createUser(request);
        return UserHttpResponseBuilder.buildResponse(responseMessage);
    }

    /**
     * Get user by UUID
     *
     * @param id User id
     * @return ResponseEntity<UserResDto>
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
     * @return ResponseEntity<UserResDto>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeUserById(@PathVariable String id) throws ApiException {
        var responseMessage = userService.removeUserById(id);
        return UserHttpResponseBuilder.buildResponse(responseMessage);
    }

    /**
     * Update a user partially
     *
     * @param request UserCreateRequestDto
     * @return ResponseEntity<UserResDto>
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @Valid @RequestBody UserUpdateReqDto request) throws ApiException {
        var responseMessage = userService.updateUser(id, request);
        return UserHttpResponseBuilder.buildResponse(responseMessage);
    }

    /**
     * Update phones of a user
     *
     * @param id User id
     * @param request PhoneUpdateRequestDto
     * @return ResponseEntity>
     */
    @PatchMapping("/{id}/phones")
    public ResponseEntity<?> updatePhones(@PathVariable String id, @Valid @RequestBody PhoneUpdateRequestDto request) throws ApiException {
        var responseMessage = userService.updatePhones(id, request);
        return UserHttpResponseBuilder.buildResponse(responseMessage);
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
        return UserHttpResponseBuilder.buildResponse(responseMessage);
    }
}
