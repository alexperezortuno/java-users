package com.glign.backend.service.impl;

import com.glign.backend.dto.*;
import com.glign.backend.dto.UserUpdateResDto;
import com.glign.backend.exception.ApiException;
import com.glign.backend.jpa.entity.User;
import com.glign.backend.mapper.UserMapper;
import com.glign.backend.component.JwtTokenProvider;
import com.glign.backend.repository.UserRepository;
import com.glign.backend.service.IUserService;
import com.glign.backend.util.ResponseCode;
import com.glign.backend.util.ResponseMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Log4j2
public class UserService implements IUserService {
    private UserRepository userRepository;
    private JwtTokenProvider tokenProvider;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setTokenProvider(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Value("${regexes.password}")
    private String passwordRegex;

    @Value("${regexes.email}")
    private String emailRegex;

    @Override
    public ResponseMessage<UserResDto> createUser(UserCreateRequestDto userCreateRequestDto) throws ApiException {
        if (userRepository.existsByEmail(userCreateRequestDto.getEmail())) {
            throw new ApiException(ResponseCode.EMAIL_EXIST.getMessage(), HttpStatus.BAD_REQUEST);
        }

        if (!userCreateRequestDto.getEmail().matches(emailRegex)) {
            throw new ApiException(ResponseCode.EMAIL_REQUIRED.getMessage(), HttpStatus.BAD_REQUEST);
        }

        if (!userCreateRequestDto.getPassword().matches(passwordRegex)) {
            throw new ApiException(ResponseCode.INVALID_PASSWORD.getMessage(), HttpStatus.BAD_REQUEST);
        }

        if (userCreateRequestDto.getPhones() == null || userCreateRequestDto.getPhones().isEmpty()) {
            throw new ApiException(ResponseCode.PHONE_REQUIRED.getMessage(), HttpStatus.BAD_REQUEST);
        }

        try {
            var user = UserMapper.INSTANCE.dtoToEntity(userCreateRequestDto);
            user.setActive(true);
            user.getPhones().forEach(phone -> phone.setUser(user));

            String token = tokenProvider.generateToken(user);
            user.setToken(token);

            User savedUser = userRepository.save(user);
            var response = UserMapper.INSTANCE.reduceEntityToDto(savedUser);
            return new ResponseMessage<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage());
            throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseMessage<UserResDto> getUserById(String id) throws ApiException {
        try {
            var user = userRepository.findByUuid(UUID.fromString(id));
            if (user == null) {
                throw new ApiException(ResponseCode.USER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
            }
            var response = UserMapper.INSTANCE.reduceEntityToDto(user);
            return new ResponseMessage<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error getting user: {}", e.getMessage());
            throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseMessage<SimpleResponse> removeUserById(String id) throws ApiException {
        try {
            var user = userRepository.findByUuid(UUID.fromString(id));
            if (user == null) {
                throw new ApiException(ResponseCode.USER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
            }
            userRepository.delete(user);
            return new ResponseMessage<>(new SimpleResponse(ResponseCode.USER_DELETED.getMessage()), HttpStatus.OK);
        } catch (Exception e) {
            log.error("error deleting user: {}", e.getMessage());
            throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseMessage<UserUpdateResDto> updateUser(String id, UserUpdateReqDto userCreateRequestDto) throws ApiException {
        try {
            var user = userRepository.findByUuid(UUID.fromString(id));
            if (user == null) {
                throw new ApiException(ResponseCode.USER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
            }
            user.setName(userCreateRequestDto.getName());
            user.setActive(userCreateRequestDto.isActive());
            userRepository.save(user);
            var response = UserMapper.INSTANCE.reduceEntityToUpdateDto(user);
            return new ResponseMessage<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating user: {}", e.getMessage());
            throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseMessage<?> updatePhones(String id, PhoneUpdateRequestDto request) throws ApiException {
        try {
            var user = userRepository.findByUuid(UUID.fromString(id));
            if (user == null) {
                throw new ApiException(ResponseCode.USER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
            }
            user.setPhones(UserMapper.INSTANCE.toPhoneEntityList(request.getPhones()));
            userRepository.save(user);
            return new ResponseMessage<>(new SimpleResponse(ResponseCode.PHONE_UPDATED.getMessage()), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating phones: {}", e.getMessage());
            throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
