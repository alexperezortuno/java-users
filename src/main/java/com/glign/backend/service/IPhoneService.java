package com.glign.backend.service;

import com.glign.backend.dto.NumberReqDto;
import com.glign.backend.dto.PhoneReqDto;
import com.glign.backend.dto.PhoneUpdateRequestDto;
import com.glign.backend.exception.ApiException;
import com.glign.backend.util.ResponseMessage;

public interface IPhoneService {
    ResponseMessage<?> updatePhones(String id, PhoneUpdateRequestDto request) throws ApiException;
    ResponseMessage<?> addPhones(String id, PhoneReqDto request) throws ApiException;
    ResponseMessage<?> deletePhones(String id, NumberReqDto request) throws ApiException;
    ResponseMessage<?> getPhones(String id) throws ApiException;
}
