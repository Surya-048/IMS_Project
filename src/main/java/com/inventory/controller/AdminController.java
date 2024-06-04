package com.inventory.controller;

import com.inventory.dto.ProductsDto;
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
public class AdminController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductService productService;

    // Showing All Product Details..
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/allproducts")
    public ResponseEntity<List<ProductsDto>> allProducts(){
        List<ProductsDto> productsDtoList = this.productService.allProductsDetails();

        return new ResponseEntity<>(productsDtoList, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addproduct")
    public ResponseEntity addProduct(@RequestBody ProductsDto productsDto){
        ProductsDto productsDto1 = null;
        if(productsDto.getProductId()==null){
            this.productService.addNewProduct(productsDto);

        }else {
            this.productService.updateProduct(productsDto);
        }

        return  ResponseEntity.ok(HttpStatus.CREATED);
    }

}
