package com.inventory.service;

import com.inventory.dto.OrdersDto;
import com.inventory.dto.frontendreq.OrderResponceDto;

import java.util.Date;
import java.util.List;

public interface OrderService {
    //Add New Order..
    OrderResponceDto addNewOrder(OrdersDto ordersDto);

    List<OrdersDto> orderHistory(Long adminId, Integer page, Integer size, String search);

    //Order History By Date...
    List<OrdersDto> orderHistoryByDate(Long adminId, Date date);
}
