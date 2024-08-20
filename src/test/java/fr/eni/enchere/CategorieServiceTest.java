package fr.eni.enchere;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.RepositoryReset;
import fr.eni.enchere.bll.CategorieService;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
class CategorieServiceTest {

	@Autowired
	CategorieService categorieService;
	@Autowired
	RepositoryReset repositoryReset;
	
	@BeforeAll
	public void init() {
		repositoryReset.resetBDD();
	}

	@Test
	void ajoutDeCategorie() {
		Categorie categorie1= new Categorie("patate");
		Categorie categorie2=new Categorie("saucissons");
		categorieService.ajouterCategorie(categorie1);
		categorieService.ajouterCategorie(categorie2);
	}
	
	@Test 
	void findAll(){
		System.out.println(categorieService.findAll());
	}
	
	@Test
	void supprimerCategorie() {
		Categorie categorie=new Categorie("supprimer");
		categorieService.ajouterCategorie(categorie);
		categorieService.supprimerCategorie(categorie);
		try {
			categorieService.getCategorieByID(categorie.getNoCategorie());
			assertTrue(false);
		}
		catch(Exception e) {
			assertTrue(true);
		}
		
	}
	@Test
	void getCategorieByID() {
		Categorie categorie=new Categorie(categorieService.findAll().size(),"saucissons");
		categorieService.ajouterCategorie(categorie);
		System.out.println(categorieService.getCategorieByID(categorie.getNoCategorie()).getLibelle());
		
	}
	@Test
	void modifierCategorie() {
		categorieService.ajouterCategorie(new Categorie("poisson"));
		Categorie categorie=new Categorie(1l,"plage");
		categorieService.updateCategorie(categorie);
		
	}
	@Test
	void existenceCategorie() {
		Categorie categorie=new Categorie("serviette");
		categorieService.ajouterCategorie(categorie);
		assertTrue(categorieService.verifierExistenceLibelleCategorie(categorie));
		Categorie categorieFausse=new Categorie("faux");
		assertTrue(!categorieService.verifierExistenceLibelleCategorie(categorieFausse));
	}


}
