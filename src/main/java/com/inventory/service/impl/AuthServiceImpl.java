package com.inventory.service.impl;

import com.inventory.dto.SellerDto;
import com.inventory.dto.frontendreq.SignUpDto;
import com.inventory.enums.Role;
import com.inventory.exception.GenericException;
import com.inventory.model.Seller;
import com.inventory.repository.SellerRepo;
import com.inventory.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private SellerRepo sellerRepo;


    //Find Seller...
    @Override
    public SellerDto findSeller(String email){
        Seller seller;
        try {
            seller = this.sellerRepo.findByEmail(email).get();
        }catch (NoSuchElementException e){
            throw new GenericException("User Not Exits.");
        }
        SellerDto sellerDto = this.modelMapper.map(seller,SellerDto.class);
        sellerDto.setAdminId(seller.getId());

        return sellerDto;
    }

    //Save Seller..
    @Override
    public void saveSeller(SignUpDto signUpDto){

        if(this.sellerRepo.findByEmail(signUpDto.getEmail()).isPresent()){
            throw new GenericException("User already exit.");
        }else {
            Seller seller = this.modelMapper.map(signUpDto,Seller.class);
            seller.setPassword(this.passwordEncoder.encode(seller.getPassword()));
            seller.setActive(true);
            seller.setCreatedAt(new Date());
//            seller.setPhoneNumber("7735725548");
            seller.setUpdatedAt(new Date());
            seller.setRole(Role.ROLE_ADMIN);
            sellerRepo.save(seller);
        }
    }



}
