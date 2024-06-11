package com.inventory.repository;

import com.inventory.model.Products;
import com.inventory.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Long> {
    List<Products> findByAdminId (Long id);
    Products findByProductName(String name);
}
