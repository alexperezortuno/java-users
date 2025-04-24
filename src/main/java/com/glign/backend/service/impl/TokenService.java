package com.glign.backend.service.impl;

import com.glign.backend.service.ITokenService;
import net.spy.memcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService implements ITokenService {
    private final MemcachedClient memcachedClient;

    @Autowired
    public TokenService(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    @Override
    public void storeToken(String userId, String token, int expirationSeconds) {
        memcachedClient.set(userId, expirationSeconds, token);
    }

    @Override
    public String getToken(String userId) {
        Object token = memcachedClient.get(userId);
        return token != null ? token.toString() : null;
    }

    @Override
    public boolean tokenExist(String userId) {
        Object token = memcachedClient.get(userId);
        return token != null;
    }

    @Override
    public boolean removeToken(String userId) {
        return memcachedClient.delete(userId).getStatus().isSuccess();
    }
}
