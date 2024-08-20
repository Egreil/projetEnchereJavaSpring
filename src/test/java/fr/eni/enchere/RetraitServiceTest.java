package fr.eni.enchere;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.enchere.bll.RetraitService;

import fr.eni.enchere.bll.ArticleVenduService;
import fr.eni.enchere.bll.CategorieService;
import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;

@SpringBootTest
class RetraitServiceTest {

	@Autowired
	RetraitService retraitService;
	@Autowired
	private ArticleVenduService articleVenduService;
	@Autowired
	private CategorieService categorieService;
	@Autowired
	private UtilisateurService utilisateurService;
	
	
	@Test
	public void ajouterRetraitTest() {
		Utilisateur utilisateur=new Utilisateur("pseudo","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurService.ajouterUtilisateur(utilisateur);
		
		Categorie categorie=new Categorie("categorieRetrait");
		categorieService.ajouterCategorie(categorie);
		
		ArticleVendu articleVendu= new ArticleVendu("nomArticle","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
		articleVenduService.ajouterArticle(articleVendu);
		
		Retrait retrait=new Retrait(articleVendu,"dans","ta","ville");
		retraitService.ajouterRetrait(retrait);
	}
	@Test
	public void modifierRetraitTest() {
		Utilisateur utilisateur=new Utilisateur("pseudo","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurService.ajouterUtilisateur(utilisateur);
		
		Categorie categorie=new Categorie("categorieRetrait");
		categorieService.ajouterCategorie(categorie);
		
		ArticleVendu articleVendu= new ArticleVendu("nomArticle","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
		articleVenduService.ajouterArticle(articleVendu);
		
		Retrait retrait=new Retrait(articleVendu,"dans","ta","ville");
		retraitService.ajouterRetrait(retrait);
		Retrait retraitModifier=new Retrait(articleVendu,"dans","ton","allee");
		retraitService.updateRetrait(retraitModifier);
	}
	@Test
	public void findAllRetraitTest() {
		System.out.println((retraitService.findAll()).size());
	}
	@Test
	public void getRetraitByIDTest() {
		Utilisateur utilisateur=new Utilisateur("pseudo","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurService.ajouterUtilisateur(utilisateur);
		
		Categorie categorie=new Categorie("categorieRetrait");
		categorieService.ajouterCategorie(categorie);
		
		ArticleVendu articleVendu= new ArticleVendu("nomArticle","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
		articleVenduService.ajouterArticle(articleVendu);
		
		Retrait retrait=new Retrait(articleVendu,"sera","tout","mort");
		retraitService.ajouterRetrait(retrait);
		System.out.println(retraitService.getRetraitById(retrait.getArticleVendu().getNoArticle()));
	}
	@Test
	public void supprimerRetraitTest() {
		Utilisateur utilisateur=new Utilisateur("pseudo","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurService.ajouterUtilisateur(utilisateur);
		
		Categorie categorie=new Categorie("categorieRetrait");
		categorieService.ajouterCategorie(categorie);
		
		ArticleVendu articleVendu= new ArticleVendu("nomArticle","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
		articleVenduService.ajouterArticle(articleVendu);
		
		Retrait retrait=new Retrait(articleVendu,"sera","tout","mort");
		retraitService.ajouterRetrait(retrait);
		retraitService.supprimerRetrait(retrait);
		try {
		System.out.println(retraitService.getRetraitById(retrait.getArticleVendu().getNoArticle()));
		assertTrue(false);
		}
		catch(Exception e) {
			assertTrue(true);
			System.out.println("retrait supprimé");
		}
	}

}
