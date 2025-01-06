package com.team5.campscore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class CampscoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampscoreApplication.class, args);
	}

}
