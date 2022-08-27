package com.ftn.Chickito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ChickitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChickitoApplication.class, args);
	}

}
