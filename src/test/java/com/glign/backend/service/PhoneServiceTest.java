package com.glign.backend.service;

import com.glign.backend.dto.MessageResponse;
import com.glign.backend.dto.PhoneReqDto;
import com.glign.backend.dto.PhoneUpdateRequestDto;
import com.glign.backend.exception.ApiException;
import com.glign.backend.jpa.entity.User;
import com.glign.backend.repository.PhoneRepository;
import com.glign.backend.repository.UserRepository;
import com.glign.backend.service.impl.PhoneService;
import com.glign.backend.util.ResponseCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PhoneServiceTest {

    @InjectMocks
    private PhoneService phoneService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PhoneRepository phoneRepository;

    @Test
    void shouldUpdateUserPhonesSuccessfully() throws Exception {
        UUID uuid = UUID.randomUUID();

        var phoneDto = new PhoneReqDto("1234567", "1", "57");
        var request = new PhoneUpdateRequestDto(List.of(phoneDto));

        var user = new User();
        user.setUuid(uuid);
        user.setPhones(new ArrayList<>());

        when(userRepository.findByUuid(uuid)).thenReturn(user);
        //when(userRepository.save(any())).thenReturn(user);

        var response = phoneService.updatePhones(uuid.toString(), request);

        assertEquals(ResponseCode.PHONE_UPDATED.getMessage(), ((MessageResponse) response.getDto()).getMessage());
        assertEquals(HttpStatus.OK, response.getCode());
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        UUID uuid = UUID.randomUUID();

        var request = new PhoneUpdateRequestDto(List.of(new PhoneReqDto("1234567", "1", "57")));

        when(userRepository.findByUuid(uuid)).thenReturn(null);

        ApiException ex = assertThrows(ApiException.class, () ->
                phoneService.updatePhones(uuid.toString(), request));

        assertEquals(ResponseCode.USER_NOT_FOUND.getMessage(), ex.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, ex.getResponse().getStatusCode());
    }

    @Test
    void shouldThrowExceptionWhenPhonesMissing() {
        UUID uuid = UUID.randomUUID();
        var request = new PhoneUpdateRequestDto(new ArrayList<>());

        ApiException ex = assertThrows(ApiException.class, () ->
                phoneService.updatePhones(uuid.toString(), request));

        assertEquals(ResponseCode.PHONE_REQUIRED.getMessage(), ex.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, ex.getResponse().getStatusCode());
    }
}
