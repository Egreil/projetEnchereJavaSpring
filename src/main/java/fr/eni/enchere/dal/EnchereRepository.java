package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Enchere;


public interface EnchereRepository {
	public void ajouterEnchere(Enchere enchere);
	public void supprimerEnchere(Enchere enchere);
	public Enchere  getEnchereByID(long id);
	public List<Enchere> findAll();
	public void update(Enchere enchere);
}
