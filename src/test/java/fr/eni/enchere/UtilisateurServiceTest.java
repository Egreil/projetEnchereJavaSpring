package fr.eni.enchere;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ch.qos.logback.core.net.SyslogOutputStream;
import fr.eni.enchere.bll.ArticleVenduService;
import fr.eni.enchere.bll.CategorieService;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.RepositoryReset;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
class UtilisateurServiceTest {
	@Autowired
	private UtilisateurService utilisateurService;
	@Autowired
	private ArticleVenduService articleVenduService;
	@Autowired
	private CategorieService categorieService;
	@Autowired
	RepositoryReset repositoryReset;
	
	@BeforeAll
	public void init() {
		repositoryReset.resetBDD();
	}
	
	
  @Test
	void testFindAll() {
	  System.out.println("FIND ALL: ");
		List<Utilisateur> list = utilisateurService.findAll();
		list.forEach(System.out::println);
		System.out.println(utilisateurService.findAll());
	}
	
  @Test
	void ajouterUtilisateur() {
	  System.out.println("ajouter: ");
  	Utilisateur user2= new Utilisateur("admin", "nono", "abdel", "user2@example.com", "25335445","rue de nono","5258", "rennes", "admin", 10, true);
  	Utilisateur user3=new Utilisateur("user", "toto","tarmp","user3@example.com","25335445","rue de Nail","5258", "Le mens", "user", 6456, false);
  	utilisateurService.ajouterUtilisateur(user2);
  	utilisateurService.ajouterUtilisateur(user3);
	}
	
  @Test
	void getUtilisateurByID() {
	  System.out.println("GET bY ID: ");
	  	Utilisateur utilisateur = new Utilisateur("blabla", "nono", "abdel", "userBis@example.com", "25335445", "rue de nono", "5258", "rennes", "354", 2, false);
  		utilisateurService.ajouterUtilisateur(utilisateur);
		System.out.println("get : "+utilisateurService.getUtilisateurByID(utilisateur.getNoUtilisateur()));
		
	}
	@Test
	void modifierUtilisateur() {
		System.out.println("UPDATE: ");
      utilisateurService.ajouterUtilisateur(new Utilisateur("user5", "nono", "abdel", "usertris@example.com", "25335445", "rue de nono", "5258", "rennes", "354", 2, false));
		Utilisateur utilisateur = new Utilisateur(8,"blabla2", "nono", "abdel", "userQuadri@example.com", "25335445", "rue de nono", "5258", "rennes", "354", 2, false);
		utilisateurService.update(utilisateur);
		System.out.println();
		//assertEquals("user4",utilisateurService.getUtilisateurByID(utilisateurService.findAll().size()));
		
	}
	@Test
	void supprimerUtilisateur() {
		System.out.println("DELETE: ");
		Utilisateur utilisateur=new Utilisateur("user6", "nono", "abdel", "userflafla@example.com", "25335445", "rue de nono", "5258", "rennes", "354", 2, false);
		utilisateurService.ajouterUtilisateur(utilisateur);
		utilisateurService.supprimerUtilisateur(utilisateur);
		try {
			utilisateurService.getUtilisateurByID(utilisateur.getNoUtilisateur());
			assertTrue(false);
		}
		catch (Exception e) {
			System.out.println("utilisateur supprimé");
			assertTrue(true);
		}
	}
	@Test
	void getArticleByUtilisateurTest() {
		System.out.println("Get: ");
		Utilisateur utilisateur=new Utilisateur("UserGetArticles", "nono", "abdel", "user777@example.com", "25335445", "rue de nono", "5258", "rennes", "354", 2, false);
		utilisateurService.ajouterUtilisateur(utilisateur);
		Categorie categorie=new Categorie("catGetArticleByUser");
		categorieService.ajouterCategorie(categorie);
		ArticleVendu articleVendu= new ArticleVendu("nomArticlegetArticles","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
		articleVenduService.ajouterArticle(articleVendu);
		ArticleVendu articleVendu2= new ArticleVendu("nomArticlegetArticles","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",utilisateur,categorie);
		articleVenduService.ajouterArticle(articleVendu2);
		List<ArticleVendu> articleVendus=utilisateurService.getArticlesByUser(utilisateur);
		System.out.println(articleVendus.size());
	}
	
	@Test
	void blockageCreationPseudoDouble() {
		Utilisateur utilisateur= new Utilisateur("testDouble", "nono", "abdel", "double@mail", "25335445","rue de nono","5258", "rennes", "admin", 10, true);
		utilisateurService.ajouterUtilisateur(utilisateur);
		
		
		try {
				//Bug par pseudo
				utilisateur= new Utilisateur("testDouble", "nono", "abdel", "doubleBis@mail", "25335445","rue de nono","5258", "rennes", "admin", 10, true);
				utilisateurService.ajouterUtilisateur(utilisateur);
				utilisateurService.getUtilisateurByID(utilisateur.getNoUtilisateur());
				assertTrue(false);
			}
			catch(Exception e) {
				System.out.println("echec pseudo");
			}
		try {	//Bug par email
			utilisateur= new Utilisateur("testDoubleBis", "nono", "abdel", "double@mail", "25335445","rue de nono","5258", "rennes", "admin", 10, true);
			utilisateurService.ajouterUtilisateur(utilisateur);
			utilisateurService.getUtilisateurByID(utilisateur.getNoUtilisateur());
			assertTrue(false);
		}
		catch (Exception e) {
			System.out.println("echec email");
		}
		
	}
	

}
