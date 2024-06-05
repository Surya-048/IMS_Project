package com.inventory.repository;

import com.inventory.model.OrderProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductDetailsRepo extends JpaRepository<OrderProductDetails,Integer> {
}
