package com.glign.backend.service;

import com.glign.backend.dto.*;
import com.glign.backend.exception.ApiException;
import com.glign.backend.util.ResponseMessage;

import java.util.List;

public interface IUserService {
    ResponseMessage<UserFullResDto> createUser(UserCreateRequestDto userCreateRequestDto) throws ApiException;
    ResponseMessage<UserFullResDto> getUserById(String authHeader) throws ApiException;
    ResponseMessage<MessageResponse> removeUserById(String authHeader) throws ApiException;
    ResponseMessage<UserUpdateResDto> updateUser(String authHeader, UserUpdateReqDto userCreateRequestDto) throws ApiException;
    ResponseMessage<List<UserGetResDto>> getAll(String authHeader) throws ApiException;
}
