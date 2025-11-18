package com.gym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MembersApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MembersApiApplication.class, args);
    }
}
package com.gym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.gym.repository")
@EntityScan(basePackages = "com.gym.entity")
public class MembersApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MembersApiApplication.class, args);
    }
}
