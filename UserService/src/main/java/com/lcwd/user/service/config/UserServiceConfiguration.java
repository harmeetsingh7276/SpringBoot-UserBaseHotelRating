package com.lcwd.user.service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class UserServiceConfiguration {
    @Bean
    @LoadBalanced//uses service names instead of ip address
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
