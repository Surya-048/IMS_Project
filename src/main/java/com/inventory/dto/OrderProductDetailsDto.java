package com.inventory.dto;

import com.inventory.model.Products;
import com.inventory.model.Orders;

public class OrderProductDetailsDto {

    private Long productId;

    private int quantity;

    private Orders orders;

    private double totalPrice;

    private String productCode;

    private String productName;

    private double sellingPrice;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getProductCode() {
        return productCode;
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

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    @Override
    public String toString() {
        return "SoldProductDetailsDto{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                ", orders=" + orders +
                ", totalPrice=" + totalPrice +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", sellingPrice=" + sellingPrice +
                '}';
    }
}
