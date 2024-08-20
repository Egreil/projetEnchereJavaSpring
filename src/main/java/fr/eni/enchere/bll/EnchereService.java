package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;

public interface EnchereService {
	public void ajouterEnchere(Enchere enchere);
	public void supprimerEnchere(Enchere enchere);
	public Enchere getEnchereByID(long id);
	public List<Enchere> findAll();
	public void update(Enchere enchere);
	List<Enchere> rechercheEncheres(List<ArticleVendu> articlesRecherches);
	public List<Enchere> encheresSurArticle(ArticleVendu articleVendu);
	public Enchere enchereMaxParArticle(List<Enchere> encheresSurArticle);
}

