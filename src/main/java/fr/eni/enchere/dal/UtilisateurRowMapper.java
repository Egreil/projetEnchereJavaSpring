package fr.eni.enchere.dal;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Utilisateur;



public class UtilisateurRowMapper implements RowMapper<Utilisateur> {


	@Override
	public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {

		Utilisateur utilisateur = new Utilisateur();
		
		utilisateur.setNoUtilisateur(rs.getLong("noUtilisateur"));
		utilisateur.setPseudo(rs.getString("pseudo"));
		utilisateur.setNom(rs.getString("nom"));
		utilisateur.setPrenom(rs.getString("prenom"));
		utilisateur.setEmail(rs.getString("email"));
		utilisateur.setTelephone (rs.getString("telephone"));
		utilisateur.setRue (rs.getString("rue"));
		utilisateur.setCodePostal(rs.getString("codePostal"));
		utilisateur.setVille(rs.getString("ville"));
		utilisateur.setMotDePasse(rs.getString("motDePasse"));
		utilisateur.setCredit (rs.getInt("Credit"));
		utilisateur.setAdministrateur (rs.getBoolean("administrateur"));
//		
//		if (rs.getLong("noArticle") != 0) {
//			ArticleVendu ArticleVend = new ArticleVendu();
//			
//			utilisateur.setArticleVendu(ArticleVend);
//		}
		
		
		return utilisateur;
	}

}



