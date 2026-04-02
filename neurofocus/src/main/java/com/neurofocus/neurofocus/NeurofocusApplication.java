package com.neurofocus.neurofocus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.neurofocus.neurofocus")
public class NeurofocusApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeurofocusApplication.class, args);
	}
}