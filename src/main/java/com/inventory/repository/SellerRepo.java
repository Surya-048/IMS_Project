package com.inventory.repository;

import com.inventory.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepo extends JpaRepository<Seller,Integer> {
    Optional<Seller> findByEmail(String userName);
}
