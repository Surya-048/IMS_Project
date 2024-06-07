package com.inventory.model;

import jakarta.persistence.*;

@Entity
public class OrderProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "product_Id",nullable = false)
    private Products productId;

    private int quantity;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "sold_Order_Id",nullable = false)
    private Orders orders;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Products getProductId() {
        return productId;
    }

    public void setProductId(Products productId) {
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


    @Override
    public String toString() {
        return "SoldProductDetails{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", orders=" + orders +
                '}';
    }
}
