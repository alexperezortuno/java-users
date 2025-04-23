package com.glign.backend.service;

import com.glign.backend.dto.UserRequestDto;
import com.glign.backend.dto.UserResponseDto;
import com.glign.backend.exception.ApiException;
import com.glign.backend.util.ResponseMessage;

public interface IUserService {
    ResponseMessage<UserResponseDto> createUser(UserRequestDto userRequestDto) throws ApiException;
}
