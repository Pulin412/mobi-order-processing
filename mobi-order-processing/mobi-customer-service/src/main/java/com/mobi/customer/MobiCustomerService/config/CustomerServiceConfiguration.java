package com.mobi.customer.MobiCustomerService.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class CustomerServiceConfiguration {

  @Bean
  public static ModelMapper modelMapper() {

    return new ModelMapper();
  }
}
