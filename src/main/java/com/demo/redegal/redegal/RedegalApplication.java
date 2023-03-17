package com.demo.redegal.redegal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RedegalApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedegalApplication.class, args);
	}

}
