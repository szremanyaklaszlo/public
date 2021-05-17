package com.training.sportsbetting.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.training.sportsbetting.service.config.ServiceConfig;

@Configuration
@Import(value = ServiceConfig.class)
public class ApiConfig {

}
