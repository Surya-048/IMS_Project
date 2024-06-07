package com.inventory.repository;

import com.inventory.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdersRepo extends JpaRepository<Orders,Integer> {
    Orders findByCreatedAt(Date date);
    List<Orders> findByAdminId(Long id);
    Page<Orders> findByAdminId(Long adminId, Pageable pageable);
    Page<Orders> findByCustomerNameContaining(String customerName, Pageable pageable);

    // Method to get sales details by user ID and date
    @Query("SELECT o FROM Orders o WHERE o.adminId = :userId AND DATE(o.createdAt) = :saleDate")
    List<Orders> findByAdminIdAndCreatedAt(@Param("userId") Long userId,
                                        @Param("saleDate") Date saleDate);
}
