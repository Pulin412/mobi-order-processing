package com.mobi.inventory.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

/**
 * The type Configuration.
 */
@org.springframework.context.annotation.Configuration
public class Configuration {

    /**
     * Model mapper model mapper.
     *
     * @return the model mapper
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
