package fr.eni.enchere.ihm;

import java.util.List;

import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.eni.enchere.bll.ArticleVenduService;
import fr.eni.enchere.bll.CategorieService;
import fr.eni.enchere.bll.EnchereService;
import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dal.ArticleVenduRepository;

@Controller
@RequestMapping("")
public class EncheresIndexController {

	
	ArticleVenduService articleVenduService;
	UtilisateurService utilisateurService;
	CategorieService categorieService;
	EnchereService enchereService;
	
	
	public EncheresIndexController(ArticleVenduService articleVenduService, UtilisateurService utilisateurService,
			CategorieService categorieService, EnchereService enchereService) {
		super();
		this.articleVenduService = articleVenduService;
		this.utilisateurService = utilisateurService;
		this.categorieService = categorieService;
		this.enchereService = enchereService;
	}
	
	@GetMapping
	public String afficherEncheres(Model model) {
		List<ArticleVendu> articleVendus=articleVenduService.findAll();
		articleVendus.forEach(a->{
			List<Enchere> encheresSurArticle=enchereService.encheresSurArticle(a);
			if(encheresSurArticle.size()>0) {
			a.setPrixVente(enchereService.enchereMaxParArticle(encheresSurArticle).getMontantEnchere());
			articleVenduService.updateStatut(a);
			}
			else {
				a.setPrixVente(a.getPrixInitial());
			}
		});
		
		List<Enchere> encheres=enchereService.findAll();
		//model.addAttribute("encheres",enchereService.findAll());
		articleVendus.forEach(a->System.out.println(a));
		model.addAttribute("articles",articleVendus);
		model.addAttribute("categories",categorieService.findAll());
		//model.addAttribute("categorie",new Categorie());
		return "enchere";
	}
	
	
}
