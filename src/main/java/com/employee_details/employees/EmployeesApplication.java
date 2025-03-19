package com.employee_details.employees;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeesApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);
	}
	@PostConstruct
	public void logMessage() {
		System.out.println("Server listening on port 8080");
	}
}
