package com.inventory.repository;

import com.inventory.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OrdersRepo extends JpaRepository<Orders,Integer> {
    Orders findByCreatedAt(Date date);
}
