package com.training.springsecurityOauth2.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.training.springsecurityOauth2.security.AuthEntryPointJwt;
import com.training.springsecurityOauth2.security.AuthTokenFilter;
import com.training.springsecurityOauth2.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserWebSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	AuthEntryPointJwt authentrypoint;
	
	@Autowired
	UserDetailsServiceImpl userdetialsServiceimpl;
	
	@Bean
	public PasswordEncoder initPaswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthTokenFilter authinticateJwtFIlter() {
		return new AuthTokenFilter();
	} 
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
					http
					.headers().frameOptions()
					.sameOrigin().and()
					.cors().and().csrf().disable()
					.exceptionHandling().authenticationEntryPoint(authentrypoint)
					.and()
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
					.and()
					.authorizeRequests()
					.antMatchers("/signin/**","/login/**").permitAll()
					.antMatchers("/users/**").permitAll()
					.anyRequest().authenticated();
	}
	
	
	@Override
	public void configure(AuthenticationManagerBuilder authmanagerBuilder) throws Exception {
		authmanagerBuilder.userDetailsService(userdetialsServiceimpl).passwordEncoder(initPaswordEncoder());
		
	}
}
