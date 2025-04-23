package com.glign.backend.controller;

import com.glign.backend.dto.NumberReqDto;
import com.glign.backend.dto.PhoneReqDto;
import com.glign.backend.dto.PhoneUpdateRequestDto;
import com.glign.backend.exception.ApiException;
import com.glign.backend.service.IPhoneService;
import com.glign.backend.util.UserHttpResponseBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/phone")
public class PhoneController {
    private IPhoneService phoneService;

    @Autowired
    private void setPhoneService(IPhoneService phoneService) {
        this.phoneService = phoneService;
    }

    /**
     * Update phones of a user
     *
     * @param id User id
     * @param request PhoneUpdateRequestDto
     * @return ResponseEntity>
     */
    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePhones(@PathVariable String id, @Valid @RequestBody PhoneUpdateRequestDto request) throws ApiException {
        var responseMessage = phoneService.updatePhones(id, request);
        return UserHttpResponseBuilder.buildResponse(responseMessage);
    }

    /**
     * Add phones to a user
     *
     * @param id User id
     * @return ResponseEntity>
     */
    @PostMapping("/{id}")
    public ResponseEntity<?> addPhones(@PathVariable String id, @Valid @RequestBody PhoneReqDto request) throws ApiException {
        var responseMessage = phoneService.addPhones(id, request);
        return UserHttpResponseBuilder.buildResponse(responseMessage);
    }

    /**
     * Delete phones of a user
     *
     * @param id User id
     * @return ResponseEntity>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePhones(@PathVariable String id, @RequestBody NumberReqDto number) throws ApiException {
        var responseMessage = phoneService.deletePhones(id, number);
        return UserHttpResponseBuilder.buildResponse(responseMessage);
    }

    /**
     * Get phones of a user
     *
     * @param id User id
     * @return ResponseEntity>
     */
    @GetMapping("/all/{id}")
    public ResponseEntity<?> getPhones(@PathVariable String id) throws ApiException {
        var responseMessage = phoneService.getPhones(id);
        return UserHttpResponseBuilder.buildResponse(responseMessage);
    }
}
