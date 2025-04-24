package com.glign.backend.service;

public interface ITokenService {
    void storeToken(String userId, String token, int expirationSeconds);

    String getToken(String userId);

    void removeToken(String userId);
}
