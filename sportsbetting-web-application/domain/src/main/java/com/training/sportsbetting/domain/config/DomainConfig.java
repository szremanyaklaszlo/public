package com.training.sportsbetting.domain.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "com.training.sportsbetting.domain")
@ComponentScan(basePackages = "com.training.sportsbetting.domain")
public class DomainConfig {

}
