package com.sf.foodtruck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAutoConfiguration
@EnableAsync
@ComponentScan
@Configuration
public class FoodTruckApp {

    public static void main(final String[] args) throws Exception {
        SpringApplication.run(FoodTruckApp.class, args);
    }
}
