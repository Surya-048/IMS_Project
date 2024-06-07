package com.inventory.dto.frontendreq;

import org.springframework.stereotype.Component;

@Component
public class LoginResponseDto {
    private String jwtToken;

    private String userName;

    private long adminId;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
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
                ", adminId=" + adminId +
                '}';
    }
}
