package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Retrait;

public interface RetraitRepository {
	public void ajouterRetrait(Retrait retrait);
	public void supprimerRetrait(Retrait retrait);
	public void updateRetrait(Retrait retrait);
	public Retrait getRetraitById(long idRetrait);
	public List<Retrait> findAll();
}
