package com.glign.backend.service;

import com.glign.backend.dto.UserRequestDto;
import com.glign.backend.dto.UserResponseMessage;
import com.glign.backend.exception.ApiException;

public interface IUserService {
    UserResponseMessage createUser(UserRequestDto userRequestDto) throws ApiException;
}
