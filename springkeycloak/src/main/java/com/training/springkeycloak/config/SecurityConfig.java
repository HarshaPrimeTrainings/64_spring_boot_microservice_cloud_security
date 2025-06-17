package com.training.springkeycloak.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	
	@Autowired
	JwtAuthConvertor jwtAuthConvertor;
	
	@Bean
	public SecurityFilterChain initSecurityFilerChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(authorize->authorize.requestMatchers("/save/**").hasAnyAuthority("admin")
				.requestMatchers("/products/**").hasAnyAuthority("user")
				.anyRequest().authenticated())
		.oauth2ResourceServer(oAuth->oAuth.jwt(jwt->jwt.jwtAuthenticationConverter(jwtAuthConvertor)))
		.csrf(csrf->csrf.disable());
		return http.build();
	}
	
}
