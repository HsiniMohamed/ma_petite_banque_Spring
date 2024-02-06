package org.sid;




import java.util.Date;

import org.sid.dao.IClientRepository;
import org.sid.dao.ICompteRepository;
import org.sid.dao.IOperationRespository;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.CompteEpargne;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.sid.metier.IBanqueMetier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import lombok.AllArgsConstructor;



@AllArgsConstructor
@SpringBootApplication
public class MaBanqueApplication implements CommandLineRunner  {
	
	
	private IClientRepository iClientRepository;
	private ICompteRepository iCompteRepository;
	private IOperationRespository iOperationRespository;
	private IBanqueMetier iBanqueMetier;
	
	public static void main(String[] args) {
		SpringApplication.run(MaBanqueApplication.class, args);	
	
	}

	@Override
	public void run(String... args) throws Exception {
		
		

	}
	
		
	@Bean
	CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {
		 PasswordEncoder passwordEncoder=passwordEncoder();
		return args ->{
			UserDetails u1= jdbcUserDetailsManager.loadUserByUsername("user11");
			if (u1==null) 		
			jdbcUserDetailsManager.createUser(
					 User.withUsername("user11").password(passwordEncoder.encode("1234")).roles("USER").build()
					 );
			UserDetails u2= jdbcUserDetailsManager.loadUserByUsername("user22");
			if (u2==null) 
			jdbcUserDetailsManager.createUser(
					 User.withUsername("user22").password(passwordEncoder.encode("1234")).roles("USER").build()
					 );
			UserDetails u3= jdbcUserDetailsManager.loadUserByUsername("admin2");
			if (u3==null) 
			jdbcUserDetailsManager.createUser(
					 User.withUsername("admin2").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
					 );
		};
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
