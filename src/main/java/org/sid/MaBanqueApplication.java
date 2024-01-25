package org.sid;

import org.sid.dao.IClientRepository;
import org.sid.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.AllArgsConstructor;




@SpringBootApplication
public class MaBanqueApplication implements CommandLineRunner  {
	
	@Autowired
	private IClientRepository iClientRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MaBanqueApplication.class, args);	
	
	}

	@Override
	public void run(String... args) throws Exception {
		
		iClientRepository.save(new Client("hsini", "gmail.com"));
		iClientRepository.save(new Client(null,"karin", "karimgmail.com",null));
		iClientRepository.save(new Client("baba", "babagmail.com"));

		iClientRepository.findAll().forEach(c->{ System.out.println(c.getNom()+" "+c.getEmail()); });
		
	}

	
}
