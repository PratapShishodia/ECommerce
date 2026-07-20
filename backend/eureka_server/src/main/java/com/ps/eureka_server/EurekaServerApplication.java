package com.ps.eureka_server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

	public static void main(String[] args) {
		System.out.println(System.getenv("APPLICATION_PORT"));
		SpringApplication.run(EurekaServerApplication.class, args);
		log.info("Eureka Server Started");
	}

	@Bean
	CommandLineRunner runner(Environment env) {
		return args -> {
			System.out.println("APPLICATION_PORT = " + env.getProperty("APPLICATION_PORT"));
			System.out.println("server.port      = " + env.getProperty("server.port"));
		};
	}

}
