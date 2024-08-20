package fr.eni.enchere.ihm;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.ModelAndViewMethodReturnValueHandler;

import fr.eni.enchere.bll.UtilisateurService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class LoginController {

	UtilisateurService utilisateurService;
	
	
	public LoginController(UtilisateurService utilisateurService) {
		super();
		this.utilisateurService = utilisateurService;
	}

	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/login/motDePasseOublie")
	public String goToChangerMotDePasse(Model model) {
		model.addAttribute("message", "");
		return "motDePasseOublie";
	}
	@PostMapping("/login/motDePasseOublie")
	public String validerChangerMotDePasse(@RequestParam("mail") String mail , Model model) {
		String message="";
		//chercher le mail de l'utilisateur
		if(utilisateurService.getUtilisateurByEmail(mail)!=null) {
			message="un mail de renouvelement de mot de passe vient d'être envoyé à votre adresse";
		}
		else{
			message="Cette adresse mail n'est liée à aucun compte";
		}
		model.addAttribute("message", message);
		return "motDePasseOublie";
	}
	
	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "/login";
	}
	
}
