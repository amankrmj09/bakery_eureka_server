package com.blubugtech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BakeryEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BakeryEurekaServerApplication.class, args);
    }

}
