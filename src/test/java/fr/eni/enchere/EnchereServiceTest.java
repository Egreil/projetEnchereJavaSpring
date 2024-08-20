package fr.eni.enchere;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import fr.eni.enchere.bll.ArticleVenduService;
import fr.eni.enchere.bll.CategorieService;
import fr.eni.enchere.bll.EnchereService;
import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ArticleVenduRepository;
import fr.eni.enchere.dal.CategorieRepository;
import fr.eni.enchere.dal.EnchereRepository;
import fr.eni.enchere.dal.RepositoryReset;
import fr.eni.enchere.dal.UtilisateurRepository;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
public class EnchereServiceTest {

	@Autowired
	ArticleVenduService articleVenduService;
	@Autowired
	CategorieService categorieService;
	@Autowired
	UtilisateurService utilisateurService;
	@Autowired
	EnchereService enchereService;
	@Autowired
	RepositoryReset repositoryReset;
	
	@BeforeAll
	public void init() {
		repositoryReset.resetBDD();
	}

	@Test //OK
	void ajoutEnchereTest() {
		//Creation de l'article
		Categorie categorie=new Categorie("cat1Service");
		categorieService.ajouterCategorie(categorie);
		Utilisateur vendeur= new Utilisateur("pseudoEnchereAddVendeur","nom","prenom","pseudoEnchereAddVendeur@","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurService.ajouterUtilisateur(vendeur);// A completer avec le code d'Abdel
		ArticleVendu article= new ArticleVendu("nomArticle","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",vendeur,categorie);
		articleVenduService.ajouterArticle(article);
		//Creation Utilisateur acheteur
		Utilisateur acheteur= new Utilisateur("pseudoEnchereAddAcheteur","nom","prenom","pseudoEnchereAddAcheteur@","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurService.ajouterUtilisateur(acheteur);
		
		//rassemblement des donnes dans la creation de l'enchere
		Enchere enchere= new Enchere(acheteur,article,LocalDate.of(2009, 3, 10),3.3);
		enchereService.ajouterEnchere(enchere);
	
		
	}
	@Test // OK
	public void supprimerEnchereTest() {
		//Creation de l'article
		Categorie categorie=new Categorie("cat1");
		categorieService.ajouterCategorie(categorie);
		Utilisateur vendeur= new Utilisateur("pseudoEnchereDelVendeur","nom","prenom","pseudoEnchereDelVendeur@","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurService.ajouterUtilisateur(vendeur);// A completer avec le code d'Abdel
		ArticleVendu article= new ArticleVendu("nomArticle","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",vendeur,categorie);
		articleVenduService.ajouterArticle(article);
		//Creation Utilisateur acheteur
		Utilisateur acheteur= new Utilisateur("pseudoEnchereDelAcheteur","nom","prenom","pseudoEnchereDelAcheteur@","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurService.ajouterUtilisateur(acheteur);
		//rassemblement des donnes dans la creation de l'enchere
		Enchere enchere= new Enchere(acheteur,article,LocalDate.of(2009, 3, 10),3.3);
		enchereService.ajouterEnchere(enchere);
		
	    // On le supprime
	    enchereService.supprimerEnchere(enchere);
		try {
			System.out.println(enchereService.getEnchereByID(enchere.getNoEnchere()));
			assertTrue(false);
		}
		catch (Exception e) {
			assertTrue(true);
		}
	    
	}
	
	@Test // OK
	void findAllEnchereTest() {
		System.out.println(enchereService.findAll().size());
	}
	
	
	@Test // OK
	void getEnchereByIdTest() {
		Categorie categorie=new Categorie("cat1GetTest");
		categorieService.ajouterCategorie(categorie);
		Utilisateur vendeur= new Utilisateur("getEnchereVendeur","nom","prenom","getEnchereVendeur@","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurService.ajouterUtilisateur(vendeur);// A completer avec le code d'Abdel
		ArticleVendu article= new ArticleVendu("nomArticle","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"En cours",vendeur,categorie);
		articleVenduService.ajouterArticle(article);
		//Creation Utilisateur acheteur
		Utilisateur acheteur= new Utilisateur("getEnchereAcheteur","nom","prenom","getEnchereAcheteur@","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurService.ajouterUtilisateur(acheteur);
		
		//Creation Echeteur
		Enchere enchere= new Enchere(acheteur,article,LocalDate.of(2009, 3, 10),6);
		enchereService.ajouterEnchere(enchere);
		System.out.println("GET: "+ enchereService.getEnchereByID(enchere.getNoEnchere()));
	}
	@Test //OK
	void updateEnchereTest() {
		Categorie categorie=new Categorie("cat1UpdateTest");
		categorieService.ajouterCategorie(categorie);
		Utilisateur vendeur= new Utilisateur("updateEnchereVendeur","nom","prenom","updateEnchereVendeur@","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurService.ajouterUtilisateur(vendeur);// A completer avec le code d'Abdel
		ArticleVendu article= new ArticleVendu("nomArticle","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",vendeur,categorie);
		articleVenduService.ajouterArticle(article);
		//Creation Utilisateur acheteur
		Utilisateur acheteur= new Utilisateur("updateEnchereAcheteur","nom","prenom","updateEnchereAcheteur@","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurService.ajouterUtilisateur(acheteur);
		
		//Creation Echeteur
		Enchere enchere= new Enchere(acheteur,article,LocalDate.of(2009, 3, 10),3.3);
		enchereService.ajouterEnchere(enchere);
		//System.out.println(enchere);
		
		Enchere enchereModifer= new Enchere(enchere.getNoEnchere(),acheteur,article,LocalDate.of(2028, 7, 10),222.5);
		enchereService.update(enchereModifer);
	}
	
	@Test
	void enchereServiceRechercheParNomEtCategorie() {
		//Bonne catégorie
		Categorie categorie=new Categorie("catBonne");
		categorieService.ajouterCategorie(categorie);
		//Mauvaise catégorie
		Categorie categorieMauvaise=new Categorie("categorieMauvaise");
		categorieService.ajouterCategorie(categorieMauvaise);
		
		Utilisateur utilisateur=new Utilisateur("UserGetArticlesEnchereVendeur", "nono", "abdel", "UserGetArticlesEnchereVendeur@example.com", "25335445", "rue de nono", "5258", "rennes", "354", 2, false);
		utilisateurService.ajouterUtilisateur(utilisateur);
		Utilisateur acheteur=new Utilisateur("UserGetArticlesEnchereAcheteur", "nono", "abdel", "UserGetArticlesEnchereAcheteur@example.com", "25335445", "rue de nono", "5258", "rennes", "354", 2, false);
		utilisateurService.ajouterUtilisateur(acheteur);
		
		ArticleVendu articleVendu= new ArticleVendu("doigt","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"En cours",utilisateur,categorie);
		articleVenduService.ajouterArticle(articleVendu);
		
		Enchere enchereBonne=new Enchere(acheteur,articleVendu,LocalDate.of(2009, 3, 10),3.3);
		enchereService.ajouterEnchere(enchereBonne);
		
		articleVendu= new ArticleVendu("badoit","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"En cours",utilisateur,categorie);
		articleVenduService.ajouterArticle(articleVendu);
		
		enchereBonne=new Enchere(acheteur,articleVendu,LocalDate.of(2009, 3, 10),3.3);
		enchereService.ajouterEnchere(enchereBonne);
		// Bon nom et mauvaise catégorie
		articleVendu= new ArticleVendu("ardoise","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"En cours",utilisateur,categorieMauvaise);
		articleVenduService.ajouterArticle(articleVendu);
		
		enchereBonne=new Enchere(acheteur,articleVendu,LocalDate.of(2009, 3, 10),3.3);
		enchereService.ajouterEnchere(enchereBonne);
		//Mauvais nom et bonne catégorie
		ArticleVendu articleVenduMauvais= new ArticleVendu("trou","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"En cours",utilisateur,categorie);
		articleVenduService.ajouterArticle(articleVenduMauvais);
		Enchere enchereMauvaise=new Enchere(acheteur,articleVenduMauvais,LocalDate.of(2009, 3, 10),3.3);
		enchereService.ajouterEnchere(enchereMauvaise);
		//Tous faux
		articleVenduMauvais= new ArticleVendu("fruit","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"En cours",utilisateur,categorieMauvaise);
		articleVenduService.ajouterArticle(articleVenduMauvais);
		enchereMauvaise=new Enchere(acheteur,articleVenduMauvais,LocalDate.of(2009, 3, 10),3.3);
		enchereService.ajouterEnchere(enchereMauvaise);
		System.out.println("Nom et Catgorie :");
		List<ArticleVendu> articlesRecherches=articleVenduService.rechercheArticles("doi", categorie);
		List<Enchere> encheres=enchereService.rechercheEncheres(articlesRecherches);
		encheres.forEach(e->System.out.println(e));
	}
	
	@Test
	public void listerEncheresSurArticle() {
		Categorie categorie=new Categorie("catEncheresSurArticle");
		categorieService.ajouterCategorie(categorie);
		
		Utilisateur utilisateur=new Utilisateur("UserEncheresSurArticleVendeur", "nono", "abdel", "UserEncheresSurArticleVendeur@example.com", "25335445", "rue de nono", "5258", "rennes", "354", 2, false);
		utilisateurService.ajouterUtilisateur(utilisateur);
		Utilisateur acheteur=new Utilisateur("UserEncheresSurArticleAcheteur", "nono", "abdel", "UserEncheresSurArticleAcheteur@example.com", "25335445", "rue de nono", "5258", "rennes", "354", 2, false);
		utilisateurService.ajouterUtilisateur(acheteur);
		
		ArticleVendu articleVendu= new ArticleVendu("articleEncheresSurArticle","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"En cours",utilisateur,categorie);
		articleVenduService.ajouterArticle(articleVendu);
		
		Enchere enchereBonne=new Enchere(acheteur,articleVendu,LocalDate.of(2009, 3, 10),3.3);
		enchereService.ajouterEnchere(enchereBonne);
		enchereBonne=new Enchere(acheteur,articleVendu,LocalDate.of(2009, 3, 10),12);
		enchereService.ajouterEnchere(enchereBonne);
		enchereBonne=new Enchere(acheteur,articleVendu,LocalDate.of(2009, 3, 10),15);
		enchereService.ajouterEnchere(enchereBonne);
		enchereBonne=new Enchere(acheteur,articleVendu,LocalDate.of(2009, 3, 10),20);
		enchereService.ajouterEnchere(enchereBonne);
		
		List<Enchere> encheres= enchereService.encheresSurArticle(articleVendu);
		encheres.forEach(e->System.out.println(e));
		assertEquals(4, encheres.size());
		
		Enchere enchereMax=enchereService.enchereMaxParArticle(encheres);
		assertEquals(20, enchereMax.getMontantEnchere());
		
	}
}
