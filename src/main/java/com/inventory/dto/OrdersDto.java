package com.inventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrdersDto {
    @JsonIgnore
    private Long Id;

    private Long adminId;

    @JsonIgnore
    private Date createdAt;

    private String date;

    private String customerName;

    private String phoneNo;

    private String description;

    private double totalAmount;

    private String transactionId;
    private Long totalBills;

    List<OrderProductDetailsDto> products;


    public Long getTotalBills() {
        return totalBills;
    }

    public void setTotalBills(Long totalBills) {
        this.totalBills = totalBills;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDate() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(this.createdAt);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<OrderProductDetailsDto> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductDetailsDto> products) {
        this.products = products;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTransactionId() {
        return "IOID$"+this.Id;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "OrdersDto{" +
                "Id=" + Id +
                ", adminId=" + adminId +
                ", createdAt=" + createdAt +
                ", customerName='" + customerName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", description='" + description + '\'' +
                ", totalAmount=" + totalAmount +
                ", orderId='" + transactionId + '\'' +
                ", products=" + products +
                '}';
    }
}
