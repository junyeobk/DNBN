package com.dnbn.back.security;

import static org.springframework.security.config.Customizer.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.dnbn.back.security.auth.MemberDetailsService;

import jakarta.servlet.DispatcherType;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.builder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
			.authorizeHttpRequests(request -> request
				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				// .requestMatchers("/login").permitAll()
				// .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
				.anyRequest().permitAll()
			)
			.formLogin(login -> login	// form 방식 로그인 사용
				.loginPage("/login")
				// .loginProcessingUrl("/login-process")
				.usernameParameter("userId")
				.passwordParameter("userPw")
				// .defaultSuccessUrl("/view/dashboard", true)	// 성공 시 dashboard로
				.permitAll()	// 대시보드 이동이 막히면 안되므로 얘는 허용
			)
			.logout(withDefaults()	// 로그아웃은 기본설정으로 (/logout으로 인증해제)
			)
			.csrf((csrf) -> csrf.disable()
			)
			.cors((cors) -> cors.disable());

        return http.build();
    }

	// @Bean
	// public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	// 	return http
	// 		.authorizeHttpRequests((requests) -> requests
	// 			.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
	// 			// .requestMatchers("/login", "/signup", "/user").permitAll()
	// 			.anyRequest().authenticated() // 어떤 요청이라도 인증 필요
	// 		)
	// 		.logout(Customizer.withDefaults() // "/logout" 으로 인증 해제
	// 			// (logout) -> logout
	// 			// .logoutSuccessUrl("/login")
	// 			// .invalidateHttpSession(true)
	// 		)
	// 		.csrf((csrf) -> csrf.disable()
	// 		)
	// 		.cors((cors) -> cors.disable())
	// 		.build();
	// }

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
