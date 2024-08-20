package fr.eni.enchere.dal;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;

@Repository
public class EnchereRepositorySQLServer implements EnchereRepository {

	

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private UtilisateurRepository utilisateurRepository;
	private ArticleVenduRepository articleVenduRepository;
	
	
	
	public EnchereRepositorySQLServer(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate,
			UtilisateurRepository utilisateurRepository, ArticleVenduRepository articleVenduRepository) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.utilisateurRepository = utilisateurRepository;
		this.articleVenduRepository = articleVenduRepository;
	}


	
    @Override
	public void ajouterEnchere(Enchere enchere) {
		GeneratedKeyHolder keyHolder= new GeneratedKeyHolder();
		String sql="INSERT INTO Encheres (noUtilisateur, noArticle, dateEnchere,montantEnchere)"
				+ "values("
				+ ":noUtilisateur, :noArticle, "
				+ ":dateEnchere, :montantEnchere );";
		
		MapSqlParameterSource source= new MapSqlParameterSource();
	    source.addValue("noUtilisateur", enchere.getUtilisateur().getNoUtilisateur());
		source.addValue("noArticle", enchere.getArticleVendu().getNoArticle());
		source.addValue("dateEnchere", enchere.getDateEnchere());
		source.addValue("montantEnchere", enchere.getMontantEnchere());
		namedParameterJdbcTemplate.update(sql, source,keyHolder);
		
		if(keyHolder.getKey()!=null) {
			enchere.setNoEnchere(keyHolder.getKey().longValue());
		}
	}



	@Override
	public void supprimerEnchere(Enchere enchere) {
		String sql="DELETE FROM Encheres WHERE noEnchere =:noEnchere;";
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("noEnchere", enchere.getNoEnchere());
		namedParameterJdbcTemplate.update(sql, source);
	}


	@Override
	public Enchere getEnchereByID(long id) {
		String sql="SELECT * FROM Encheres WHERE noEnchere =:noEnchere;";
		MapSqlParameterSource source= new MapSqlParameterSource();
		source.addValue("noEnchere", id);
		return namedParameterJdbcTemplate.queryForObject(sql, source, new EnchereRowMapper(utilisateurRepository,articleVenduRepository));
	}



	@Override
	public List<Enchere> findAll() {
		String sql=" SELECT * FROM Encheres ;";
		List<Enchere> enchere= jdbcTemplate.query(sql,new EnchereRowMapper(utilisateurRepository, articleVenduRepository));
		return enchere;
	}



	@Override
	public void update(Enchere enchere) {
		String sql=" UPDATE Encheres SET noUtilisateur=:noUtilisateur,"
				+ "dateEnchere=:dateEnchere,montantEnchere=:montantEnchere"
				+ " where noEnchere=:noEnchere;";
		MapSqlParameterSource source= new MapSqlParameterSource();
		    source.addValue("noUtilisateur", enchere.getUtilisateur().getNoUtilisateur());
			source.addValue("noEnchere", enchere.getNoEnchere());
			source.addValue("dateEnchere", enchere.getDateEnchere());
			source.addValue("montantEnchere", enchere.getMontantEnchere());
		
		namedParameterJdbcTemplate.update(sql, source);
		
		
	}
	
}
