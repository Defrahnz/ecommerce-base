package com.ecommerce.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class EcommerceGuitarcenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceGuitarcenterApplication.class, args);
	}

}
