package com.inventory.dto;

import org.springframework.stereotype.Component;

@Component
public class LoginResponseDto {
    private String jwtToken;

    private String name;

    private long adminId;

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "LoginResponseDto{" +
                "jwtToken='" + jwtToken + '\'' +
                ", name='" + name + '\'' +
                ", adminId=" + adminId +
                '}';
    }
}
