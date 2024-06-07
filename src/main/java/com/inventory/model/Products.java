package com.inventory.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "products_details")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_Id")
    private Long productId;

    @Column(name = "product_Name",nullable = false)
    private String productName;

    @Column(name = "cost_Price",nullable = false)
    private double costPrice;

    @Column(name = "sell_Price",nullable = false)
    private double sellingPrice;
    @Column(name = "min_Quantity",nullable = false)
    private int minimumQuantity;

    @Column(name = "quantity",nullable = false)
    private int quantity;

    @Column(name = "product_Type")
    private String productType;

    @Column(name = "is_Active")
    private boolean isActive;

    @Column(name = "created_At",nullable = false)
    private Date createdAt;

//    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @Column(name = "created_By",nullable = false)
    private Long adminId;

    @Column(name = "updated_At")
    private Date updatedAt;

    public Products(){}

    public Products(Long productId, String productName, double costPrice,
                    double sellingPrice, int minimumQuantity, int quantity,
                    String productType, boolean isActive, Date createdAt,
                    Long adminId, Date updatedAt) {
        this.productId = productId;
        this.productName = productName;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.minimumQuantity = minimumQuantity;
        this.quantity = quantity;
        this.productType = productType;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.adminId = adminId;
        this.updatedAt = updatedAt;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(int minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Products{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", costPrice=" + costPrice +
                ", sellingPrice=" + sellingPrice +
                ", minimumQuantity=" + minimumQuantity +
                ", quantity=" + quantity +
                ", productType='" + productType + '\'' +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", adminId=" + adminId +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
