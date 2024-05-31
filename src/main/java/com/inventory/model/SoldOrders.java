package com.inventory.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
@Entity
@Table(name = "sold_orders")
public class SoldOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

//    @OneToMany
//    @JoinColumn(name = "product_Details")
//    private List<SoldProductDetails> productsOrderDetails;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "created_By",nullable = false)
    private Seller createdBy;

    @Column(name = "created_At")
    private Date createdAt;

    @Column(name = "is_Active")
    private boolean isActive;

    public int getOrderId() {
        return Id;
    }

    public void setOrderId(int orderId) {
        this.Id = orderId;
    }


    public Seller getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Seller createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + Id +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdAt +
                ", isActive=" + isActive +
                '}';
    }
}
