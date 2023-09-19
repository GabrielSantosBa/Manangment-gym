package com.management.gym.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class CorsConfig{

	@Bean
    public OpenAPI customOpenAPI() {
		return new OpenAPI().addServersItem(new Server().url("http://manangment-gym-production.up.railway.app"))
				.addServersItem(new Server().url("http://localhost:8080"));
        
    }
}
