package com.example.hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HibernateApplication {

	public static void main(String[] args) {

		Singleton instance = Singleton.getInstance();
		SpringApplication.run(HibernateApplication.class, args);
	}

}
