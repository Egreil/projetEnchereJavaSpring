package fr.eni.enchere;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.DebugBeanDefinitionParser;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ArticleVenduRepository;
import fr.eni.enchere.dal.CategorieRepository;
import fr.eni.enchere.dal.UtilisateurRepository;
import net.bytebuddy.agent.builder.ResettableClassFileTransformer;

@SpringBootTest
class ArticleVenduRepositoryTest {
	
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private ArticleVenduRepository articleVenduRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;


	
	@Test //OK
	void ajoutArticleVenduTest() {
		//Creation de categories
		Categorie categorie=new Categorie("cat1");
		categorieRepository.ajouterCategorie(categorie);
		Utilisateur utilisateur= new Utilisateur("pseudo","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurRepository.ajouterUtilisateur(utilisateur);// A completer avec le code d'Abdel
		ArticleVendu articleVendu= new ArticleVendu("nomArticle","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
		articleVenduRepository.ajouterArticle(articleVendu);
		//ArticleVendu article2 = new ArticleVendu(123456, "Nom de l'article", "Description de l'article", LocalDate.now(), LocalDate.now().plusDays(7), 100.0, 0.0, "En cours", vendeur, categorie);
	}
	
	@Test
	void supprimerArticleVendu() {
		Categorie categorie=new Categorie("cat1");
		categorieRepository.ajouterCategorie(categorie);
		Utilisateur utilisateur= new Utilisateur("pseudo","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurRepository.ajouterUtilisateur(utilisateur);// A completer avec le code d'Abdel
		ArticleVendu article= new ArticleVendu("nomArticle","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
		articleVenduRepository.ajouterArticle(article);// On ajoute l'article 
		System.out.println(articleVenduRepository.getArticleByID(article.getNoArticle()));
		articleVenduRepository.supprimerArticle(article);//On le supprime
		try {
			System.out.println(articleVenduRepository.getArticleByID(article.getNoArticle()));
			assertTrue(false);
		}
		catch (Exception e) {
			assertTrue(true);
		}
		
	}
	
	@Test //OK
	void updateArticleVenduTest() {
		Categorie categorie=new Categorie("catUpdate");
		categorieRepository.ajouterCategorie(categorie);
		System.out.println(categorie.getNoCategorie());
		
		Utilisateur utilisateur= new Utilisateur("pseudo","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurRepository.ajouterUtilisateur(utilisateur);// A completer avec le code d'Abdel
		System.out.println(utilisateur);
		
		ArticleVendu articleVendu= new ArticleVendu("TestUPDATE","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
		articleVenduRepository.ajouterArticle(articleVendu);
		
		System.out.println(articleVendu);
		ArticleVendu articleVenduModifier= new ArticleVendu(articleVendu.getNoArticle(),"EraseUPDATE","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
		
		articleVenduRepository.update(articleVenduModifier);
	}
	
	@Test
	void getArticleByIDTest() {
		Categorie categorie=new Categorie("catUpdate");
		categorieRepository.ajouterCategorie(categorie);
		System.out.println(categorie.getNoCategorie());
		
		Utilisateur utilisateur= new Utilisateur("pseudo","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurRepository.ajouterUtilisateur(utilisateur);// A completer avec le code d'Abdel
		System.out.println(utilisateur);
		
		ArticleVendu articleVendu= new ArticleVendu("TestUPDATE","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
		articleVenduRepository.ajouterArticle(articleVendu);
		System.out.println("GET: "+articleVenduRepository.getArticleByID(articleVendu.getNoArticle()));
	}
	@Test
	void findAllArticleTest() {
		System.out.println(articleVenduRepository.findAll().size());
	}
	
	@Test
	void updateStatut() {
		Categorie categorie=new Categorie("catUpdateStatut");
		categorieRepository.ajouterCategorie(categorie);
		System.out.println(categorie.getNoCategorie());
		
		Utilisateur utilisateur= new Utilisateur("pseudoUpdateStatut","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurRepository.ajouterUtilisateur(utilisateur);// A completer avec le code d'Abdel
		System.out.println(utilisateur);
		
		ArticleVendu articleVenduAvant= new ArticleVendu("TestUPDATEStatut","description",LocalDate.now().plusDays(1),LocalDate.now().plusDays(3),0.0,"En cours",utilisateur,categorie);
		articleVenduRepository.ajouterArticle(articleVenduAvant);
		ArticleVendu articleVenduPendant= new ArticleVendu("TestUPDATEStatut","description",LocalDate.now().plusDays(-1),LocalDate.now().plusDays(3),0.0,"en cours",utilisateur,categorie);
		articleVenduRepository.ajouterArticle(articleVenduPendant);
		ArticleVendu articleVenduApres= new ArticleVendu("TestUPDATEStatut","description",LocalDate.now().plusDays(-3),LocalDate.now().plusDays(-1),0.0,"en cours",utilisateur,categorie);
		articleVenduRepository.ajouterArticle(articleVenduApres);
		
		articleVenduAvant=articleVenduRepository.getArticleByID(articleVenduAvant.getNoArticle());
		articleVenduPendant=articleVenduRepository.getArticleByID(articleVenduPendant.getNoArticle());
		articleVenduApres=articleVenduRepository.getArticleByID(articleVenduApres.getNoArticle());

		
		articleVenduRepository.updateStatut(articleVenduAvant);
		articleVenduRepository.updateStatut(articleVenduPendant);
		articleVenduRepository.updateStatut(articleVenduApres);
		
		System.out.println(articleVenduAvant);
		System.out.println(articleVenduPendant);
		System.out.println(articleVenduApres);
		
		assertEquals("En attente",articleVenduRepository.getArticleByID(articleVenduAvant.getNoArticle()).getEtatVente());
		assertEquals("En cours",articleVenduRepository.getArticleByID(articleVenduPendant.getNoArticle()).getEtatVente());
		assertEquals("Terminee",articleVenduRepository.getArticleByID(articleVenduApres.getNoArticle()).getEtatVente());
		
	}

}
