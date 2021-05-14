package com.training.sportsbetting.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.training.sportsbetting.domain.config.DomainConfig;

@Configuration
@EnableAspectJAutoProxy
@EnableJpaRepositories("com.training.sportsbetting.service")
@ComponentScan(basePackages = "com.training.sportsbetting.service")
@Import(value = DomainConfig.class)
public class ServiceConfig {

}
