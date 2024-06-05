package com.inventory.dto;

import java.util.Date;
import java.util.List;

public class OrdersDto {
    private Long Id;

    private Long adminId;

    private Date createdAt;

    private String customerName;

    private String phoneNo;

    private String description;

    private double totalAmount;

    private String orderId;

    List<OrderProductDetailsDto> products;

    public List<OrderProductDetailsDto> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductDetailsDto> products) {
        this.products = products;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    public String getOrderId() {
        return "IOID$"+this.Id;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
                ", orderId='" + orderId + '\'' +
                ", products=" + products +
                '}';
    }
}
