package com.inventory.dto;

import com.inventory.model.Seller;

import java.util.Date;

public class ProductsDto {
    private Long productId;

    private String productName;

    private String productType;

    private Integer quantity;

    private Integer minimumQuantity;

    private Double costPrice;

    private Double sellingPrice;

    private Boolean isActive;

    private String productCode;

    private Date createdAt;

    private Integer adminId;

    private Date updatedAt;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer createdBy) {
        this.adminId = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getProductCode() {
        return "IPID0"+this.productId;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(Integer minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "ProductsDto{" +
                "productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", quantity=" + quantity +
                ", minimumQuantity=" + minimumQuantity +
                ", costPrice=" + costPrice +
                ", sellingPrice=" + sellingPrice +
                ", isActive=" + isActive +
                ", productCode='" + productCode + '\'' +
                '}';
    }
}
