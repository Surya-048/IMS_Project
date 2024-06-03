package com.inventory.dto;

public class ProductsDto {

    private String productName;

    private String productType;

    private Integer quantity;

    private Integer minimumQuantity;

    private Double costPrice;

    private Double sellingPrice;

    private Boolean isActive;

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
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", quantity=" + quantity +
                ", minimumQuantity=" + minimumQuantity +
                ", costPrice=" + costPrice +
                ", sellingPrice=" + sellingPrice +
                ", isActive=" + isActive +
                '}';
    }
}
