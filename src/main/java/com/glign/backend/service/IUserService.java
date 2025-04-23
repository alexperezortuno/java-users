package com.glign.backend.service;

import com.glign.backend.dto.*;
import com.glign.backend.exception.ApiException;
import com.glign.backend.util.ResponseMessage;

public interface IUserService {
    ResponseMessage<UserFullResDto> createUser(UserCreateRequestDto userCreateRequestDto) throws ApiException;
    ResponseMessage<UserFullResDto> getUserById(String id) throws ApiException;
    ResponseMessage<MessageResponse> removeUserById(String id) throws ApiException;
    ResponseMessage<UserUpdateResDto> updateUser(String id, UserUpdateReqDto userCreateRequestDto) throws ApiException;
}
