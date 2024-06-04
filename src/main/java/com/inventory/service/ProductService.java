package com.inventory.service;

import com.inventory.dto.ProductsDto;
import com.inventory.exception.GenericException;
import com.inventory.model.Products;
import com.inventory.model.Seller;
import com.inventory.repository.ProductsRepo;
import com.inventory.repository.SellerRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductsRepo productsRepo;
    @Autowired
    private SellerRepo sellerRepo;


    //Find Single Product
//    public ProductsDto singleProduct(Long id){
//        Products byId =this.productsRepo.findById(id).get();
//        return this.modelMapper.map(byId,ProductsDto.class);
//    }

    //Find All Products Details...
    public List<ProductsDto> allProductsDetails(){
        List<Products> products = this.productsRepo.findAll();

//        System.out.println(products);
        return products.stream().map(p-> this.modelMapper.map(p,ProductsDto.class)).toList();
    }

    //Add new product..
    public void addNewProduct(ProductsDto productsDto){
        Products products = this.modelMapper.map(productsDto,Products.class);
        products.setActive(true);
        products.setCreatedAt(new Date());
        products.setUpdatedAt(new Date());
//         Seller seller = this.sellerRepo.findById(productsDto.getAdminId());
         Seller seller =this.sellerRepo.findById(productsDto.getAdminId()).get();
         products.setCreatedBy(seller);
         this.productsRepo.save(products);
         return ;
    }

    //Update Product..
    public void updateProduct(ProductsDto productsDto){
        Products products ;
        try{
            products = this.productsRepo.findById(productsDto.getProductId()).get();
        }catch (NoSuchElementException e){
            throw new GenericException("This Product Doesn't Exits. ");
        }

        products = this.modelMapper.map(productsDto, Products.class);
        this.productsRepo.save(products);
        return ;
    }
}
