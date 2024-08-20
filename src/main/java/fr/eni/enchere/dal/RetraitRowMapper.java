package fr.eni.enchere.dal;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Retrait;

public class RetraitRowMapper implements RowMapper<Retrait>{
	
	private ArticleVenduRepository articleVenduRepository;

	public RetraitRowMapper(ArticleVenduRepository articleVenduRepository) {
		super();
		this.articleVenduRepository = articleVenduRepository;
	}

	@Override
	public Retrait mapRow(ResultSet rs, int rowNum) throws SQLException {
		Retrait retraitResult=new Retrait();
		retraitResult.setCodePostal(rs.getString("codePostal"));
		retraitResult.setRue(rs.getString("rue"));
		retraitResult.setVille(rs.getString("ville"));
		
		ArticleVendu articleVendu=articleVenduRepository.getArticleByID(rs.getLong("noArticle"));
		retraitResult.setArticleVendu(articleVendu);
		return retraitResult;
	}
	
	

}
