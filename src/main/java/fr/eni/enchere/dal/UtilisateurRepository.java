package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurRepository {
	
	public void createTable();
	public void ajouterUtilisateur(Utilisateur utilisateur);
	public void supprimerUtilisateur(Utilisateur utilisateur);
	public Utilisateur getUtilisateurByID(long id);
	public List<Utilisateur> findAll();
	public void update(Utilisateur utilisateur);
	
	public Utilisateur findByEmail(String user);
	public Utilisateur findByPseudo(String user);
}
