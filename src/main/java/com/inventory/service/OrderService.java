package com.inventory.service;

import com.inventory.dto.OrderProductDetailsDto;
import com.inventory.dto.frontendreq.OrderResponceDto;
import com.inventory.dto.OrdersDto;
import com.inventory.exception.GenericException;
import com.inventory.model.OrderProductDetails;
import com.inventory.model.Orders;
import com.inventory.model.Products;
import com.inventory.model.Seller;
import com.inventory.repository.OrderProductDetailsRepo;
import com.inventory.repository.OrdersRepo;
import com.inventory.repository.ProductsRepo;
import com.inventory.repository.SellerRepo;
import org.hibernate.query.SortDirection;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductsRepo productsRepo;
    @Autowired
    private SellerRepo sellerRepo;

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private OrderProductDetailsRepo orderProductDetailsRepo;


    //Add New Order..
    public OrderResponceDto addNewOrder(OrdersDto ordersDto) {
        OrderResponceDto orderResponceDto = new OrderResponceDto();
        try {
            Seller seller = this.sellerRepo.findById(ordersDto.getAdminId().intValue()).get();
            if (seller.isActive()) {

                Orders orders = this.modelMapper.map(ordersDto, Orders.class);
                Date date = new Date();
                orders.setCreatedAt(date);
                this.ordersRepo.save(orders);
                Orders orders1 = this.ordersRepo.findByCreatedAt(date);
                ordersDto.getProducts().stream()
                        .map(p -> {
                            OrderProductDetails orderProductDetails = new OrderProductDetails();
                            orderProductDetails.setQuantity(p.getQuantity());
                            orderProductDetails.setOrders(orders1);
                            Products products = this.productsRepo.findById(p.getProductId()).get();

                            if (products.getIsActive() && ((products.getQuantity()-p.getQuantity())>=0) &&
                                    (p.getTotalPrice() == (p.getQuantity() * products.getSellingPrice()))) {
                                products.setQuantity(products.getQuantity() - orderProductDetails.getQuantity());
                                products.setUpdatedAt(new Date());
                            }
                            Products products1 = this.productsRepo.save(products);
                            orderProductDetails.setProductId(products1);
                            return orderProductDetails;
                        }).forEach(this.orderProductDetailsRepo::save);

                OrdersDto ordersDto1 = this.modelMapper.map(orders1,OrdersDto.class);
                orderResponceDto.setTransactionId(ordersDto1.getTransactionId());
                orderResponceDto.setDate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date));
            }
        } catch (Exception e) {
            throw new GenericException("The Quantity of the Product is less Or The Sum Price is Inaccurate .");
        }

        return orderResponceDto;
    }
    
    //Order History
    
    public List<OrdersDto> orderHistory(Long adminId, Integer page, Integer size,String customerName){
//        Pageable p = PageRequest.of(page,size).withSort(Sort.by(Sort.Direction.DESC,"createdAt"));

        Pageable p = PageRequest.of(page,size,Sort.by("createdAt").descending());
        Long size1  =(long) this.ordersRepo.findByAdminId(adminId).size();

        if(customerName.isEmpty()) {
            try {
                Page<Orders> pageOrders = this.ordersRepo.findByAdminId(adminId,p);

                if(pageOrders.isEmpty()){
                    throw new GenericException("There is no any Orders in this Seller.");
                }
                List<Orders> orders = pageOrders.getContent();

                List<OrdersDto> ordersDtoList = orders.stream().map(o -> {
                    OrdersDto ordersDto1 = this.modelMapper.map(o, OrdersDto.class);
//                    ordersDto1.setTotalBills(size1);
                    ordersDto1.setProducts(this.orderProductDetailsRepo.findByOrders(o).stream().map((od) -> {
                        OrderProductDetailsDto orderProductDetailsDto1 = this.modelMapper.map(od.getProductId(), OrderProductDetailsDto.class);
                        orderProductDetailsDto1.setQuantity(od.getQuantity());
                        orderProductDetailsDto1.setTotalPrice(od.getQuantity() * od.getProductId().getSellingPrice());
//                        System.err.println(orderProductDetailsDto1);
                        return orderProductDetailsDto1;
                    }).collect(Collectors.toList()));
                    return ordersDto1;
                }).toList();

                ordersDtoList.get(0).setTotalBills(size1);
                return ordersDtoList;

            } catch (Exception e) {
                throw new GenericException("There is no any Orders in this Seller.");
            }

        }else {
            try {
                Page<Orders> pageOrders = this.ordersRepo.findByCustomerNameContaining(customerName,p);
                if(pageOrders.isEmpty()){
                    throw new GenericException("There is no any Order in the Given Customer Name.");
                }

                List<Orders> orders = pageOrders.getContent();
                List<OrdersDto> ordersDtoList = orders.stream().map(o -> {
                    OrdersDto ordersDto1 = this.modelMapper.map(o, OrdersDto.class);
//                    ordersDto1.setTotalBills(size1);
                    ordersDto1.setProducts(this.orderProductDetailsRepo.findByOrders(o).stream().map((od) -> {
                        OrderProductDetailsDto orderProductDetailsDto1 = this.modelMapper.map(od.getProductId(), OrderProductDetailsDto.class);
                        orderProductDetailsDto1.setQuantity(od.getQuantity());
                        orderProductDetailsDto1.setTotalPrice(od.getQuantity() * od.getProductId().getSellingPrice());
//                        System.err.println(orderProductDetailsDto1);
                        return orderProductDetailsDto1;
                    }).collect(Collectors.toList()));

                    return ordersDto1;
                }).toList();

                ordersDtoList.get(0).setTotalBills(size1);

                return ordersDtoList;

            } catch (Exception e) {
                throw new GenericException("There is no any Order in the Given Customer Name.");
            }
        }
    }

    //Order History By Date...
    public List<OrdersDto> orderHistoryByDate(Long adminId,Date date){

        try {
            List<Orders> orders = this.ordersRepo.findByAdminIdAndCreatedAt(adminId,date);

            List<OrdersDto> ordersDtoList = orders.stream().map(o -> {
                OrdersDto ordersDto1 = this.modelMapper.map(o, OrdersDto.class);
                ordersDto1.setProducts(this.orderProductDetailsRepo.findByOrders(o).stream().map((od) -> {
                    OrderProductDetailsDto orderProductDetailsDto1 = this.modelMapper.map(od.getProductId(), OrderProductDetailsDto.class);
                    orderProductDetailsDto1.setQuantity(od.getQuantity());
                    orderProductDetailsDto1.setTotalPrice(od.getQuantity() * od.getProductId().getSellingPrice());
//                    System.err.println(orderProductDetailsDto1);
                    return orderProductDetailsDto1;
                }).collect(Collectors.toList()));
                return ordersDto1;
            }).toList();
            return ordersDtoList;

        } catch (Exception e) {
            throw new GenericException("There is no any Orders in the Perticular date.");
        }
    }

}
