package com.example.simplebooks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.simplebooks.util.BCryptEncoder;

@SpringBootApplication
@EnableJpaAuditing
public class BookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
		
		System.out.println("john: " + new BCryptEncoder().getBcryptHash("john"));
	}
}
