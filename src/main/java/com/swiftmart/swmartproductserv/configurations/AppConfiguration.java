package com.swiftmart.swmartproductserv.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {
    @Bean //These spring objects are to be managed by the spring container
    public RestTemplate createRestTemplate(){
        return new RestTemplate();
    }
}