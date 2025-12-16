package com.gym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.gym"})
public class MembersApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MembersApiApplication.class, args);
    }
}
