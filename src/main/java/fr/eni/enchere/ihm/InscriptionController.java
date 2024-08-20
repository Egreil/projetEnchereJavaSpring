package fr.eni.enchere.ihm;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.security.ConfigSecurity;
import fr.eni.enchere.security.MyUserDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RequestMapping("/inscription")
@Controller
public class InscriptionController {

	UtilisateurService utilisateurService;
	ConfigSecurity configSecurity;
	
	
	public InscriptionController(UtilisateurService utilisateurService) {
		super();
		this.utilisateurService = utilisateurService;
	}

	@GetMapping("")
	public String creerUtilisateur(Model model) {
		model.addAttribute("utilisateur", new Utilisateur());
		//System.out.println("IIIIIICCCCCCCCCCIIIIIIIIIII");
		return "inscription";	
	}
	
	@PostMapping("/creer")
	public String sauvegardeUtilisateur(@Valid@ModelAttribute("utilisateur")Utilisateur utilisateur,BindingResult binding,Model model){
		if(binding.hasErrors()) {
			return "inscription";
		}
		utilisateur.setAdministrateur(false);
		utilisateur.setCredit(0);
		utilisateurService.ajouterUtilisateur(utilisateur);
		//Reste à connecter l'utilisateur
		new SavedRequestAwareAuthenticationSuccessHandler() {
			   @Override
			    public void onAuthenticationSuccess(HttpServletRequest request, 
			      HttpServletResponse response, Authentication authentication)
			      throws IOException, ServletException {
			 
			    	  // MET L'UTILISATEUR CONNECTE DANS UNE VARIABLE DE SESSION currentUser
			    	  if(authentication!=null) {
			    		  MyUserDetail userDetails = (MyUserDetail) authentication.getPrincipal();
			    		  request.getSession().setAttribute("currentUser", utilisateur);
			    		  System.out.println("after log"+ userDetails.getAuthorities().toString());
			    	  }
			    	  super.onAuthenticationSuccess(request, response, authentication);
			    }
		};
		return "redirect:/";
		
	}
}
