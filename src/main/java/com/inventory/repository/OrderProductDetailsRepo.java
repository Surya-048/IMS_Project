package com.inventory.repository;

import com.inventory.model.OrderProductDetails;
import com.inventory.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductDetailsRepo extends JpaRepository<OrderProductDetails,Integer> {
    List<OrderProductDetails> findByOrders(Orders orders);
}
