package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Retrait;

public interface ArticleVenduRepository {
	public void ajouterArticle(ArticleVendu articleVendu);
	public void supprimerArticle(ArticleVendu articleVendu);
	public ArticleVendu getArticleByID(long id);
	public List<ArticleVendu> findAll();
	
	public void update(ArticleVendu articleVendu);
	public void updateStatut(ArticleVendu articleVendu);
	public Retrait getLieuRetraitDArticle(ArticleVendu articleVendu);

}
