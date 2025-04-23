package com.glign.backend.service;

import com.glign.backend.dto.SimpleResponse;
import com.glign.backend.dto.UserCreateRequestDto;
import com.glign.backend.dto.UserResponseDto;
import com.glign.backend.dto.UserUpdateRequestDto;
import com.glign.backend.exception.ApiException;
import com.glign.backend.util.ResponseMessage;

public interface IUserService {
    ResponseMessage<UserResponseDto> createUser(UserCreateRequestDto userCreateRequestDto) throws ApiException;
    ResponseMessage<UserResponseDto> getUserById(String id) throws ApiException;
    ResponseMessage<SimpleResponse> removeUserById(String id) throws ApiException;
    ResponseMessage<UserResponseDto> updateUser(String id, UserUpdateRequestDto userCreateRequestDto) throws ApiException;
}
