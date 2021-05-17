package com.training.sportsbetting.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.training.sportsbetting.api")
public class SportsbettingRestApi {

    public static void main(String[] args) {
        SpringApplication.run(SportsbettingRestApi.class, args);
    }

}
