package com.dnbn.back.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
			.authorizeHttpRequests((requests) -> requests
				// .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				.requestMatchers("/login", "/signup", "/user").authenticated()
				.anyRequest().permitAll() // 어떤 요청이라도 인증 필요
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.usernameParameter("userId")
				.passwordParameter("userPw")
				.loginProcessingUrl("/login-process")
				.defaultSuccessUrl("/", true)
				.permitAll()
			)
			.logout(Customizer.withDefaults() // "/logout" 으로 인증 해제
				// (logout) -> logout
				// .logoutSuccessUrl("/login")
				// .invalidateHttpSession(true)
			)
			.csrf((csrf) -> csrf.disable()
			)
			.cors((cors) -> cors.disable())
			.build();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
