package com.webapp.staffmanager.util;

import java.time.Duration;
import java.util.Set;

import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, String> redisTemplate;
    
    public void addToBlackList(String token, long ttl) {
        redisTemplate.opsForValue().set(token, "blacklisted", Duration.ofMillis(ttl));
    }
    
    public boolean isTokenBlackListed(String token) {
        return redisTemplate.hasKey(token);
    }
    
    public Set<String> getBlackList() {
        return redisTemplate.keys("*");
    }
}
