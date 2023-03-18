package com.demo.redegal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RedegalApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedegalApplication.class, args);
	}

}
