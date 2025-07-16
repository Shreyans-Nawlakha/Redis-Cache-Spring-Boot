package com.redis.sample.controller;

import com.redis.sample.entity.User;
import com.redis.sample.service.MainService;
import com.redis.sample.service.RedisMemoryService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MemoryController {

    @Autowired
    private RedisMemoryService redisMemoryService;

    @Autowired
    MainService mainService;

    @PostMapping("/storeUser")
    public ResponseEntity<String> storeUser(@RequestBody String iputData) {
        String response = mainService.storeUser(iputData);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-user/{inputData}")
    public ResponseEntity<String> getUser(@PathVariable String inputData) {
        String serviceName = "UserService";
        String userJsonStr = redisMemoryService.getValue(serviceName, inputData);

        if (userJsonStr == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userJsonStr);
    }

    @GetMapping("/get-all/{serviceName}")
    public ResponseEntity<String> getAllDataFromService(@PathVariable String serviceName) {
        if (serviceName == null || serviceName.isBlank()) {
            return ResponseEntity.badRequest().body("Service name is required");
        }

        Map<String, String> data = redisMemoryService.getServiceMemory(serviceName);
        if (data.isEmpty()) {
            return ResponseEntity.ok("No data found for service: " + serviceName);
        }
        JSONObject responseObject = new JSONObject(data);
        return ResponseEntity.ok(responseObject.toString());
    }


    @GetMapping("/up")
    public String server() {
        return "hello";
    }
}
