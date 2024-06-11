package com.inventory.dto.frontendreq;

public class OrderResponceDto {
    private String date;
    private String orderId;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "OrderResponceDto{" +
                "date='" + date + '\'' +
                ", transactionId='" + orderId + '\'' +
                '}';
    }
}
