package fr.eni.enchere.dal;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;


public class ArticleVenduRowMapper implements RowMapper<ArticleVendu>{
	

	UtilisateurRepository utilisateurRepository;
	CategorieRepository categorieRepository;

	public ArticleVenduRowMapper(UtilisateurRepository utilisateurRepository, CategorieRepository categorieRepository) {
		super();
		this.utilisateurRepository = utilisateurRepository;
		this.categorieRepository = categorieRepository;
	}
	

	@Override
	public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
		ArticleVendu articleVenduResult= new ArticleVendu();
		articleVenduResult.setNoArticle(rs.getLong("noArticle"));
		articleVenduResult.setNomArticle(rs.getString("nomArticle"));
		articleVenduResult.setDescription(rs.getString("description"));
		articleVenduResult.setDateDebutEncheres(rs.getDate("dateDebutEncheres").toLocalDate());
		articleVenduResult.setDateFinEncheres(rs.getDate("dateFinEncheres").toLocalDate());
		articleVenduResult.setPrixInitial(rs.getDouble("prixInitial"));
		articleVenduResult.setPrixVente(rs.getDouble("prixVente"));
		articleVenduResult.setEtatVente(rs.getString("etatVente"));
		
		Utilisateur vendeur=utilisateurRepository.getUtilisateurByID(rs.getLong("noUtilisateur"));
		//System.out.println("vendeur : "+vendeur);
		Categorie categorie=categorieRepository.getCategorieByID(rs.getLong("noCategorie"));
		//System.out.println("categorie : "+categorie);
		articleVenduResult.setVendeur(vendeur);
		articleVenduResult.setCategorie(categorie);
				
		return articleVenduResult;
	}

}
