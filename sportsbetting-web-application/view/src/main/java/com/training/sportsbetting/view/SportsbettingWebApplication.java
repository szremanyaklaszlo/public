package com.training.sportsbetting.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;

import com.training.sportsbetting.service.result.ResultService;

@SpringBootApplication(scanBasePackages = "com.training.sportsbetting.view")
public class SportsbettingWebApplication {

    @Autowired
    @Qualifier("RandomResultService")
    private ResultService resultService;

    public static void main(String[] args) {
        SpringApplication.run(SportsbettingWebApplication.class, args);
    }

    @Bean
    public CommandLineRunner schedulingRunner(TaskExecutor executor) {
        return new CommandLineRunner() {
            public void run(String... args) throws Exception {
                executor.execute(resultService);
            }
        };
    }

}
