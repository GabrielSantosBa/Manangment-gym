package com.management.gym.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	
	@Override
	public void configure(HttpSecurity http) throws Exception {

		http
			.authorizeRequests()
			.antMatchers("/student/**").permitAll()
			.antMatchers("/swagger-ui/**").permitAll()
			.antMatchers("/manangment-gym-production.up.railway.app/**").permitAll()
			.antMatchers("/manangment-gym-production.up.railway.app/swagger-ui/**").permitAll()
			.antMatchers("/manangment-gym-production.up.railway.app/student/**").permitAll();
	
	}
	
}