package com.inventory.dto;

import org.springframework.stereotype.Component;

@Component
public class LoginResponseDto {
    private String token;

    private String name;

    private int adminId;
//    private long expiresIn;

    public void setToken(String token) {
        this.token = token;
    }
    public void setName(String name){
        this.name=name;
    }

//    public void setExpiresIn(long expiresIn) {
//        this.expiresIn = expiresIn;
//    }


    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

//    public long getExpiresIn() {
//        return expiresIn;
//    }


    @Override
    public String toString() {
        return "LoginResponseDto{" +
                "token='" + token + '\'' +
                ", name='" + name + '\'' +
                ", adminId=" + adminId +
                '}';
    }
}
