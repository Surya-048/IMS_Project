package com.inventory.controller;

import com.inventory.dto.frontendreq.LoginReqDto;
import com.inventory.dto.frontendreq.LoginResponseDto;
import com.inventory.dto.SellerDto;
import com.inventory.dto.frontendreq.SignUpDto;
import com.inventory.exception.GenericException;
import com.inventory.security.JwtService;
import com.inventory.security.userdetails.CustomUserDetails;
import com.inventory.service.AuthService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/IMS")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthService applicationService;

    @PostMapping(path = "/signup")
    public ResponseEntity<SignUpDto> register(@RequestBody SignUpDto signUpDto){

        this.applicationService.saveSeller(signUpDto);

        return new ResponseEntity(signUpDto, HttpStatus.CREATED);
    }


    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginReqDto loginDto){
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();

        System.out.println(loginDto);
        SellerDto sellerDto = this.applicationService.findSeller(email);
        System.out.println(sellerDto);

        String jwtToken = null;
        if(passwordEncoder.matches(password,sellerDto.getPassword()) ){
            jwtToken = jwtService.generateToken(this.modelMapper.map(sellerDto, CustomUserDetails.class));
        }else {
            throw new GenericException("Password Doesn't match");
        }
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setJwtToken(jwtToken);
        SellerDto sellerDto1 = this.applicationService.findSeller(email);
        loginResponseDto.setAdminId(sellerDto1.getAdminId());
        loginResponseDto.setUserName(sellerDto1.getUserName());
        loginResponseDto.setEmail(sellerDto1.getEmail());

        return new ResponseEntity(loginResponseDto, HttpStatus.OK);
    }
}
