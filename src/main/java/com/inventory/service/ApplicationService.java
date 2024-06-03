package com.inventory.service;

import com.inventory.dto.ProductsDto;
import com.inventory.dto.SellerDto;
import com.inventory.dto.SignUpDto;
import com.inventory.enums.Role;
import com.inventory.exception.GenericException;
import com.inventory.model.Products;
import com.inventory.model.Seller;
import com.inventory.repository.ProductsRepo;
import com.inventory.repository.SellerRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.PrimitiveIterator;

@Service
public class ApplicationService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private SellerRepo sellerRepo;
    @Autowired
    private ProductsRepo productsRepo;

    //Find Seller...
    public SellerDto findSeller(String userName){
        return this.modelMapper.map(this.sellerRepo.findByEmail(userName),SellerDto.class);
    }

    //Save Seller..
    public void saveSeller(SignUpDto signUpDto){

        if(this.sellerRepo.findByEmail(signUpDto.getUserName()).isPresent()){
            throw new GenericException("User already exit.");
        }else {
            Seller selller = this.modelMapper.map(signUpDto,Seller.class);
            selller.setPassword(this.passwordEncoder.encode(selller.getPassword()));
            selller.setActive(true);
            selller.setCreatedAt(new Date());
//            selller.setPhoneNumber("7735725548");
            selller.setUpdatedAt(new Date());
            selller.setRole(Role.ROLE_ADMIN);
            sellerRepo.save(selller);
        }
    }

    //Find All Products Details...

    public List<ProductsDto> allProducts(){
        List<Products> products = this.productsRepo.findAll();
        List<ProductsDto> productsDtoList = products.stream().map(p-> this.modelMapper.map(p,ProductsDto.class)).toList();

        return productsDtoList;
    }

}
