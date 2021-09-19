package com.spaceXinsights.restservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static com.spaceXinsights.restservice.utils.Constants.*;

@SpringBootApplication
public class RestServiceApplication {
    public static void main(String[] args) {
        System.out.printf("%sREST application started.%n", INFO);
        SpringApplicationBuilder builder = new SpringApplicationBuilder(RestServiceApplication.class);
        builder.headless(false);
        builder.run(args);
    }
}
