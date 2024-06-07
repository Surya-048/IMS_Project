package com.inventory.dto.frontendreq;

public class OrderResponceDto {
    private String date;
    private String transactionId;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "OrderResponceDto{" +
                "date='" + date + '\'' +
                ", transactionId='" + transactionId + '\'' +
                '}';
    }
}
