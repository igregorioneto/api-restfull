package br.com.greg.apirestfull.apirestfull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class ApiRestfullApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestfullApplication.class, args);
	}

}
