package com.inventory.controller;

import com.inventory.dto.LoginDto;
import com.inventory.dto.LoginResponseDto;
import com.inventory.dto.SellerDto;
import com.inventory.dto.SignUpDto;
import com.inventory.exception.GenericException;
import com.inventory.repository.SellerRepo;
import com.inventory.security.JwtService;
import com.inventory.security.userdetails.CustomUserDetails;
import com.inventory.service.ApplicationService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/IMS")
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationService applicationService;

    @PostMapping(path = "/signup")
    public ResponseEntity<SignUpDto> register(@RequestBody SignUpDto signUpDto){

        applicationService.saveSeller(signUpDto);

        return new ResponseEntity(signUpDto, HttpStatus.CREATED);
    }


    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto){
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();

        System.out.println(loginDto);
        SellerDto sellerDto = this.applicationService.findSeller(email);
        System.out.println(sellerDto);

        String jwtToken = null;
        if(sellerDto != null && passwordEncoder.matches(password,sellerDto.getPassword()) ){
            jwtToken = jwtService.generateToken(this.modelMapper.map(sellerDto, CustomUserDetails.class));
        }else {
            throw new GenericException("User Not Found or Password Doesn't match");
        }
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setToken(jwtToken);
        loginResponseDto.setName(sellerDto.getUserName());

        return new ResponseEntity(loginResponseDto, HttpStatus.OK);
    }
}
