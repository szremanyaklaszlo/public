package com.training.sportsbetting.service;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

import com.training.sportsbetting.service.config.ServiceConfig;

@SpringBootConfiguration
@EnableAutoConfiguration
@PropertySource(value={"classpath:test.properties"})
public class ServiceTestCofig extends ServiceConfig{

}
