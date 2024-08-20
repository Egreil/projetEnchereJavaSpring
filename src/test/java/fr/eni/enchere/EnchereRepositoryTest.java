package fr.eni.enchere;


import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ArticleVenduRepository;
import fr.eni.enchere.dal.CategorieRepository;
import fr.eni.enchere.dal.EnchereRepository;
import fr.eni.enchere.dal.UtilisateurRepository;


import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class EnchereRepositoryTest {

	@Autowired
    private CategorieRepository categorieRepository;
	@Autowired
	private EnchereRepository enchereRepository;
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	@Autowired
	private ArticleVenduRepository articleVenduRepository;

	
	@Test //OK
	void ajoutEnchere() {
		//Creation de l'article
		Categorie categorie=new Categorie("cat1");
		categorieRepository.ajouterCategorie(categorie);
		Utilisateur vendeur= new Utilisateur("pseudo","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurRepository.ajouterUtilisateur(vendeur);// A completer avec le code d'Abdel
		ArticleVendu article= new ArticleVendu("nomArticle","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",vendeur,categorie);
		articleVenduRepository.ajouterArticle(article);
		//Creation Utilisateur acheteur
		Utilisateur acheteur= new Utilisateur("pseudo","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurRepository.ajouterUtilisateur(acheteur);
		
		//rassemblement des donnes dans la creation de l'enchere
		Enchere enchere= new Enchere(acheteur,article,LocalDate.of(2009, 3, 10),3.3);
		enchereRepository.ajouterEnchere(enchere);
	
		
	}
	@Test // OK
	public void supprimerEnchere() {
		//Creation de l'article
		Categorie categorie=new Categorie("cat1");
		categorieRepository.ajouterCategorie(categorie);
		Utilisateur vendeur= new Utilisateur("pseudo","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurRepository.ajouterUtilisateur(vendeur);// A completer avec le code d'Abdel
		ArticleVendu article= new ArticleVendu("nomArticle","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",vendeur,categorie);
		articleVenduRepository.ajouterArticle(article);
		//Creation Utilisateur acheteur
		Utilisateur acheteur= new Utilisateur("pseudo","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurRepository.ajouterUtilisateur(acheteur);
		//rassemblement des donnes dans la creation de l'enchere
		Enchere enchere= new Enchere(acheteur,article,LocalDate.of(2009, 3, 10),3.3);
		enchereRepository.ajouterEnchere(enchere);
		
	    // On le supprime
	    enchereRepository.supprimerEnchere(enchere);
		try {
			System.out.println(enchereRepository.getEnchereByID(enchere.getNoEnchere()));
			assertTrue(false);
		}
		catch (Exception e) {
			assertTrue(true);
		}
	    
	}
	
	@Test // OK
	void findAllEnchereTest() {
		System.out.println(enchereRepository.findAll().size());
	}
	
	
	@Test // OK
	void getEnchereByID() {
		
		Categorie categorie=new Categorie("cat1Update");
		categorieRepository.ajouterCategorie(categorie);
		Utilisateur vendeur= new Utilisateur("update","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurRepository.ajouterUtilisateur(vendeur);// A completer avec le code d'Abdel
		ArticleVendu article= new ArticleVendu("nomArticle","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",vendeur,categorie);
		articleVenduRepository.ajouterArticle(article);
		//Creation Utilisateur acheteur
		Utilisateur acheteur= new Utilisateur("pseudo","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurRepository.ajouterUtilisateur(acheteur);
		
		//Creation Enchere
		Enchere enchere= new Enchere(acheteur,article,LocalDate.of(2009, 3, 10),3.3);
		enchereRepository.ajouterEnchere(enchere);
		
		System.out.println("GET: "+ enchereRepository.getEnchereByID(enchere.getNoEnchere()));
	}
	
	@Test //OK
	void updateEncherTest() {
		Categorie categorie=new Categorie("cat1Update");
		categorieRepository.ajouterCategorie(categorie);
		Utilisateur vendeur= new Utilisateur("update","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurRepository.ajouterUtilisateur(vendeur);// A completer avec le code d'Abdel
		ArticleVendu article= new ArticleVendu("nomArticle","description",LocalDate.of(2009, 3, 10),LocalDate.of(2025, 3, 10),0.0,"en cours",vendeur,categorie);
		articleVenduRepository.ajouterArticle(article);
		//Creation Utilisateur acheteur
		Utilisateur acheteur= new Utilisateur("pseudo","nom","prenom","mail","tel","rue","codePostal","ville","mdp",10,false);
		utilisateurRepository.ajouterUtilisateur(acheteur);
		
		//Creation Enchere
		Enchere enchere= new Enchere(acheteur,article,LocalDate.of(2009, 3, 10),3.3);
		enchereRepository.ajouterEnchere(enchere);
		System.out.println(enchere);
		
		Enchere enchereModifer= new Enchere(enchere.getNoEnchere(),acheteur,article,LocalDate.of(2028, 7, 10),20.5);
		enchereRepository.update(enchereModifer);
	}
	
	
}
