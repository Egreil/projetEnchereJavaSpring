package fr.eni.enchere;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ArticleVenduRepository;
import fr.eni.enchere.dal.CategorieRepository;
import fr.eni.enchere.dal.RetraitRepository;
import fr.eni.enchere.dal.UtilisateurRepository;

@SpringBootTest
class RetraitRepositoryTest {
	
	@Autowired
	private RetraitRepository retraitRepository;
	@Autowired
	private ArticleVenduRepository articleVenduRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Test
	public void ajouterRetraitTest() {
		Utilisateur utilisateur=new Utilisateur("pseudo","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurRepository.ajouterUtilisateur(utilisateur);
		
		Categorie categorie=new Categorie("categorieRetrait");
		categorieRepository.ajouterCategorie(categorie);
		
		ArticleVendu articleVendu= new ArticleVendu("nomArticle","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
		articleVenduRepository.ajouterArticle(articleVendu);
		
		Retrait retrait=new Retrait(articleVendu,"dans","ta","ville");
		retraitRepository.ajouterRetrait(retrait);
	}
	@Test
	public void modifierRetraitTest() {
		Utilisateur utilisateur=new Utilisateur("pseudo","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurRepository.ajouterUtilisateur(utilisateur);
		
		Categorie categorie=new Categorie("categorieRetrait");
		categorieRepository.ajouterCategorie(categorie);
		
		ArticleVendu articleVendu= new ArticleVendu("nomArticle","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
		articleVenduRepository.ajouterArticle(articleVendu);
		
		Retrait retrait=new Retrait(articleVendu,"dans","ta","ville");
		retraitRepository.ajouterRetrait(retrait);
		Retrait retraitModifier=new Retrait(articleVendu,"dans","ton","allee");
		retraitRepository.updateRetrait(retraitModifier);
	}
	@Test
	public void findAllRetraitTest() {
		System.out.println((retraitRepository.findAll()).size());
	}
	@Test
	public void getRetraitByIDTest() {
		Utilisateur utilisateur=new Utilisateur("pseudo","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurRepository.ajouterUtilisateur(utilisateur);
		
		Categorie categorie=new Categorie("categorieRetrait");
		categorieRepository.ajouterCategorie(categorie);
		
		ArticleVendu articleVendu= new ArticleVendu("nomArticle","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
		articleVenduRepository.ajouterArticle(articleVendu);
		
		Retrait retrait=new Retrait(articleVendu,"sera","tout","mort");
		retraitRepository.ajouterRetrait(retrait);
		System.out.println(retraitRepository.getRetraitById(retrait.getArticleVendu().getNoArticle()));
	}
	@Test
	public void supprimerRetraitTest() {
		Utilisateur utilisateur=new Utilisateur("pseudo","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurRepository.ajouterUtilisateur(utilisateur);
		
		Categorie categorie=new Categorie("categorieRetrait");
		categorieRepository.ajouterCategorie(categorie);
		
		ArticleVendu articleVendu= new ArticleVendu("nomArticle","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
		articleVenduRepository.ajouterArticle(articleVendu);
		
		Retrait retrait=new Retrait(articleVendu,"sera","tout","mort");
		retraitRepository.ajouterRetrait(retrait);
		retraitRepository.supprimerRetrait(retrait);
		try {
		System.out.println(retraitRepository.getRetraitById(retrait.getArticleVendu().getNoArticle()));
		assertTrue(false);
		}
		catch(Exception e) {
			assertTrue(true);
			System.out.println("retrait supprimé");
		}
	}
	
}
