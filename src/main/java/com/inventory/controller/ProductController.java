package com.inventory.controller;

import com.inventory.dto.ProductsDto;
import com.inventory.exception.GenericException;
import com.inventory.service.OrderService;
import com.inventory.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/IMS")
public class ProductController {
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

}
