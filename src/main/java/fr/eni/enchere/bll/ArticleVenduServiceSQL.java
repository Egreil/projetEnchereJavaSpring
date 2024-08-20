package fr.eni.enchere.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.ArticleVenduRepository;

@Service
public class ArticleVenduServiceSQL implements ArticleVenduService{

	ArticleVenduRepository articleVenduRepository;

	public ArticleVenduServiceSQL(ArticleVenduRepository articleVenduRepository) {
		super();
		this.articleVenduRepository = articleVenduRepository;
	}

	@Override
	public void ajouterArticle(ArticleVendu articleVendu) {
		articleVenduRepository.ajouterArticle(articleVendu);
		
	}

	@Override
	public void supprimerArticle(ArticleVendu articleVendu) {
		if(articleVendu.getEtatVente().equals("En attente")) {
			articleVenduRepository.supprimerArticle(articleVendu);
		}
	}

	@Override
	public ArticleVendu getArticleByID(long id) {
		return articleVenduRepository.getArticleByID(id);
	}

	@Override
	public List<ArticleVendu> findAll() {
		return articleVenduRepository.findAll();
	}

	@Override
	public void update(ArticleVendu articleVendu) {
		articleVenduRepository.update(articleVendu);
		
	}

	@Override
	public List<ArticleVendu> rechercheArticles(String nomArticleContient,Categorie categorie) {
		List<ArticleVendu> resultatRechercheArticleVendus=articleVenduRepository.findAll();
		if(nomArticleContient!="") {
		resultatRechercheArticleVendus = articleVenduRepository.findAll().stream()
				.filter(a->a.getNomArticle().contains(nomArticleContient)).toList();
		}
		if(categorie!=null) {
			resultatRechercheArticleVendus=resultatRechercheArticleVendus.stream().filter(a->a.getCategorie().getNoCategorie()==categorie.getNoCategorie()).toList();
		}
		return resultatRechercheArticleVendus;
	}
	
	@Override
	public void updateStatut(ArticleVendu articleVendu) {
		articleVenduRepository.updateStatut(articleVendu);
	}
	
	
	
}
