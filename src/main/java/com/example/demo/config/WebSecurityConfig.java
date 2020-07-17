package com.example.demo.config;

import javax.annotation.security.PermitAll;

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
		.antMatchers("/").permitAll()
		.antMatchers("/flight/showAll").permitAll()
		.antMatchers("/flight/showAll/**").permitAll()
		.antMatchers("/airport/showAll").permitAll()
		.antMatchers("/airport/showAll/**").permitAll()
		.antMatchers("/user/register/").permitAll()
		//.antMatchers("/user/login").permitAll()
		.antMatchers("/user/bookings").hasAnyRole("USER","ADMIN")
		.antMatchers("/boardingPass/showAllBoardingPass").hasAnyRole("USER","ADMIN")
		.antMatchers("/boardingPass/showAllBoardingPass/**").hasAnyRole("USER","ADMIN")
		.antMatchers("/boardingPass/**/checkin").hasAnyRole("USER","ADMIN")
		.antMatchers("/boardingPass/showOneBoardingPass/**/email").hasAnyRole("USER","ADMIN")
		.antMatchers("/user/profile").hasAnyRole("USER","ADMIN")
		.antMatchers("/boardingPass/showAllBoardingPass/").hasAnyRole("USER","ADMIN")
		.antMatchers("/luggage/showAllLuggage").hasAnyRole("USER","ADMIN")
		.antMatchers("/luggage/showOneLuggage/**").hasAnyRole("USER","ADMIN")
		.antMatchers("/flight/**/book").hasAnyRole("USER","ADMIN")
		.antMatchers("/luggage/showOneLuggage/**/update").hasAnyRole("USER","ADMIN")
		.antMatchers("/luggage/showOneLuggage/**/delete").hasAnyRole("USER","ADMIN")
		.antMatchers("/location/showAll").hasRole("ADMIN")
		.antMatchers("/location/showAll/**").hasRole("ADMIN")
		.antMatchers("/location/update/**").hasRole("ADMIN")
		.antMatchers("/location/delete/**").hasRole("ADMIN")
		.antMatchers("/location/insert").hasRole("ADMIN")
		.antMatchers("/location/delete/**").hasRole("ADMIN")
		.antMatchers("/flight/**/delete").hasRole("ADMIN")
		.antMatchers("/flight/update/**").hasRole("ADMIN")
		.antMatchers("/flight/insert").hasRole("ADMIN")
		.antMatchers("/boardingPass/deleteBoardingPass/**").hasRole("ADMIN")
		.antMatchers("/airport/delete/**").hasRole("ADMIN")
		.antMatchers("/airport/update/**").hasRole("ADMIN")
		.antMatchers("/airport/insert").hasRole("ADMIN");
		//.and().formLogin().loginPage("/user/login").permitAll();
		//.antMatchers("/**").permitAll()
//		.antMatchers("/airport/showAll/").permitAll()
//		.antMatchers("/airport/showAll/**/**").permitAll()
//		.antMatchers("/airport/insert/**/**").hasRole("ADMIN")
//		.antMatchers("airport/delete/**/**").hasRole("ADMIN")
//		
//		.antMatchers("/boardingPass/**/**").hasRole("ADMIN")
//		
//		.antMatchers("/flight/showAll").permitAll()
//		
//		.antMatchers("/location/**").hasRole("ADMIN")
//		
//		.antMatchers("/luggage/**").authenticated()
//		
//		.antMatchers("/user/register/**").permitAll()
//		.antMatchers("/user/**").authenticated()
//		.and().formLogin();
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
	}
	 
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
