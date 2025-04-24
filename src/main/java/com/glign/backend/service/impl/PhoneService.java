package com.glign.backend.service.impl;

import com.glign.backend.dto.NumberReqDto;
import com.glign.backend.dto.PhoneReqDto;
import com.glign.backend.dto.PhoneUpdateRequestDto;
import com.glign.backend.dto.MessageResponse;
import com.glign.backend.exception.ApiException;
import com.glign.backend.jpa.entity.Phone;
import com.glign.backend.mapper.PhoneMapper;
import com.glign.backend.repository.PhoneRepository;
import com.glign.backend.repository.UserRepository;
import com.glign.backend.service.IPhoneService;
import com.glign.backend.util.ResponseCode;
import com.glign.backend.util.ResponseMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.UUID;

@Service
@Log4j2
public class PhoneService implements IPhoneService {
    private PhoneRepository phoneRepository;
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPhoneRepository(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public ResponseMessage<?> updatePhones(String id, PhoneUpdateRequestDto request) throws ApiException {
        if (request.getPhones() == null || request.getPhones().isEmpty()) {
            throw new ApiException(ResponseCode.PHONE_REQUIRED.getMessage(), HttpStatus.BAD_REQUEST);
        }

        try {
            var user = userRepository.findByUuid(UUID.fromString(id));
            if (user == null) {
                throw new ApiException(ResponseCode.USER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
            }

            var phones = new ArrayList<Phone>();
            for (var phone : request.getPhones()) {
                var phoneEntity = PhoneMapper.INSTANCE.dtoToEntity(phone);
                phoneEntity.setUser(user);
                phones.add(phoneEntity);
            }
            user.getPhones().clear();
            user.getPhones().addAll(phones);
            userRepository.save(user);
            return new ResponseMessage<>(new MessageResponse(ResponseCode.PHONE_UPDATED.getMessage()), HttpStatus.OK);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error updating phones: {}", e.getMessage());
            throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseMessage<?> addPhones(String id, PhoneReqDto request) throws ApiException {
        if (request.getNumber().isEmpty()) {
            throw new ApiException(ResponseCode.PHONE_REQUIRED.getMessage(), HttpStatus.BAD_REQUEST);
        }

        if (request.getCityCode().isEmpty()) {
            throw new ApiException(ResponseCode.PHONE_REQUIRED.getMessage(), HttpStatus.BAD_REQUEST);
        }

        if (request.getCountryCode().isEmpty()) {
            throw new ApiException(ResponseCode.PHONE_REQUIRED.getMessage(), HttpStatus.BAD_REQUEST);
        }

        try {
            var user = userRepository.findByUuid(UUID.fromString(id));
            if (user == null) {
                throw new ApiException(ResponseCode.USER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
            }

            var phones = PhoneMapper.INSTANCE.dtoToEntity(request);
            phones.setUser(user);
            phoneRepository.save(phones);
            return new ResponseMessage<>(new MessageResponse(ResponseCode.PHONE_ADDED.getMessage()), HttpStatus.OK);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error adding phones: {}", e.getMessage());
            throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public ResponseMessage<?> deletePhones(String id, NumberReqDto request) throws ApiException {
        if (request.getNumber().isEmpty()) {
            throw new ApiException(ResponseCode.NUMBER_REQUIRED.getMessage(), HttpStatus.BAD_REQUEST);
        }

        var user = userRepository.findByUuid(UUID.fromString(id));
        if (user == null) {
            throw new ApiException(ResponseCode.USER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
        }

        try {
            var phone = phoneRepository.findByUserIdAndNumber(user.getId(), request.getNumber());
            if (phone.isEmpty()) {
                throw new ApiException(ResponseCode.PHONE_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
            }

            phoneRepository.deleteById(phone.orElseThrow().getId());
            return new ResponseMessage<>(new MessageResponse(ResponseCode.PHONE_DELETED.getMessage()), HttpStatus.OK);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error deleting phones: {}", e.getMessage());
            throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseMessage<?> getPhones(String id) throws ApiException {
        try {
            var user = userRepository.findByUuid(UUID.fromString(id));
            if (user == null) {
                throw new ApiException(ResponseCode.USER_NOT_FOUND.getMessage(), HttpStatus.NOT_FOUND);
            }

            var allPhones = phoneRepository.findAllByUser(user);
            if (allPhones.isEmpty()) {
                throw new ApiException(ResponseCode.PHONE_NOT_FOUND.getMessage(), HttpStatus.NO_CONTENT);
            }
            var response = PhoneMapper.INSTANCE.entityToDto(allPhones);
            return new ResponseMessage<>(response, HttpStatus.OK);
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error getting phones: {}", e.getMessage());
            throw new ApiException(ResponseCode.INTERNAL_SERVER_ERROR.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
