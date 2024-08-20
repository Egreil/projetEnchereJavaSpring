package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Categorie;

public interface CategorieService {
	
	public void ajouterCategorie(Categorie categorie);
	public void supprimerCategorie(Categorie categorie);
	public void updateCategorie(Categorie categorie);
	public List<Categorie> findAll();
	public Categorie getCategorieByID( long id);
	
	public boolean verifierExistenceLibelleCategorie(Categorie categorie);
}
