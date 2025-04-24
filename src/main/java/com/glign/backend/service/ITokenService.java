package com.glign.backend.service;

public interface ITokenService {
    void storeToken(String userId, String token, int expirationSeconds);
    String getToken(String userId);
    boolean removeToken(String userId);
    boolean tokenExist(String userId);
}
