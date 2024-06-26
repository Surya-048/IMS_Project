package com.inventory.controller;

import com.inventory.dto.OrdersDto;
import com.inventory.dto.frontendreq.OrderResponceDto;
import com.inventory.exception.GenericException;
import com.inventory.service.OrderService;
import com.inventory.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/IMS")
public class OrderController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    //Order Products..
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/order")
    public ResponseEntity<OrderResponceDto> orderProduct(@RequestBody OrdersDto ordersDto){

        OrderResponceDto orderResponceDto = this.orderService.addNewOrder(ordersDto);
//        System.out.println(ordersDto);

        return new ResponseEntity<>(orderResponceDto, HttpStatus.CREATED);
    }

    //All Orders..
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/allorders")
    public ResponseEntity<List<OrdersDto>> allOrders(@RequestParam(value = "adminId", defaultValue = "0")Long adminId,
                                                     @RequestParam(value = "page", defaultValue = "0",required = false)Integer page,
                                                     @RequestParam(value = "size",defaultValue = "0",required = false)Integer size,
                                                     @RequestParam(value = "search",defaultValue = "",required = false)String search,
                                                     @RequestParam(value = "date",defaultValue = "",required = false)String date){
//        System.out.println();
        List<OrdersDto> ordersDto;
        if(date!=null && !date.equals("") && search.equals("")){
            try {
                ordersDto = this.orderService.orderHistoryByDate(adminId, new SimpleDateFormat("yyyy-MM-dd").parse(date));
                return new ResponseEntity<>(ordersDto,HttpStatus.OK);
            }catch (Exception e){
                throw new GenericException("The Date is not Valid");
            }

        }
        if (adminId == 0) {
            throw new GenericException("Admin Id Asuni, Patha ....");
        } else {
            ordersDto = this.orderService.orderHistory(adminId, page, size, search);
//                System.out.println(ordersDto);

        }
        return new ResponseEntity<>(ordersDto, HttpStatus.OK);

    }

    //All Orders According to the Date ..
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/dateanalysis")
//    public ResponseEntity<List<OrdersDto>> dateAnalysis(@RequestParam(value = "adminId", defaultValue = "0")Long adminId,
//                                                        @RequestParam(value = "date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
//
//        List<OrdersDto> ordersDtos = this.orderService.orderHistoryByDate(adminId, date);
//        return new ResponseEntity<>(ordersDtos,HttpStatus.OK);
//    }




}
