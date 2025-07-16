package com.redis.sample.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.json.JSONObject;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String ucc;
    String name;
    int age;

    public String getUcc() {
        return ucc;
    }

    public void setUcc(String ucc) {
        this.ucc = ucc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(int id, String ucc, String name, int age) {
        this.id = id;
        this.ucc = ucc;
        this.name = name;
        this.age = age;
    }

    public String toJsonObject(){
        JSONObject response = new JSONObject();
        try {
            response.put("id", id);
            response.put("ucc", ucc);
            response.put("name", name);
            response.put("age", age);
        } catch (Exception e) {
            System.out.println("[ERROR] in converting to json object " + e.toString());
        }
        return response.toString();
    }
}
