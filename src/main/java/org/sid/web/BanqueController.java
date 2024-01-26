package org.sid.web;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.metier.IBanqueMetier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class BanqueController {

	private IBanqueMetier iBanqueMetier;
	
	@GetMapping("/operations")
	public String index() {
		return "compte";
	}
	
	@GetMapping("/consulterCompte")
	public String consulter(Model model, String codeCpte) {
		model.addAttribute("codeCpte",codeCpte);
		try {
			Compte cp = iBanqueMetier.consulterCompte(codeCpte);
			Page<Operation> pageOperations = iBanqueMetier.listOperationsCompte(codeCpte, 0, 4);
			model.addAttribute("compte",cp);
			model.addAttribute("listeOperations",pageOperations.getContent());
			
		} catch (Exception e) {
			model.addAttribute("exception",e);
		}
		
		return "compte";
	}
}
