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
		
		Client c1= iClientRepository.save(new Client("hsini", "gmail.com"));
		Client c2= iClientRepository.save(new Client(null,"karin", "karimgmail.com",null));
		Client c3= iClientRepository.save(new Client("baba", "babagmail.com"));

		iClientRepository.findAll().forEach(c->{ System.out.println(c.getNom()+" "+c.getEmail()); });
		
		Compte cp1 = iCompteRepository.save(new CompteCourant("c1",new Date(), 2345, c1, null, 345));
		Compte cp2 = iCompteRepository.save(new CompteCourant("c2",new Date(), 2345, c3, null, 600)) ;
		Compte cp3 = iCompteRepository.save(new CompteEpargne("c3", new Date(), 1000, c2, null, 2.5));
		
		iOperationRespository.save(new Versement(null, new Date(), 500, cp1));
		iOperationRespository.save(new Versement(null, new Date(), 1000, cp1));
		iOperationRespository.save(new Versement(null, new Date(), 2000, cp1));
		iOperationRespository.save(new Versement(null, new Date(), 14000, cp2));
		iOperationRespository.save(new Versement(null, new Date(), 7000, cp2));
		iOperationRespository.save(new Versement(null, new Date(), 500, cp2));
		iOperationRespository.save(new Versement(null, new Date(), 400, cp3));
		iOperationRespository.save(new Versement(null, new Date(), 500, cp3));
		iOperationRespository.save(new Versement(null, new Date(), 1500, cp3));
		iOperationRespository.save(new Versement(null, new Date(), 4500, cp3));

		iOperationRespository.save(new Retrait(null, new Date(), 500, cp1));
		iOperationRespository.save(new Retrait(null, new Date(), 500, cp2));
		iOperationRespository.save(new Retrait(null, new Date(), 500, cp3));
		iOperationRespository.save(new Retrait(null, new Date(), 500, cp3));
		iOperationRespository.save(new Retrait(null, new Date(), 500, cp2));
		
		iBanqueMetier.verser("c1", 567890);
		iBanqueMetier.virement("c1", "c2", 12345);

	}

	
}
