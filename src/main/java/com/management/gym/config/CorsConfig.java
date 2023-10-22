package com.management.gym.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
public class CorsConfig {
	
	
	@Value("${personal.security.url-cors}")
    private List<String> URLS_FROTEND;
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        System.out.println(this.URLS_FROTEND);
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(this.URLS_FROTEND);
        configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
        //configuration.setExposedHeaders(List.of("x-auth-token"));
	final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				CorsRegistration cors = registry.addMapping("https://gym-management2-fjmn.vercel.app");				
//				cors.allowedMethods("*");
//				cors.allowedOrigins("*");
//				cors.allowedHeaders("*");
//				cors.allowCredentials(false);
//				cors.maxAge(3600);
//			}
//		};
//	}
}
