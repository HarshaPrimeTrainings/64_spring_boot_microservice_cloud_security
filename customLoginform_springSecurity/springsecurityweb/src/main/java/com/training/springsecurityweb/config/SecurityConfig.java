package com.training.springsecurityweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	PasswordEncoder intiEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain initSecurityFilterCahin(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth->auth.requestMatchers("/home","/save","/error")
				.permitAll()
				.requestMatchers("/admin")
				.hasRole("ADMIN")
				.anyRequest().authenticated())
		.formLogin(formlogin->formlogin.loginPage("/login").permitAll())
		.logout(logout->logout.permitAll())
		.csrf(csrf->csrf.disable());
		return http.build();
		
	}

}
