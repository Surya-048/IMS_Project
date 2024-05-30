package com.inventory.model;

import com.inventory.enums.ProductType;
import jakarta.persistence.*;

import javax.management.relation.Role;
import java.util.Date;
import java.util.List;

@Entity
public class ProductInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    private String productName;

    private double costPrice;

    private double sellPrice;

    private int minQuantity;

    private int quantity;

    private ProductType productType;

    private boolean isActive;

    private Date createdAt;

    private String createdBy;

    private Date updatedAt;

    private String updatedBy;

    public ProductInventory() {
    }

    public ProductInventory(int productId, String productName, double costPrice, double sellPrice, int minQuantity,
                            int quantity, ProductType productType, boolean isActive, Date createdAt, String createdBy,
                            Date updatedAt, String updatedBy) {

        this.productId = productId;
        this.productName = productName;
        this.costPrice = costPrice;
        this.sellPrice = sellPrice;
        this.minQuantity = minQuantity;
        this.quantity = quantity;
        this.productType = productType;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
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

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String toString() {
        return "ProductInventory{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", costPrice=" + costPrice +
                ", sellPrice=" + sellPrice +
                ", minQuantity=" + minQuantity +
                ", quantity=" + quantity +
                ", productType=" + productType +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                ", updatedAt=" + updatedAt +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
