package com.glign.backend.service;

import com.glign.backend.component.JwtTokenProvider;
import com.glign.backend.dto.PhoneReqDto;
import com.glign.backend.dto.UserCreateRequestDto;
import com.glign.backend.exception.ApiException;
import com.glign.backend.jpa.entity.User;
import com.glign.backend.repository.UserRepository;
import com.glign.backend.service.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtTokenProvider tokenProvider;

    @BeforeEach
    void init() {
        // Configura expresiones regulares como si vinieran del application.yml
        ReflectionTestUtils.setField(userService, "emailRegex", ".+@.+\\..+");
        ReflectionTestUtils.setField(userService, "passwordRegex", "^(?=.*[0-9]).{6,}$");
    }

    @Test
    void shouldCreateUserSuccessfully() throws ApiException {
        UserCreateRequestDto request = new UserCreateRequestDto();
        request.setEmail("test@demo.com");
        request.setPassword("pass123");
        request.setName("Test User");
        request.setPhones(List.of(new PhoneReqDto("123", "1", "57")));

        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(tokenProvider.generateToken(any(User.class))).thenReturn("mock-token");

        var response = userService.createUser(request);

        assertEquals(HttpStatus.CREATED, response.getCode());

        request.setEmail("test@demo_com");
        assertThrows(ApiException.class, () -> userService.createUser(request));

        request.setEmail("test@demo.com");
        when(userRepository.existsByEmail(anyString())).thenReturn(true);
        assertThrows(ApiException.class, () -> userService.createUser(request));

        request.setPassword("123");
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        assertThrows(ApiException.class, () -> userService.createUser(request));

        request.setPassword("pass123");
        request.setPhones(List.of());
        assertThrows(ApiException.class, () -> userService.createUser(request));
    }

    @Test
    void shouldThrowExceptionIfEmailExists() {
        when(userRepository.existsByEmail("test@demo.com")).thenReturn(true);

        UserCreateRequestDto request = new UserCreateRequestDto();
        request.setEmail("test@demo.com");

        ApiException ex = assertThrows(ApiException.class, () -> userService.createUser(request));
        assertEquals("email already exists", ex.getMessage());
    }
}

