package com.inventory.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class ApplicationConfiguration {
    @Bean
    public ModelMapper getModelMapper(){
        return  new ModelMapper();
    }

}