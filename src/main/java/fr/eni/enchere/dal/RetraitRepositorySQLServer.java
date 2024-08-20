package fr.eni.enchere.dal;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Retrait;

@Repository
public class RetraitRepositorySQLServer implements RetraitRepository{
	
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private ArticleVenduRepository articleVenduRepository;
	

	public RetraitRepositorySQLServer(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
			ArticleVenduRepository articleVenduRepository) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.articleVenduRepository = articleVenduRepository;
	}
	

	public void ajouterRetrait(Retrait retrait) {
		String sql="insert into Retraits(noArticle,rue,codePostal,ville) values (:noArticle,:rue,:codePostal,:ville);";
		MapSqlParameterSource source= new MapSqlParameterSource();
		source.addValue("noArticle",retrait.getArticleVendu().getNoArticle());
		source.addValue("rue", retrait.getRue());
		source.addValue("codePostal", retrait.getCodePostal());
		source.addValue("ville", retrait.getVille());
		namedParameterJdbcTemplate.update(sql, source);
	}

	@Override
	public void supprimerRetrait(Retrait retrait) {
		String sql="Delete from retraits where noArticle=:noArticle;";
		MapSqlParameterSource source= new MapSqlParameterSource();
		source.addValue("noArticle",retrait.getArticleVendu().getNoArticle());
		namedParameterJdbcTemplate.update(sql, source);
		
	}

	@Override
	public void updateRetrait(Retrait retrait) {
		String sql="Update Retraits Set rue=:rue,codePostal=:codePostal,ville=:ville where noArticle=:noArticle;";
		MapSqlParameterSource source= new MapSqlParameterSource();
		source.addValue("noArticle",retrait.getArticleVendu().getNoArticle());
		source.addValue("rue", retrait.getRue());
		source.addValue("codePostal", retrait.getCodePostal());
		source.addValue("ville", retrait.getVille());
		namedParameterJdbcTemplate.update(sql, source);	
	}

	@Override
	public Retrait getRetraitById(long idRetrait) {
		String sql="Select * from retraits where noArticle=:noArticle";
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("noArticle", idRetrait);
		Retrait retrait=namedParameterJdbcTemplate.queryForObject(sql, source, new RetraitRowMapper(articleVenduRepository));
		return retrait;
	}

	@Override
	public List<Retrait> findAll() {
		String sql="select * from retraits;";
		List<Retrait> retraits=jdbcTemplate.query(sql,new RetraitRowMapper(articleVenduRepository));
		return retraits;
	};
	
//	public boolean verifierExistenceLieuRetrait(Categorie categorie) {
//		String sql="select Count(libelle) from Categories where libelle=:libelle;";
//		BeanPropertySqlParameterSource source= new BeanPropertySqlParameterSource(categorie);
//		System.out.println(namedParameterJdbcTemplate.queryForObject(sql, source, Integer.class));
//		return (0<namedParameterJdbcTemplate.queryForObject(sql, source, Integer.class));
//	}
	

}
