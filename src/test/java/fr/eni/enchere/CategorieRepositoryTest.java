package fr.eni.enchere;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.DebugBeanDefinitionParser;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.CategorieRepository;
import fr.eni.enchere.dal.CategorieRepositorySQLServer;

@SpringBootTest
class CategorieRepositoryTest{
	
	@Autowired
	private CategorieRepositorySQLServer categorieRepository;


	@Test
	void ajoutDeCategorie() {
		Categorie categorie1= new Categorie("patate");
		Categorie categorie2=new Categorie("saucissons");
		categorieRepository.ajouterCategorie(categorie1);
		categorieRepository.ajouterCategorie(categorie2);
	}
	
	@Test 
	void findAll(){
		System.out.println(categorieRepository.findAll());
	}
	
	@Test
	void supprimerCategorie() {
		Categorie categorie=new Categorie("supprimer");
		categorieRepository.ajouterCategorie(categorie);
		categorieRepository.supprimerCategorie(categorie);
		try {
			categorieRepository.getCategorieByID(categorie.getNoCategorie());
			assertTrue(false);
		}
		catch(Exception e) {
			assertTrue(true);
		}
	}
	@Test
	void getCategorieByID() {
		Categorie categorie=new Categorie(categorieRepository.findAll().size(),"saucissons");
		categorieRepository.ajouterCategorie(categorie);
		System.out.println(categorieRepository.getCategorieByID(categorie.getNoCategorie()).getLibelle());
		
	}
	@Test
	void modifierCategorie() {
		categorieRepository.ajouterCategorie(new Categorie("poisson"));
		Categorie categorie=new Categorie(1l,"plage");
		categorieRepository.updateCategorie(categorie);
		//System.out.println(categorieRepository.getCategorieByID(categorieRepository.findAll().size()));
		//assertEquals("plage",categorieRepository.getCategorieByID(categorieRepository.findAll().size()));
		
	}
	@Test
	void existenceCategorie() {
		Categorie categorie=new Categorie("serviette");
		categorieRepository.ajouterCategorie(categorie);
		assertTrue(categorieRepository.verifierExistenceLibelleCategorie(categorie));
		Categorie categorieFausse=new Categorie("faux");
		assertTrue(!categorieRepository.verifierExistenceLibelleCategorie(categorieFausse));
	}

}
