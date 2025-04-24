package com.glign.backend.service;

import com.glign.backend.dto.NumberReqDto;
import com.glign.backend.dto.PhoneReqDto;
import com.glign.backend.dto.PhoneUpdateRequestDto;
import com.glign.backend.exception.ApiException;
import com.glign.backend.util.ResponseMessage;

public interface IPhoneService {
    ResponseMessage<?> updatePhones(String authHeader, PhoneUpdateRequestDto request) throws ApiException;
    ResponseMessage<?> addPhones(String authHeader, PhoneReqDto request) throws ApiException;
    ResponseMessage<?> deletePhones(String authHeader, NumberReqDto request) throws ApiException;
    ResponseMessage<?> getPhones(String authHeader) throws ApiException;
}
