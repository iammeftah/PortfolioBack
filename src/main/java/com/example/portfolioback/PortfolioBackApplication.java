package com.example.portfolioback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:.env")

public class PortfolioBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortfolioBackApplication.class, args);
    }

}
