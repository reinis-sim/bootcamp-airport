package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/airport/showAll/").permitAll()
		.antMatchers("/airport/showAll/**/**").permitAll()
		.antMatchers("/airport/insert/**/**").hasRole("ADMIN")
		.antMatchers("airport/delete/**/**").hasRole("ADMIN")
		
		.antMatchers("/boardingPass/**/**").hasRole("ADMIN")
		
		.antMatchers("/flight/showAll").permitAll()
		
		.antMatchers("/location/**").hasRole("ADMIN")
		
		.antMatchers("/luggage/**").authenticated()
		
		.antMatchers("/user/**").authenticated()
		
		//.antMatchers("/").permitAll()
		.and().formLogin();
		/*
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user").hasAnyRole("ADMIN","USER")
			.antMatchers("/").permitAll()
			.antMatchers("/h2-console/**").permitAll()
			.and().formLogin();
		*/
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
	}
	 
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
