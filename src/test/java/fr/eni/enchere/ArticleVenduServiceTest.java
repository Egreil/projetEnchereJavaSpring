package fr.eni.enchere;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.enchere.bll.ArticleVenduService;
import fr.eni.enchere.bll.CategorieService;
import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.RepositoryReset;

@SpringBootTest
class ArticleVenduServiceTest {

	@Autowired
	ArticleVenduService articleVenduService;
	@Autowired
	CategorieService categorieService;
	@Autowired
	UtilisateurService utilisateurService;
	@Autowired
	RepositoryReset repositoryReset;
	
	@BeforeEach
	public void init() {
		repositoryReset.resetBDD();
	}
//	@Test
//	public void testReset() {
//		repositoryReset.resetBDD();
//	}
	


	@Test
	void ajouterArticleTest() {
		System.out.println("Ajouter:");
		//Création d'une catégorie
		Categorie categorie=new Categorie("catService");
		categorieService.ajouterCategorie(categorie);
		//Création d'un Utilisateur
		Utilisateur utilisateur= new Utilisateur("pseudoService","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurService.ajouterUtilisateur(utilisateur);
		//Création d'un article
		ArticleVendu articleVendu= new ArticleVendu("nomArticleService","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
		articleVenduService.ajouterArticle(articleVendu);
	}
	
	@Test
	void supprimerArticleVendu() {
		System.out.println("Delete:");
		Categorie categorie=new Categorie("catService");
		categorieService.ajouterCategorie(categorie);
		Utilisateur utilisateur= new Utilisateur("pseudoService","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurService.ajouterUtilisateur(utilisateur);// A completer avec le code d'Abdel
		ArticleVendu articleVendu= new ArticleVendu("nomArticleService","description",LocalDate.of(2025, 2, 10),LocalDate.of(2025, 3, 10),0.0,"En attente",utilisateur,categorie);
		articleVenduService.ajouterArticle(articleVendu);
		System.out.println(articleVenduService.getArticleByID(articleVendu.getNoArticle()));
		articleVenduService.supprimerArticle(articleVendu);//On le supprime
		try {
			System.out.println(articleVenduService.getArticleByID(articleVendu.getNoArticle()));
			assertTrue(false);
		}
		catch (Exception e) {
			assertTrue(true);
		}
		
	}
	
	@Test
	void getArticleByIDTest() {
		System.out.println("GetByID:");
		Categorie categorie=new Categorie("catUpdate");
		categorieService.ajouterCategorie(categorie);
		System.out.println(categorie.getNoCategorie());
		
		Utilisateur utilisateur= new Utilisateur("pseudo","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurService.ajouterUtilisateur(utilisateur);// A completer avec le code d'Abdel
		System.out.println(utilisateur);
		
		ArticleVendu articleVendu= new ArticleVendu("TestUPDATE","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
		articleVenduService.ajouterArticle(articleVendu);
		System.out.println("GET: "+articleVenduService.getArticleByID(articleVendu.getNoArticle()));
	}
	
	@Test
	void findAllArticleTest() {
		System.out.println(articleVenduService.findAll().size());
	}
	@Test
	void updateArticleVenduTest() {
	System.out.println("Update:");
	Categorie categorie=new Categorie("catUpdate");
	categorieService.ajouterCategorie(categorie);
	System.out.println(categorie.getNoCategorie());
	
	Utilisateur utilisateur= new Utilisateur("pseudo","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
	utilisateurService.ajouterUtilisateur(utilisateur);// A completer avec le code d'Abdel
	System.out.println(utilisateur);
	
	ArticleVendu articleVendu= new ArticleVendu("TestUPDATE","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
	articleVenduService.ajouterArticle(articleVendu);
	
	System.out.println(articleVendu);
	ArticleVendu articleVenduModifier= new ArticleVendu(5,"EraseUPDATE","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
	
	articleVenduService.update(articleVenduModifier);
}
	@Test
	void rechercheArticle() {
		//Bonne catégorie
		Categorie categorie=new Categorie("catBonne");
		categorieService.ajouterCategorie(categorie);
		//Mauvaise catégorie
		Categorie categorieMauvaise=new Categorie("categorieMauvaise");
		categorieService.ajouterCategorie(categorieMauvaise);
		
		Utilisateur utilisateur=new Utilisateur("UserGetArticles", "nono", "abdel", "user2@example.com", "25335445", "rue de nono", "5258", "rennes", "354", 2, false);
		utilisateurService.ajouterUtilisateur(utilisateur);

		//Bons articles
		ArticleVendu articleVendu= new ArticleVendu("doigt","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
		articleVenduService.ajouterArticle(articleVendu);
		articleVendu= new ArticleVendu("badoit","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
		articleVenduService.ajouterArticle(articleVendu);
		// Bon nom et mauvaise catégorie
		articleVendu= new ArticleVendu("ardoise","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorieMauvaise);
		articleVenduService.ajouterArticle(articleVendu);
		//Mauvais nom et bonne catégorie
		ArticleVendu articleVenduMauvais= new ArticleVendu("trou","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
		articleVenduService.ajouterArticle(articleVenduMauvais);
		//Tous faux
		articleVenduMauvais= new ArticleVendu("fruit","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorieMauvaise);
		articleVenduService.ajouterArticle(articleVenduMauvais);
		System.out.println("Nom et Catgorie :");
		List<ArticleVendu> articleVendusNomEtCategorie=articleVenduService.rechercheArticles("doi", categorie);
		articleVendusNomEtCategorie.forEach(a->System.out.println(a));
		System.out.println("Nom :");
		List<ArticleVendu> articleVendusNom=articleVenduService.rechercheArticles("doi", null);
		articleVendusNom.forEach(a->System.out.println(a));
		System.out.println("Categorie :");
		List<ArticleVendu> articleVendusCategorie=articleVenduService.rechercheArticles("", categorie);
		articleVendusCategorie.forEach(a->System.out.println(a));
		
	}

}
