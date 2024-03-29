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
			.antMatchers("/swagger-ui/**").authenticated()
			.antMatchers("/student/**").authenticated()
			.antMatchers("/plan/**").authenticated()
			.antMatchers("/teacher/**").authenticated()
			.antMatchers("/daily-accounting/**").authenticated()
			.antMatchers("oauth/token").permitAll()
			.antMatchers("/oauth/token").permitAll()
			.antMatchers("/oauth/token").permitAll()
			.antMatchers("http://localhost:4200/#/").permitAll()
			.antMatchers("http://localhost:4200/").permitAll()
			.antMatchers("http://localhost:4200").permitAll()
			.antMatchers("localhost:8080").permitAll()						
			.and().cors().and().csrf().disable();
	
	}
	
}
