package com.inventory.controller;

import com.inventory.dto.frontendreq.OrderResponceDto;
import com.inventory.dto.ProductsDto;
import com.inventory.dto.OrdersDto;
import com.inventory.exception.GenericException;
import com.inventory.service.OrderService;
import com.inventory.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/IMS")
public class AdminController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    // Showing All Product Details..
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/allproducts")
    public ResponseEntity<List<ProductsDto>> allProducts(@RequestParam(value = "adminId",defaultValue = "0")Long adminId){
        List<ProductsDto> productsDtoList;
        if(adminId == 0){
            throw new GenericException("Admin Id Asuni, Patha ....");
        }else {
            productsDtoList = this.productService.allProductsDetails(adminId);
        }

//        if(productsDtoList.isEmpty())
//            throw new GenericException("The Admin with Id "+ adminId + " doesn't Exits");
//        System.out.println(adminId);
        return new ResponseEntity<>(productsDtoList, HttpStatus.OK);
    }


    //Add products..
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addproduct")
    public ResponseEntity<ProductsDto> addProduct(@RequestBody ProductsDto productsDto){
        ProductsDto productsDto1 = null;
        if(productsDto.getProductId()==null){
           productsDto1 = this.productService.addNewProduct(productsDto);

        }else {
            productsDto1 = this.productService.updateProduct(productsDto);
        }

        return new ResponseEntity<>(productsDto1, HttpStatus.CREATED);
    }

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
                                    @RequestParam(value = "size",defaultValue = "5",required = false)Integer size,
                                    @RequestParam(value = "customerName",defaultValue = "",required = false)String customerName){
//        System.out.println();
            List<OrdersDto> ordersDto;
            if (adminId == 0) {
                throw new GenericException("Admin Id Asuni, Patha ....");
            } else {
                ordersDto = this.orderService.orderHistory(adminId, page, size, customerName);
//                System.out.println(ordersDto);

            }
            return new ResponseEntity<>(ordersDto, HttpStatus.OK);

    }

    //All Orders According to the Date ..
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dateanalysis")
    public ResponseEntity<List<OrdersDto>> dateAnalysis(@RequestParam(value = "adminId", defaultValue = "0")Long adminId,
                                          @RequestParam(value = "date")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date date){

        List<OrdersDto> ordersDtos = this.orderService.orderHistoryByDate(adminId, date);
        return new ResponseEntity<>(ordersDtos,HttpStatus.OK);
    }

}
