package com.tutorial.microservices.order;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableJpaRepositories("com.tutorial.microservices.order")
@ComponentScan(basePackages = "com.tutorial.microservices.order")
@PropertySource(value={"classpath:test.properties"})
public class TestConfig {

}
