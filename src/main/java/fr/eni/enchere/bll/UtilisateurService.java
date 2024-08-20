package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Utilisateur;

public interface UtilisateurService {
	public void ajouterUtilisateur(Utilisateur utilisateur);
	public void supprimerUtilisateur(Utilisateur utilisateur);
	public Utilisateur getUtilisateurByID(long id);
	public List<Utilisateur> findAll();
	public void update(Utilisateur utilisateur);
	public List<ArticleVendu> getArticlesByUser(Utilisateur utilisateur);
	public Utilisateur getUtilisateurByPseudo(String pseudo);
	public Utilisateur getUtilisateurByEmail(String email);
		
}
