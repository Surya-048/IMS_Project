package com.inventory.dto;

public class ErrerDto {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrerDto(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrerDto{" +
                "message='" + message + '\'' +
                '}';
    }
}
