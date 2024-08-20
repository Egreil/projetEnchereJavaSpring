package fr.eni.enchere.ihm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.eni.enchere.bll.UtilisateurService;




@Controller
@RequestMapping("/securites")
public class SecuriteController {
	
	public UtilisateurService service;

	@GetMapping("/")
	public String accueil(Model model) {
		model.addAttribute("utilisateurs",service.findAll());
		return "accueil";
	}
	@GetMapping("/liste")
	public String afficheliste() {
		return "afficheListe";
	}
	@GetMapping("/admin")
	public String afficheadmin() {
		return "afficheAdmin";
	}
	
}

