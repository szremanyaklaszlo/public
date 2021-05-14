package com.tutorial.microservices.product.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
@RefreshScope
public class TestController {

    @Value("${spring.data.mongodb.uri}")
    private String mongoURL;

    @GetMapping
    public String getUrl() {
        return mongoURL;
    }
}
