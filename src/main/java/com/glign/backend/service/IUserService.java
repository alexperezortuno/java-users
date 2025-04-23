package com.glign.backend.service;

import com.glign.backend.dto.*;
import com.glign.backend.exception.ApiException;
import com.glign.backend.util.ResponseMessage;

public interface IUserService {
    ResponseMessage<UserResponseDto> createUser(UserCreateRequestDto userCreateRequestDto) throws ApiException;
    ResponseMessage<UserResponseDto> getUserById(String id) throws ApiException;
    ResponseMessage<SimpleResponse> removeUserById(String id) throws ApiException;
    ResponseMessage<UserUpdateResponseDto> updateUser(String id, UserUpdateRequestDto userCreateRequestDto) throws ApiException;
}
