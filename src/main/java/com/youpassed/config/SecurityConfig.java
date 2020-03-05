package com.youpassed.config;

import com.youpassed.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/admin").hasRole(User.Role.ADMIN.toString())
				.antMatchers("/student").hasRole(User.Role.STUDENT.toString())
				.antMatchers("/", "/login", "/register", "/static/**").permitAll()
//				.anyRequest().authenticated()
				.and()
			.formLogin().loginPage("/").loginProcessingUrl("/login").permitAll()
				.and()
			.logout().permitAll();
	}


}
