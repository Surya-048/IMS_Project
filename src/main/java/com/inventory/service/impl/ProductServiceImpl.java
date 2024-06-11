package com.inventory.service.impl;

import com.inventory.dto.ProductsDto;
import com.inventory.exception.GenericException;
import com.inventory.model.Products;
import com.inventory.repository.OrderProductDetailsRepo;
import com.inventory.repository.ProductsRepo;
import com.inventory.repository.SellerRepo;
import com.inventory.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductsRepo productsRepo;
    @Autowired
    private SellerRepo sellerRepo;

    @Autowired
    private OrderProductDetailsRepo orderProductDetailsRepo;


    //Find All Products Details according to Admin..
    @Override
    public List<ProductsDto> allProductsDetails(Long id){
        List<Products> products = this.productsRepo.findByAdminId(id);
//        System.out.println(products);
        return products.stream().map(p-> this.modelMapper.map(p,ProductsDto.class)).toList();
    }

    @Override
    public List<ProductsDto> allProducts(){
        List<Products> products = this.productsRepo.findAll();
//        System.out.println(products);
        return products.stream().map(p-> this.modelMapper.map(p,ProductsDto.class)).toList();
    }



    //Add new product..
    @Override
    public ProductsDto addNewProduct(ProductsDto productsDto){

        Products products1 = null;
        if(productsDto.getProductName().isEmpty()){
            throw new GenericException("Product Name Can't be Empty");
        }
        if(this.allProductsDetails(productsDto.getAdminId()).stream()
                .noneMatch(i -> i.getProductName().equalsIgnoreCase(productsDto.getProductName().trim()))) {
            productsDto.setProductName(productsDto.getProductName().trim());
            Products products = this.modelMapper.map(productsDto, Products.class);
            products.setCreatedAt(new Date());
            products.setUpdatedAt(new Date());
            products.setAdminId(productsDto.getAdminId());
            products1 = this.productsRepo.save(products);
        }
        return this.modelMapper.map(products1,ProductsDto.class);
    }

    //Update Product..
    @Override
    public ProductsDto updateProduct(ProductsDto productsDto){
        Products products = null ;
        try{
            products = this.productsRepo.findById(productsDto.getProductId()).get();
        }catch (NoSuchElementException e){
            throw new GenericException("This Product Doesn't Exits. ");
        }

        Date date = products.getCreatedAt();
        products = this.modelMapper.map(productsDto, Products.class);
        products.setCreatedAt(date);
        products.setUpdatedAt(new Date());
        Products products1 = this.productsRepo.save(products);
        return this.modelMapper.map(products1,ProductsDto.class);
    }



}
