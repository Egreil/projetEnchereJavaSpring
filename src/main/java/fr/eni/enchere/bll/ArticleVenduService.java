package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Retrait;

public interface ArticleVenduService {
	public void ajouterArticle(ArticleVendu articleVendu);
	public void supprimerArticle(ArticleVendu articleVendu);
	public ArticleVendu getArticleByID(long id);
	public List<ArticleVendu> findAll();
	public void update(ArticleVendu articleVendu);
	public List<ArticleVendu> rechercheArticles(String nomArticleContient,Categorie categorie);
	//public Retrait getLieuRetraitDArticle(ArticleVendu articleVendu)
	public void updateStatut(ArticleVendu articleVendu);

}
