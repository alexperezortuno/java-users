package com.glign.backend.service;

import com.glign.backend.dto.*;
import com.glign.backend.exception.ApiException;
import com.glign.backend.util.ResponseMessage;

public interface IUserService {
    ResponseMessage<UserResDto> createUser(UserCreateRequestDto userCreateRequestDto) throws ApiException;
    ResponseMessage<UserResDto> getUserById(String id) throws ApiException;
    ResponseMessage<SimpleResponse> removeUserById(String id) throws ApiException;
    ResponseMessage<UserUpdateResDto> updateUser(String id, UserUpdateReqDto userCreateRequestDto) throws ApiException;
    ResponseMessage<?> updatePhones(String id, PhoneUpdateRequestDto request) throws ApiException;
}
