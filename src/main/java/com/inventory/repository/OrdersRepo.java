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
    List<Orders> findByAdminId(Long adminId);
    Page<Orders> findByAdminId(Long adminId, Pageable pageable);

    @Query("select o from Orders o where o.adminId =:adminId and (o.customerName like CONCAT('%', :search,'%') or o.phoneNo like CONCAT('%', :search,'%'))")
    Page<Orders> findAllByAdminIdAndSearch(@Param("adminId")Long adminId,@Param("search")String search,@Param("search")String search0,Pageable pageable);

    // Method to get sales details by user ID and date
    @Query("SELECT o FROM Orders o WHERE o.adminId = :userId AND DATE(o.createdAt) = :saleDate")
    List<Orders> findByAdminIdAndCreatedAt(@Param("userId") Long userId,
                                        @Param("saleDate") Date saleDate);

    @Query("select order from Orders order where order.adminId =:adminId and (order.customerName like CONCAT('%',:search,'%') or order.phoneNo like CONCAT('%',:search,'%') and Date(order.createdAt) =:date)")
    List<Orders> orderHistoryBySearchAndDate (@Param("adminId")Long adminId,@Param("search")String search,@Param("search")String search1,@Param("date")Date date);
}
