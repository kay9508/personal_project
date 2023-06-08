package com.sy.personal_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Personal_projectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Personal_projectApplication.class, args);
	}

}
