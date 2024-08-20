package fr.eni.enchere.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.CategorieRepository;

@Service
public class CategorieServiceSQL implements CategorieService{

	private CategorieRepository categorieRepository;

	public CategorieServiceSQL(CategorieRepository categorieRepository) {
		super();
		this.categorieRepository = categorieRepository;
	}

	@Override
	public void ajouterCategorie(Categorie categorie) {
		categorieRepository.ajouterCategorie(categorie);
		
	}

	@Override
	public void supprimerCategorie(Categorie categorie) {
		categorieRepository.supprimerCategorie(categorie);
		
	}

	@Override
	public void updateCategorie(Categorie categorie) {
		categorieRepository.updateCategorie(categorie);
		
	}

	@Override
	public List<Categorie> findAll() {
		
		return categorieRepository.findAll();
	}

	@Override
	public Categorie getCategorieByID(long id) {
		return categorieRepository.getCategorieByID(id);
	}

	@Override
	public boolean verifierExistenceLibelleCategorie(Categorie categorie) {
		
		return categorieRepository.verifierExistenceLibelleCategorie(categorie);
	}
	
	
	

}
