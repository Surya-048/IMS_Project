package com.inventory.repository;

import com.inventory.model.SoldOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldOrdersRepo extends JpaRepository<SoldOrders,Integer> {
}
