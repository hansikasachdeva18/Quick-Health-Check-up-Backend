package com.silverlining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={ "com.silverlining.*" })
public class DoctorBotApplication {
    public static void main(String[] args) {
        SpringApplication.run(DoctorBotApplication.class, args);
    }
}
