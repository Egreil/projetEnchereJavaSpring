package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Retrait;

public interface RetraitService {
	public void ajouterRetrait(Retrait retrait);
	public void supprimerRetrait(Retrait retrait);
	public void updateRetrait(Retrait retrait);
	public Retrait getRetraitById(long idRetrait);
	public List<Retrait> findAll();
}
