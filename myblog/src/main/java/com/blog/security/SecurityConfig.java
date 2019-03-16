package com.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static String KEY = "yellow";
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	UserDetailsService userDetailsService;
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider  = new DaoAuthenticationProvider();
		 authenticationProvider.setUserDetailsService(userDetailsService);
		 authenticationProvider.setPasswordEncoder(passwordEncoder);
		return  authenticationProvider;
	}	
	@Autowired
	protected void configureGlobel(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
		
	}
	
	
	@Override
/**
 * 自定义配置
 */
	protected void configure(HttpSecurity http) throws Exception {
	// TODO Auto-generated method stub

	http
	.authorizeRequests()
	.antMatchers("/css/**","/js/**","/fonts/**","/index")
	.permitAll()
	.antMatchers("/admin/**").hasRole("ADMIN")
	.and()
	.formLogin()
	.loginPage("/login").failureUrl("/login_error").defaultSuccessUrl("/homepage")
	.and()
	.logout()
	.and()
	.rememberMe()
	.and()
	.exceptionHandling().accessDeniedPage("/403");
	http.csrf().disable();
	http.headers().frameOptions().sameOrigin();
}

	
}
