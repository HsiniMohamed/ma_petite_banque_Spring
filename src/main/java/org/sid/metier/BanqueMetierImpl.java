package org.sid.metier;

import java.util.Date;

import org.sid.dao.ICompteRepository;
import org.sid.dao.IOperationRespository;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.Operation;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.sid.exceptions.MyCustomRuntimeException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;



@Service
@Transactional
@AllArgsConstructor
public class BanqueMetierImpl implements IBanqueMetier {
 
	private ICompteRepository compteRepository;
	private IOperationRespository operationRespository;
	 
	@Override
	public Compte consulterCompte(String codeCpte) {
		Compte cpte= compteRepository.findOneByCodeCompte(codeCpte);
		if(cpte ==null)throw new MyCustomRuntimeException("Compte introuvable");
		return cpte;
	}

	@Override
	public void verser(String codeCpte, double montant) {
		Compte cpte = consulterCompte(codeCpte);
		Versement versement = new Versement(null, new Date(), montant, cpte);
		operationRespository.save(versement);
		cpte.setSolde(cpte.getSolde()+montant);
		compteRepository.save(cpte);
		
		
	}

	@Override
	public void retirer(String codeCpte, double montant) {
		Compte cpte = consulterCompte(codeCpte);
		double faciliteCaisse=0;
		if(cpte instanceof CompteCourant)
			faciliteCaisse=((CompteCourant) cpte).getDecouvert();
		if(cpte.getSolde()+faciliteCaisse<montant)
			throw new MyCustomRuntimeException("Solde insuffisant");
		Retrait retrait = new Retrait(null, new Date(), montant, cpte);
		operationRespository.save(retrait);
		cpte.setSolde(cpte.getSolde()- montant);
		compteRepository.save(cpte);
		
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
		if(codeCpte1.equals(codeCpte2))		
			throw new MyCustomRuntimeException("Impossible d'effectuer un virement sur le méme compte!");
		retirer(codeCpte1, montant);
		verser(codeCpte2, montant);
	}

	@Override
	public Page<Operation> listOperationsCompte(String codeCpte, int page,int size) {
		
		return operationRespository.listOperationCompte(codeCpte, PageRequest.of(page, size));
	}

}
