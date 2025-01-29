package com.karmunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.karmunity.repositories")
//@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class KarmunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(KarmunityApplication.class, args);
	}
}