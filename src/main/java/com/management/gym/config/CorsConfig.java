package com.management.gym.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig extends CorsFilter{

	
	private static final long serialVersionUID = 1L;
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				CorsRegistration cors = registry.addMapping("https://gym-management2-6135.vercel.app/");
				cors.allowedMethods("*");
				cors.allowedOrigins("https://gym-management2-6135.vercel.app/api/", "https://gym-management2-6135.vercel.app/api", "https://gym-management2-6135.vercel.app");
				cors.allowedHeaders("*");
				cors.allowCredentials(false);
				cors.maxAge(3600);
			}
		};
	}
}
