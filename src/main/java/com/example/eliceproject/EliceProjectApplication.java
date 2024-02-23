package com.example.eliceproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EliceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EliceProjectApplication.class, args);
	}

}
