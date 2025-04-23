package com.glign.backend.service.impl;

import com.glign.backend.dto.UserRequestDto;
import com.glign.backend.dto.UserResponseMessage;
import com.glign.backend.exception.ApiException;
import com.glign.backend.jpa.entity.Phone;
import com.glign.backend.jpa.entity.User;
import com.glign.backend.mapper.UserMapper;
import com.glign.backend.provider.JwtTokenProvider;
import com.glign.backend.repository.UserRepository;
import com.glign.backend.service.IUserService;
import com.glign.backend.util.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
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

    @Value("${password.regex}")
    private String passwordRegex;

    @Override
    public UserResponseMessage createUser(UserRequestDto userRequestDto) throws ApiException {
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new ApiException(ResponseCode.EMAIL_EXIST.getMessage());
        }

        if (userRequestDto.getEmail() == null || userRequestDto.getEmail().isEmpty()) {
            throw new ApiException(ResponseCode.EMAIL_REQUIRED.getMessage());
        }

        if (!userRequestDto.getPassword().matches(passwordRegex)) {
            throw new ApiException(ResponseCode.INVALID_PASSWORD.getMessage());
        }

        var user = UserMapper.INSTANCE.dtoToEntity(userRequestDto);
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setActive(true);

        List<Phone> phones = userRequestDto.getPhones().stream()
                .map(phoneDto -> {
                    Phone phone = new Phone();
                    phone.setPhoneNumber(phoneDto.getNumber());
                    phone.setCityCode(phoneDto.getCityCode());
                    phone.setCountryCode(phoneDto.getCountryCode());
                    phone.setUser(user);
                    return phone;
                })
                .collect(Collectors.toList());

        user.setPhones(phones);

        String token = tokenProvider.generateToken(user);
        user.setToken(token);

        User savedUser = userRepository.save(user);
        var response = UserMapper.INSTANCE.entityToDto(savedUser);
        return new UserResponseMessage(response, HttpStatus.CREATED);
    }
}
