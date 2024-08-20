package fr.eni.enchere.ihm;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Binding;

import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.eni.enchere.bll.ArticleVenduService;
import fr.eni.enchere.bll.CategorieService;
import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.UtilisateurRepository;
import fr.eni.enchere.security.MyUserDetail;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/profil")
public class UtilisateurControler {
	
	UtilisateurService utilisateurService;
	ArticleVenduService articleVenduService;
	CategorieService categorieService;
	@Autowired
	PasswordEncoder encodeur;
	
	public UtilisateurControler(UtilisateurService utilisateurService, ArticleVenduService articleVenduService,CategorieService categorieService) {
		super();
		this.utilisateurService = utilisateurService;
		this.articleVenduService = articleVenduService;
		this.categorieService=categorieService;
	}

	@GetMapping("")
	public String afficherProfil(Model model,@RequestParam("noUtilisateur") String noUtilisateur) {//(MyUserDetail) ;.getUser() .getPrincipal()
		Utilisateur utilisateur=new Utilisateur();
		if(!noUtilisateur.equals("0")) {
			utilisateur= utilisateurService.getUtilisateurByID(Long.parseLong(noUtilisateur));
		}
		else {
			utilisateur= utilisateurService.getUtilisateurByPseudo(SecurityContextHolder.getContext().getAuthentication().getName());
		}
		System.out.println("principal : "+((SecurityContextHolder.getContext().getAuthentication().getName())));
		System.out.println(utilisateur);
		model.addAttribute("utilisateur",utilisateur);
		model.addAttribute("article",new ArticleVendu());//Pour ajouter un nouvel article
		model.addAttribute("articles",utilisateurService.getArticlesByUser(utilisateur));
		model.addAttribute("categories",categorieService.findAll());
		return "profil";
	}
	
//	@GetMapping("test")
//	public String testGet() {
//		return "test";
//		
//	}
//	@PostMapping("tester")
//	public String testPost() {
//		return"test";
//	}
	@GetMapping("updateProfil")
	public String goToUpdateProfil(Model model){
		model.addAttribute("utilisateur",utilisateurService.getUtilisateurByPseudo(SecurityContextHolder.getContext().getAuthentication().getName()));
		return "updateProfil";
	}
	@PostMapping("updateProfil")
	public String updateUtilisateur(@Valid@ModelAttribute("utilisateur") Utilisateur utilisateur,
			@RequestParam("ancienMotDePasse") String ancienMotDePasse ,
			@RequestParam("nouveauMotDePasse") String nouveauMotDePasse ,
			BindingResult bindingResult,
			Model model){
		
		if(bindingResult.hasErrors()) {
			return "updateProfil";
		}
		Utilisateur ancienUtilisateur=utilisateurService.getUtilisateurByPseudo(SecurityContextHolder.getContext().getAuthentication().getName());
		utilisateur.setNoUtilisateur(ancienUtilisateur.getNoUtilisateur());
		utilisateur.setPseudo(ancienUtilisateur.getPseudo());
		utilisateur.setNom(ancienUtilisateur.getNom());
		utilisateur.setPrenom(ancienUtilisateur.getPrenom());
		utilisateur.setEmail(ancienUtilisateur.getEmail());
		utilisateur.setCredit(ancienUtilisateur.getCredit());
		utilisateur.setMotDePasse(ancienUtilisateur.getMotDePasse());
		//Modification du mot de passe
		System.out.println("ancien mot de passe"+ancienMotDePasse);
		System.out.println("nouveau mot de passe"+nouveauMotDePasse);
		if(ancienMotDePasse!="") {
			//controle ancien mot de passe
			if(encodeur.matches(ancienMotDePasse, utilisateur.getMotDePasse())) {
				//criptage du nouveau mot de passe et ajout à l'utilisateur
				utilisateur.setMotDePasse(encodeur.encode(utilisateur.getMotDePasse()));
				System.out.println("mdp changé");
			}
			else {
				System.out.println("mdp incorrect!");
			}
		}
		
		utilisateurService.update(utilisateur);
		
		utilisateur= utilisateurService.getUtilisateurByPseudo(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println(utilisateur);
		model.addAttribute("utilisateur",utilisateur);
		model.addAttribute("article",new ArticleVendu());//Pour ajouter un nouvel article
		model.addAttribute("articles",utilisateurService.getArticlesByUser(utilisateur));
		model.addAttribute("categories",categorieService.findAll());
		return "profil";
		
	}
	
	@PostMapping("ajouterArticle")
	public String ajouterArticle(@Valid@ModelAttribute("article") ArticleVendu articleVendu,BindingResult bindingResult, Model model) {
		//Détection des erreurs
		if(bindingResult.hasErrors()) {
			return "profil";
		}
		
		
		//Set les derniers attributs 
		articleVendu.setEtatVente("en attente");
		Utilisateur utilisateur=utilisateurService.getUtilisateurByPseudo(SecurityContextHolder.getContext().getAuthentication().getName());
		articleVendu.setVendeur(utilisateur);
		articleVenduService.ajouterArticle(articleVendu);
		System.out.println(articleVendu);
		
		//Récupération des infos pour recharger la page.
		model.addAttribute("utilisateur",utilisateur);
		model.addAttribute("article",new ArticleVendu());//Pour ajouter un nouvel article
		model.addAttribute("articles",utilisateurService.getArticlesByUser(utilisateur));
		model.addAttribute("categories",categorieService.findAll());
		
		return "profil";
	}
	
	// Modifier des données de l'article
	@GetMapping("modification")
	public String afficherArticle(@RequestParam("noArticle") String noArticle,Model model) {
		System.out.println(noArticle);
		model.addAttribute("article",articleVenduService.getArticleByID(Long.parseLong(noArticle)));
		model.addAttribute("categories",categorieService.findAll());
		return "modification";
	}
	
	
	// Modifier des données de l'article
	@PostMapping("/modification")
	public String modificationArticle(@Valid@ModelAttribute("article") ArticleVendu articleVendu,BindingResult bindingResult,Model model) {
		
		//Détection des erreurs
		if(bindingResult.hasErrors()) {
			return "modification";
		}
		
		//Set les derniers attributs 
		Utilisateur utilisateur=utilisateurService.getUtilisateurByPseudo(SecurityContextHolder.getContext().getAuthentication().getName());
		articleVendu.setVendeur(utilisateur);
		articleVendu.setEtatVente("En attente");
	    articleVenduService.update(articleVendu); 
	    System.out.println(articleVendu);
	    
	    //Récupération des infos pour recharger la page.
		model.addAttribute("utilisateur",utilisateur);
		model.addAttribute("article",new ArticleVendu());//Pour ajouter un nouvel article
		model.addAttribute("articles",utilisateurService.getArticlesByUser(utilisateur));
		model.addAttribute("categories",categorieService.findAll());
		
	    return "profil" ;
	}
	
	
//  Annuler des données de l'article
	@GetMapping("/supprimer")
	public String supprimerVente(@RequestParam("noArticle") String noArticle,Model model) {
		System.out.println(noArticle);
		ArticleVendu articleVendu=articleVenduService.getArticleByID(Long.parseLong(noArticle));
		System.out.println(articleVendu);
		articleVenduService.supprimerArticle(articleVendu);
		Utilisateur utilisateur=utilisateurService.getUtilisateurByPseudo(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("utilisateur",utilisateur);
		model.addAttribute("article",new ArticleVendu());//Pour ajouter un nouvel article
		model.addAttribute("articles",utilisateurService.getArticlesByUser(utilisateur));
		model.addAttribute("categories",categorieService.findAll());
		return "profil" ;
	}

	
	

}
