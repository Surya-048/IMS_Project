package com.inventory.service;

import com.inventory.dto.ProductsDto;

import java.util.List;

public interface ProductService {
    //Find All Products Details according to Admin..
    List<ProductsDto> allProductsDetails(Long id);

    List<ProductsDto> allProducts();

    //Add new product..
    ProductsDto addNewProduct(ProductsDto productsDto);

    //Update Product..
    ProductsDto updateProduct(ProductsDto productsDto);
}
