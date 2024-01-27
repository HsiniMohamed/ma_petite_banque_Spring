package org.sid.web;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.metier.IBanqueMetier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String consulter(Model model, 
			@RequestParam(name = "codeCpte", defaultValue = "")String codeCpte,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		model.addAttribute("codeCpte", codeCpte);
		
		try {
			Compte cp = iBanqueMetier.consulterCompte(codeCpte);
			Page<Operation> pageOperations = iBanqueMetier.listOperationsCompte(codeCpte, page, size);
			model.addAttribute("compte", cp);
			model.addAttribute("pageOperations", pageOperations);
			model.addAttribute("currentPage",page);
			model.addAttribute("size",size);
			model.addAttribute("pages",new int[pageOperations.getTotalPages()]);


		} catch (Exception e) {
			model.addAttribute("exception", e);
		}

		return "compte";
	}

	@PostMapping("/saveOperation")
	public String saveOperation(Model model, String typeOperation, String codeCpte, double montant, String codeCpte2) {

		try {
			if (typeOperation.equals("VERS")) {
				iBanqueMetier.verser(codeCpte, montant);

			} else if (typeOperation.equals("RETR")) {
				iBanqueMetier.retirer(codeCpte, montant);
			} else if (typeOperation.equals("VIR")) {
				iBanqueMetier.virement(codeCpte, codeCpte2, montant);
			}
		} catch (Exception e) {
			model.addAttribute("error", e);
			return "redirect:/consulterCompte?codeCpte="+codeCpte+"&error="+e.getMessage();

		}

		return "redirect:/consulterCompte?codeCpte="+codeCpte;

	}
}
