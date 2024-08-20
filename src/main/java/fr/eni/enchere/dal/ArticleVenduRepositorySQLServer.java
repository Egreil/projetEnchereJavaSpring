package fr.eni.enchere.dal;

import java.nio.channels.SelectableChannel;
import java.time.LocalDate;
import java.util.List;

import javax.xml.transform.Source;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import ch.qos.logback.core.joran.conditional.IfAction;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;

@Repository
public class ArticleVenduRepositorySQLServer implements ArticleVenduRepository{

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private CategorieRepository categorieRepository;
	private UtilisateurRepository utilisateurRepository;
	

	public ArticleVenduRepositorySQLServer(JdbcTemplate jdbcTemplate,
			NamedParameterJdbcTemplate namedParameterJdbcTemplate, CategorieRepository categorieRepository,
			UtilisateurRepository utilisateurRepository) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.categorieRepository = categorieRepository;
		this.utilisateurRepository = utilisateurRepository;
	}

	@Override
	public void ajouterArticle(ArticleVendu articleVendu) {
		GeneratedKeyHolder keyHolder= new GeneratedKeyHolder();
		String sql="insert into Articles_vendus (nomArticle,"
				+ "description,dateDebutEncheres,dateFinEncheres,"
				+ "prixInitial,prixVente,etatVente,noUtilisateur,noCategorie) "
				+ "values (:nomArticle,:description,"
				+ ":dateDebutEncheres,:dateFinEncheres,:prixInitial,:prixVente"
				+ ",:etatVente,:noUtilisateur,:noCategorie);";
		
		MapSqlParameterSource source= new MapSqlParameterSource();
			source.addValue("noArticle",articleVendu.getNoArticle());
			source.addValue("nomArticle", articleVendu.getNomArticle());
			source.addValue("description", articleVendu.getDescription());
			source.addValue("dateDebutEncheres", articleVendu.getDateDebutEncheres());
			source.addValue("dateFinEncheres", articleVendu.getDateFinEncheres());
			source.addValue("prixInitial", articleVendu.getPrixInitial());
			source.addValue("prixVente", articleVendu.getPrixVente());
			source.addValue("etatVente", "En attente");
			source.addValue("noUtilisateur", articleVendu.getVendeur().getNoUtilisateur());
			source.addValue("noCategorie", articleVendu.getCategorie().getNoCategorie());
			
			namedParameterJdbcTemplate.update(sql, source,keyHolder);
			
			if(keyHolder.getKey()!=null) {
				articleVendu.setNoArticle(keyHolder.getKey().longValue());
			}
	}

	@Override
	public void supprimerArticle(ArticleVendu articleVendu) {
		String sql="Delete from articles_vendus where noArticle=:noArticle;";
		MapSqlParameterSource source= new MapSqlParameterSource();
		source.addValue("noArticle",articleVendu.getNoArticle());
		namedParameterJdbcTemplate.update(sql, source);
		
	}

	@Override
	public ArticleVendu getArticleByID(long id) {
		String sql="select * from articles_vendus where noArticle=:noArticle;";
		MapSqlParameterSource source= new MapSqlParameterSource();
		source.addValue("noArticle",id);
		return namedParameterJdbcTemplate.queryForObject(sql, source, new ArticleVenduRowMapper(utilisateurRepository,categorieRepository));
	}

	@Override
	public List<ArticleVendu> findAll() {
		String sql="select * from articles_vendus as a inner join Utilisateurs as u on a.noUtilisateur=u.noUtilisateur";
		List<ArticleVendu> articlesVendus= jdbcTemplate.query(sql,new ArticleVenduRowMapper(utilisateurRepository,categorieRepository));
		return articlesVendus;
		
	}

	@Override
	public void update(ArticleVendu articleVendu) {
		String sql="update articles_vendus set nomArticle=:nomArticle,description=:description,"
				+"dateDebutEncheres=:dateDebutEncheres,dateFinEncheres=:dateFinEncheres,"
				+ "prixInitial=:prixInitial,prixVente=:prixVente,etatVente=:etatVente, "
				+ "noUtilisateur=:noUtilisateur,noCategorie=:noCategorie where noArticle=:noArticle;";
		MapSqlParameterSource source= new MapSqlParameterSource();
		source.addValue("noArticle",articleVendu.getNoArticle());
		source.addValue("nomArticle", articleVendu.getNomArticle());
		source.addValue("description", articleVendu.getDescription());
		source.addValue("dateDebutEncheres", articleVendu.getDateDebutEncheres());
		source.addValue("dateFinEncheres", articleVendu.getDateFinEncheres());
		source.addValue("prixInitial", articleVendu.getPrixInitial());
		source.addValue("prixVente", articleVendu.getPrixVente());
		source.addValue("etatVente", articleVendu.getEtatVente());
		source.addValue("noUtilisateur", articleVendu.getVendeur().getNoUtilisateur());
		source.addValue("noCategorie", articleVendu.getCategorie().getNoCategorie());
		
		namedParameterJdbcTemplate.update(sql, source);
	}
	
	
	public void updateStatut(ArticleVendu articleVendu) {
		if(LocalDate.now().isAfter(articleVendu.getDateDebutEncheres())) {
			articleVendu.setEtatVente("En cours");
		
			if(LocalDate.now().isAfter(articleVendu.getDateFinEncheres())) {
				articleVendu.setEtatVente("Terminee");
			}
		}
		this.update(articleVendu);
	}
	
	public Retrait getLieuRetraitDArticle(ArticleVendu articleVendu) {
		String sql="select r.noArticle,r.rue,r.codePostal,r.ville from articles_vendus as a inner join retrait as r on a.noArticle=r.noArticle where r.noArticle=:noArticle;";
		MapSqlParameterSource source= new MapSqlParameterSource();
		source.addValue("noArticle",articleVendu.getNoArticle() );
		return namedParameterJdbcTemplate.queryForObject(sql, source,new BeanPropertyRowMapper<>(Retrait.class));
	}
	

}
