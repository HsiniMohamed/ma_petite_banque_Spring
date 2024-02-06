package org.sid.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		
		//utlisation de formulaire d'authentification
		httpSecurity.formLogin().loginPage("/login").defaultSuccessUrl("/",true).permitAll();
		
		httpSecurity.rememberMe();

		//permission d'accées au webjars "bootstrap".....
		httpSecurity.authorizeHttpRequests().requestMatchers("/webjars/**").permitAll();

		//gestions des roles et droit d'accés ou Avec les annotations
		
		httpSecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
		httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");

		//toutes les requetes depend une authentification
		httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
				
		//Redirection dans le cas non autorisé
		httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
		return httpSecurity.build();
	}
	
}
