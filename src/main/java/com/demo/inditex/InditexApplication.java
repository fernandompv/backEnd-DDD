package com.demo.inditex;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;


@SpringBootApplication
@EnableR2dbcRepositories
@OpenAPIDefinition(info = @Info(
		title = "Spring WebFlux CRUD Example",
		version = "1.0",
		description = "Spring WebFlux CRUD Example Sample documents"
))
public class InditexApplication {

	public static void main(String[] args) {SpringApplication.run(InditexApplication.class, args);}

}
