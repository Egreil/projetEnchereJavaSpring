package fr.eni.enchere.dal;

import java.nio.channels.SelectableChannel;
import java.util.List;

import org.springframework.boot.context.properties.bind.Nested;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.security.config.DebugBeanDefinitionParser;
import org.springframework.stereotype.Repository;

import fr.eni.enchere.bo.Categorie;

@Repository
public class CategorieRepositorySQLServer implements CategorieRepository{

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public CategorieRepositorySQLServer(JdbcTemplate jdbcTemplate,
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	

	@Override
	public void ajouterCategorie(Categorie categorie) {
		GeneratedKeyHolder keyHolder= new GeneratedKeyHolder();
		String sql="insert into CATEGORIES (libelle) values (:libelle);";
		
		BeanPropertySqlParameterSource source= new BeanPropertySqlParameterSource(categorie);
			
			namedParameterJdbcTemplate.update(sql, source,keyHolder);
			
			if(keyHolder.getKey()!=null) {
				categorie.setNoCategorie(keyHolder.getKey().longValue());
			}
		
	}

	@Override
	public void supprimerCategorie(Categorie categorie) {
		String sql="Delete from Categories where noCategorie=:noCategorie;";
		MapSqlParameterSource source= new MapSqlParameterSource();
		source.addValue("noCategorie",categorie.getNoCategorie());
		namedParameterJdbcTemplate.update(sql, source);
		
	}

	@Override
	public void updateCategorie(Categorie categorie) {
		String sql="Update Categories Set libelle=:libelle where noCategorie=:noCategorie;";
		BeanPropertySqlParameterSource source= new BeanPropertySqlParameterSource(categorie);
		namedParameterJdbcTemplate.update(sql, source);
		
	}

	@Override
	public List<Categorie> findAll() {
		String sql="select * from categories;";
		List<Categorie> categories=jdbcTemplate.query(sql,new BeanPropertyRowMapper(Categorie.class));
		return categories;
	}

	@Override
	public Categorie getCategorieByID(long id) {
		String sql="Select * from categories where noCategorie=:noCategorie";
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("noCategorie", id);
		Categorie categorie=namedParameterJdbcTemplate.queryForObject(sql, source, new BeanPropertyRowMapper<>(Categorie.class));
		return categorie;
	}
	
	public boolean verifierExistenceLibelleCategorie(Categorie categorie) {
		String sql="select Count(libelle) from Categories where libelle=:libelle;";
		BeanPropertySqlParameterSource source= new BeanPropertySqlParameterSource(categorie);
		System.out.println(namedParameterJdbcTemplate.queryForObject(sql, source, Integer.class));
		return (0<namedParameterJdbcTemplate.queryForObject(sql, source, Integer.class));
	}

}
