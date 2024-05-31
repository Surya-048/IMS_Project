package com.inventory.repository;

import com.inventory.model.SoldProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoldProductDetailsRepo extends JpaRepository<SoldProductDetails,Integer> {
}
