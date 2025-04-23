package com.glign.backend.service.impl;

import com.glign.backend.dto.UserRequestDto;
import com.glign.backend.dto.UserResponseDto;
import com.glign.backend.exception.ApiException;
import com.glign.backend.jpa.entity.User;
import com.glign.backend.mapper.UserMapper;
import com.glign.backend.provider.JwtTokenProvider;
import com.glign.backend.repository.UserRepository;
import com.glign.backend.service.IUserService;
import com.glign.backend.util.ResponseCode;
import com.glign.backend.util.ResponseMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
    public ResponseMessage<UserResponseDto> createUser(UserRequestDto userRequestDto) throws ApiException {
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new ApiException(ResponseCode.EMAIL_EXIST.getMessage(), HttpStatus.BAD_REQUEST);
        }

        if (!userRequestDto.getEmail().matches(emailRegex)) {
            throw new ApiException(ResponseCode.EMAIL_REQUIRED.getMessage(), HttpStatus.BAD_REQUEST);
        }

        if (!userRequestDto.getPassword().matches(passwordRegex)) {
            throw new ApiException(ResponseCode.INVALID_PASSWORD.getMessage(), HttpStatus.BAD_REQUEST);
        }

        try {
            var user = UserMapper.INSTANCE.dtoToEntity(userRequestDto);
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
}
