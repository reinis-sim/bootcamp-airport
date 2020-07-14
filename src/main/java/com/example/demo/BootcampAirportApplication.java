package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.repos.IUserRepo;

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = IUserRepo.class)
public class BootcampAirportApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootcampAirportApplication.class, args);
	}

}
