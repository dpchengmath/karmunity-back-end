package com.karmunity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.karmunity.repositories")
public class KarmunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(KarmunityApplication.class, args);
	}

}