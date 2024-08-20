package fr.eni.enchere;



import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.module.ModuleDescriptor.Exports.Modifier;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.UtilisateurRepository;

@SpringBootTest
class UtilisateurRepositoryTests {
	
	@Autowired
	UtilisateurRepository utilisateurRepository;

   
   
    @Test
	void testFindAll() {
    	System.out.println("FIND ALL:");
		List<Utilisateur> list = utilisateurRepository.findAll();
		list.forEach(System.out::println);
		System.out.println(utilisateurRepository.findAll());
	}
	
    @Test
	void ajouterUtilisateur() {
    	System.out.println("Ajout:");
    	//admin-admin
    	Utilisateur user2= new Utilisateur("admin", "nono", "abdel", "user2@example.com", "25335445","rue de nono","5258", "rennes", "admin", 2, false);
    	//user-user
    	Utilisateur user3=new Utilisateur("user", "toto","tarmp","user3@example.com","25335445","rue de Nail","5258", "Le mens", "user", 6456, false);
    	utilisateurRepository.ajouterUtilisateur(user2);
    	utilisateurRepository.ajouterUtilisateur(user3);
	}
	
    @Test
	void getUtilisateurByID() {
    	System.out.println("GET:");
	  	Utilisateur utilisateur = new Utilisateur("blabla", "nono2", "abdel", "user2@example.com", "25335445", "rue de nono", "5258", "rennes", "354", 2, false);
  		utilisateurRepository.ajouterUtilisateur(utilisateur);
		System.out.println(utilisateurRepository.getUtilisateurByID(utilisateur.getNoUtilisateur()));//.getNoUtilisateur());
		
	}
	@Test
	void modifierUtilisateur() {
        utilisateurRepository.ajouterUtilisateur(new Utilisateur("user5", "nono", "abdel", "user2@example.com", "25335445", "rue de nono", "5258", "rennes", "354", 2, false));
		Utilisateur utilisateur = new Utilisateur(8,"blabla2", "nono", "abdel", "user2@example.com", "25335445", "rue de nono", "5258", "rennes", "354", 2, false);
		utilisateurRepository.update(utilisateur);
		System.out.println();
		//assertEquals("user4",utilisateurRepository.getUtilisateurByID(utilisateurRepository.findAll().size()));
		
	}
	
	@Test
	void supprimerUtilisateur() {
		System.out.println("DELETE:");
		Utilisateur utilisateur=new Utilisateur("user6", "nono", "abdel", "user2@example.com", "25335445", "rue de nono", "5258", "rennes", "354", 2, false);
		utilisateurRepository.ajouterUtilisateur(utilisateur);
		utilisateurRepository.supprimerUtilisateur(utilisateur);
		try {
			utilisateurRepository.getUtilisateurByID(utilisateur.getNoUtilisateur());
			assertTrue(false);
		}
		catch (Exception e) {
			System.out.println("utilisateur supprimé");
			assertTrue(true);
		}
	}
	@Test
	void findByPseudo() {
		System.out.println("Find by pseudo:");
		Utilisateur utilisateur=new Utilisateur("fbp", "nono", "abdel", "user2@example.com", "25335445", "rue de nono", "5258", "rennes", "354", 2, false);
		utilisateurRepository.ajouterUtilisateur(utilisateur);
		//System.out.println(utilisateur);
		System.out.println(utilisateurRepository.findByPseudo(utilisateur.getPseudo()));
		 
		}
	@Test
	void findByEmail() {
		System.out.println("Find by email:");
		Utilisateur utilisateur=new Utilisateur("fbe", "nono", "abdel", "userEMAIL@example.com", "25335445", "rue de nono", "5258", "rennes", "354", 2, false);
		utilisateurRepository.ajouterUtilisateur(utilisateur);
		//System.out.println(utilisateur);
		System.out.println(utilisateurRepository.findByPseudo(utilisateur.getPseudo()));
		 
		}
	
}


