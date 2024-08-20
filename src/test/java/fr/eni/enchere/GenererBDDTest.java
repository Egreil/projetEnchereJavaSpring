package fr.eni.enchere;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Year;

import org.hibernate.validator.internal.util.privilegedactions.NewInstance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.enchere.bll.ArticleVenduService;
import fr.eni.enchere.bll.CategorieService;
import fr.eni.enchere.bll.EnchereService;
import fr.eni.enchere.bll.UtilisateurService;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.RepositoryReset;

@SpringBootTest
class GenererBDDTest {
	
	@Autowired
	CategorieService categorieService;
	@Autowired
	UtilisateurService utilisateurService;
	@Autowired
	ArticleVenduService articleVenduService;
	@Autowired
	EnchereService enchereService;
	@Autowired
	RepositoryReset repositoryReset;
	
	@Test
	void EchantillonBDD() {
		//repositoryReset.resetBDD();
		
		Categorie infoCategorie= new Categorie("Informatique");
		Categorie ammeublementCategorie=new Categorie("Ammeublement");
		Categorie vetementCategorie=new Categorie("Vetement");
		Categorie sportCategorie=new Categorie("sport&Loisir");
		
		categorieService.ajouterCategorie(sportCategorie);
		categorieService.ajouterCategorie(vetementCategorie);
		categorieService.ajouterCategorie(ammeublementCategorie);
		categorieService.ajouterCategorie(infoCategorie);
		
		
		Utilisateur vendeur=new Utilisateur("vendeur","Ven","Deur","vendeur@","10","rueduvendeur","01001","VENDEUR","vendeur",150,false);
		Utilisateur acheteur=new Utilisateur("acheteur","Ach","Teur","acheteur@","10","rueduacheteur","01001","ACHETEUR","acheteur",150,false);
		utilisateurService.ajouterUtilisateur(acheteur);
		utilisateurService.ajouterUtilisateur(vendeur);
	  	Utilisateur user2= new Utilisateur("admin", "nono", "abdel", "user2@example.com", "25335445","rue de nono","5258", "rennes", "admin", 10, true);
	  	Utilisateur user3=new Utilisateur("user", "toto","tarmp","user3@example.com","25335445","rue de Nail","5258", "Le mens", "user", 6456, false);
	  	utilisateurService.ajouterUtilisateur(user2);
	  	utilisateurService.ajouterUtilisateur(user3);
		
		ArticleVendu articleVendu=new ArticleVendu("souris","filaire",LocalDate.now(),LocalDate.now().plusDays(3),10,"en cours",vendeur,infoCategorie);
		
		
	}

}
