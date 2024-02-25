package org.edupoll.app.config;

import javax.sql.DataSource;

import org.edupoll.app.config.support.CustomUserDetailsService;
import org.edupoll.app.repository.AccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration

public class SecurityConfig {// movie bell

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(t -> t.dispatcherTypeMatchers(DispatcherType.ERROR).permitAll() //
				.requestMatchers("/static/**").permitAll().anyRequest().permitAll());
		http.csrf(t -> t.disable());
		http.formLogin(t -> t.loginPage("/auth/login").permitAll());
		http.anonymous(t -> t.disable());


		return http.build();
	}

	@Bean
	public UserDetailsService jpaUsers(AccountRepository accountRepository) {

		return new CustomUserDetailsService(accountRepository);

	}


}
