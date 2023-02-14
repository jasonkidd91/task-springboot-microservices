package com.wcbeh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SimpleCrudServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleCrudServiceApplication.class, args);
	}

}
