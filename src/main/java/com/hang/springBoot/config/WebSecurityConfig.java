package com.hang.springBoot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hang.springBoot.component.JwtRequestFilter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
//        .csrf().disable();

		// Tất cả các request gửi tới Web Server yêu cầu phải được xác thực
		// (authenticated).
//		http.authorizeRequests().antMatchers("/users/**").permitAll();
	}

//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auBuilder) throws Exception {
//		List<com.hang.springBoot.models.User> users = repository.findAll();
//		InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> mngConfig = auBuilder
//				.inMemoryAuthentication();
//
//		for (com.hang.springBoot.models.User u : users) {
//			String password = u.getPassword();
//			String encryptedPassword = bCryptPasswordEncoder().encode(password);
//
//			UserDetails user = User.withUsername(u.getName()).password(encryptedPassword).roles("USER").build();
//			mngConfig.withUser(user);
//		}
//	}
}
