package com.redis.sample.service;

import com.redis.sample.entity.User;
import com.redis.sample.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    private static final LoggerService logger = new LoggerService(MainService.class);

    @Autowired RedisMemoryService redisMemoryService;
    @Autowired UserRepository userRepository;

    public String storeUser(String inputData) {
        JSONObject res = new JSONObject();
        try {
            JSONObject req = new JSONObject(inputData);
            if (!req.has("service") || !req.has("user") || !req.has("name") || !req.has("age")){
                res.put("status","failed");
                res.put("message","fields missing");
                return res.toString();
            }
            String serviceName = req.optString("service","");
            String ucc = req.optString("user","");
            String name = req.optString("name");
            int age = req.optInt("age");
            User savedEntity = userRepository.save(new User(0,ucc,name,age));
            redisMemoryService.putValue(serviceName.toUpperCase(),ucc, savedEntity.toJsonObject());
            res.put("status","success");
        }
        catch (Exception ex) {
            logger.logError("[ERROR] in store user -> " + ex);
        }
        return res.toString();
    }
}
