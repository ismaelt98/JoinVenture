package com.joinventure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@SuppressWarnings({ "deprecation", "removal" })
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
        .csrf().disable()
	        .authorizeRequests(authorizeRequests ->
	            authorizeRequests
	                .requestMatchers("/**", "/google").permitAll()
	                .anyRequest().authenticated()
	        )
	        .oauth2Login().defaultSuccessUrl("/google/callback")
	        .and()
	        .oauth2Client(Customizer.withDefaults());
	    
	    return http.build();
	}
}