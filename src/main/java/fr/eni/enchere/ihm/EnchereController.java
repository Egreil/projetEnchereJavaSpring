package fr.eni.enchere.ihm;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.eni.enchere.bll.ArticleVenduService;
import fr.eni.enchere.bll.CategorieService;
import fr.eni.enchere.bll.EnchereService;
import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;

@Controller
@RequestMapping("/enchere")
public class EnchereController {

	EnchereService enchereService;
	ArticleVenduService articleVenduService;
	UtilisateurService utilisateurService;
	CategorieService categorieService;
	
	public EnchereController(EnchereService enchereService, ArticleVenduService articleVenduService,
			UtilisateurService utilisateurService, CategorieService categorieService) {
		super();
		this.enchereService = enchereService;
		this.articleVenduService = articleVenduService;
		this.utilisateurService = utilisateurService;
		this.categorieService = categorieService;
	}
	
	//Affichage après recherche de l'enchere
	@PostMapping("/recherche")
	public String rechercheEnchere(Model model,@RequestParam("recherche") String recherche,
			@RequestParam("categorie") String categorieString){
		Categorie categorie=categorieService.getCategorieByID(Long.parseLong(categorieString));
		System.out.println("RECHERCHE : "+recherche +"      CATEGORIE : "+categorie);
		List<ArticleVendu> articles= articleVenduService.rechercheArticles(recherche, categorie);
		model.addAttribute("articles", articles);
		model.addAttribute("encheres",enchereService.rechercheEncheres(articles));
		model.addAttribute("categories", categorieService.findAll());
		return "enchere";
	}
	//Affichage des données de l'article
	@GetMapping("/article")
	public String afficherArticle(Model model,@RequestParam("noArticle") long noArticle){
		model.addAttribute("article",articleVenduService.getArticleByID(noArticle));
		model.addAttribute("enchere",new Enchere());
		return "detailsArticle";
	}
	
	@PostMapping("/article/encherir")
	public String encherir(@RequestParam("noArticle") String noArticle
			/*@ModelAttribute("article") ArticleVendu articleVendu*/,
			@RequestParam("montantEnchere") Double montantEnchere,Model model){
		Enchere enchere= new Enchere();
		enchere.setMontantEnchere(montantEnchere);
		enchere.setUtilisateur(utilisateurService.getUtilisateurByPseudo(SecurityContextHolder.getContext().getAuthentication().getName()));
		enchere.setDateEnchere(LocalDate.now());
		
		ArticleVendu articleVendu=articleVenduService.getArticleByID(Long.parseLong(noArticle));
		enchere.setArticleVendu(articleVendu);
		enchereService.ajouterEnchere(enchere);
		
		if( enchere.getMontantEnchere()>articleVendu.getPrixVente()) {
			articleVendu.setPrixVente(enchere.getMontantEnchere());
		}
		
		model.addAttribute("article", articleVendu);
		model.addAttribute("enchere",new Enchere());
		return "detailsArticle";
	}
	
	
}
