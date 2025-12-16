package com.pulse.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class PulseEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PulseEcommerceApplication.class, args);
	}

}
