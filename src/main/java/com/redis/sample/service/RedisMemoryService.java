package com.redis.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RedisMemoryService {

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisMemoryService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // Set the entire service memory (Map<String, String>)
    public void setServiceMemory(String serviceName, Map<String, String> data) {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        hashOps.putAll(serviceName, data);
    }

    // Get the full map for a service
    public Map<String, String> getServiceMemory(String serviceName) {
        HashOperations<String, String, String> hashOps = redisTemplate.opsForHash();
        return hashOps.entries(serviceName);
    }

    // Get a specific value by key
    public String getValue(String serviceName, String key) {
        return (String) redisTemplate.opsForHash().get(serviceName, key);
    }

    // Put a specific key-value pair
    public void putValue(String serviceName, String key, String value) {
        redisTemplate.opsForHash().put(serviceName, key, value);
    }
}
