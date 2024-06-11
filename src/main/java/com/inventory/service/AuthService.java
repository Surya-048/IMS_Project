package com.inventory.service;

import com.inventory.dto.SellerDto;
import com.inventory.dto.frontendreq.SignUpDto;

public interface AuthService {
    //Find Seller...
    SellerDto findSeller(String email);

    //Save Seller..
    void saveSeller(SignUpDto signUpDto);
}
