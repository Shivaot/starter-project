package com.starter.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

/**
 * Shiva Created on 09/04/23
 */
@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
