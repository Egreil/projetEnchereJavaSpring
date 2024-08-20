package fr.eni.enchere.dal;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;

public class EnchereRowMapper implements RowMapper<Enchere> {
	
	UtilisateurRepository utilisateurRepository;
    ArticleVenduRepository articleVenduRepository;
  
	public EnchereRowMapper(UtilisateurRepository utilisateurRepository,
			ArticleVenduRepository articleVenduRepository) {
		super();
		this.utilisateurRepository = utilisateurRepository;
		this.articleVenduRepository = articleVenduRepository;
	}

	public Enchere mapRow(ResultSet rs, int rowNum) throws SQLException {

		Enchere enchereResult = new Enchere();
		enchereResult.setNoEnchere(rs.getLong("noEnchere"));
		if (rs.getDate("dateEnchere") != null)
			enchereResult.setDateEnchere(rs.getDate("dateEnchere").toLocalDate());
		enchereResult.setMontantEnchere(rs.getDouble("montantEnchere"));
		Utilisateur acheteur=utilisateurRepository.getUtilisateurByID(rs.getLong("noUtilisateur"));
		//System.out.println("acheteur : "+acheteur);
		ArticleVendu article=articleVenduRepository.getArticleByID(rs.getLong("noArticle"));
		//System.out.println("article : "+article);
		
		enchereResult.setUtilisateur(acheteur);
		enchereResult.setArticleVendu(article);

		
		return enchereResult;
	
	}

}
