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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class UserService implements IUserService {
    private UserRepository userRepository;
    private JwtTokenProvider tokenProvider;
    private BCryptPasswordEncoder passwordEncoder;

    @Value("${regexes.password}")
    private String passwordRegex;

    @Value("${regexes.email}")
    private String emailRegex;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setTokenProvider(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Autowired
    private void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseMessage<UserFullResDto> createUser(UserCreateRequestDto userCreateRequestDto) throws ApiException {
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
            user.setPassword(passwordEncoder.encode(userCreateRequestDto.getPassword()));

            User savedUser = userRepository.save(user);
            var response = UserMapper.INSTANCE.reduceForCreateUderDto(savedUser);
            response.setToken(token);
            return new ResponseMessage<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating user: {}", e.getMessage());
            throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseMessage<UserFullResDto> getUserById(String authHeader) throws ApiException {
        var id = this.getId(authHeader);

        try {
            var user = userRepository.findByUuid(UUID.fromString(id));
            if (user == null) {
                throw new ApiException(ResponseCode.USER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
            }
            var response = UserMapper.INSTANCE.reduceForGetUser(user);
            return new ResponseMessage<>(response, HttpStatus.OK);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error getting user: {}", e.getMessage());
            throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseMessage<MessageResponse> removeUserById(String authHeader) throws ApiException {
        var id = this.getId(authHeader);

        try {
            var user = userRepository.findByUuid(UUID.fromString(id));
            if (user == null) {
                throw new ApiException(ResponseCode.USER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
            }
            userRepository.delete(user);
            return new ResponseMessage<>(new MessageResponse(ResponseCode.USER_DELETED.getMessage()), HttpStatus.OK);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            log.error("error deleting user: {}", e.getMessage());
            throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseMessage<UserUpdateResDto> updateUser(String authHeader, UserUpdateReqDto userCreateRequestDto) throws ApiException {
        var id = this.getId(authHeader);

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
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error updating user: {}", e.getMessage());
            throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseMessage<List<UserGetResDto>> getAll(String authHeader) throws ApiException {
        this.getId(authHeader);

        try {
            var users = userRepository.findAll();
            if (users.isEmpty()) {
                throw new ApiException(ResponseCode.USER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
            }
            var response = UserMapper.INSTANCE.toUserGetResDtoList(users);
            return new ResponseMessage<>(response, HttpStatus.OK);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error users: {}", e.getMessage());
            throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String getId(String authHeader) throws ApiException {
        try {
            return tokenProvider.getUserIdFromToken(authHeader);
        } catch (Exception e) {
            throw new ApiException(ResponseCode.LOGIN_PROBLEM.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
