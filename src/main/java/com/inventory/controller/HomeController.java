package com.inventory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/IMS")
public class HomeController {
    @PostMapping(path = "/signup")
    public ResponseEntity<String> register(@RequestBody String msg){
        return ResponseEntity.ok(msg);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody String msg){
        return new ResponseEntity<>("This is Login page" + msg, HttpStatus.CREATED);
    }
}
