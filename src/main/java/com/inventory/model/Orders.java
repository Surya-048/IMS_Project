package com.inventory.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

//    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @Column(name = "created_By",nullable = false)
    private Long adminId;

    @Column(name = "created_At")
    private Date createdAt;

    @Column(name = "customer_Name",length = 50)
    private String customerName;

    @Column(name = "phone_No",length = 20)
    private String phoneNo;

    @Column(name = "description",length = 50)
    private String description;

    @Column(name = "total_Amount")
    private double totalAmount;

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

    @Override
    public String toString() {
        return "SoldOrders{" +
                "Id=" + Id +
                ", adminId=" + adminId +
                ", createdAt=" + createdAt +
                ", customerName='" + customerName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", description='" + description + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
