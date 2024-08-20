package fr.eni.enchere.dal;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.enchere.bo.Utilisateur;

@Repository
public class UtilisateurRepositorySQLServer  implements UtilisateurRepository{
	

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	public UtilisateurRepositorySQLServer(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	public void createTable() {
		jdbcTemplate.execute("CREATE TABLE UTILISATEURS("
				+ "noUtilisateur int primary key identity(1,1) not null,"
				+ "pseudo varchar(30) not null,"
				+ "nom varchar(30) not null,"
				+ "prenom varchar(30) not null,"
				+ "email varchar (255) not null,"
				+ "telephone varchar(15),"
				+ "rue varchar(30)not null,"
				+ "codePostal varchar(30) not null,"
				+ "ville varchar(30) not null,"
				+ "motDePasse varchar(30) not null, "
				+ "credit int not null, "
				+ "administrateurn boolean not null);");
	}

	
	//CROD
	
	@Override
	public void ajouterUtilisateur(Utilisateur utilisateur) {
		GeneratedKeyHolder key = new GeneratedKeyHolder();
		String sql = "INSERT INTO Utilisateurs(pseudo,nom,prenom,email,telephone,rue,codePostal,ville,motDePasse,credit,administrateur)VALUES"
			+ "(:pseudo,:nom,:prenom,:email,:telephone,:rue,:codePostal,:ville,:motDePasse,:credit,:administrateur) ";
		
		MapSqlParameterSource source = new  MapSqlParameterSource();
		//source.addValue("noUtilisateur", utilisateur.getNoUtilisateur());
		source.addValue("pseudo", utilisateur.getPseudo());
		source.addValue("nom", utilisateur.getNom());
		source.addValue("prenom", utilisateur.getPrenom());
		source.addValue("email", utilisateur.getEmail());
		source.addValue("telephone", utilisateur.getTelephone());
		source.addValue("rue", utilisateur.getRue());
		source.addValue("codePostal", utilisateur.getCodePostal());
		source.addValue("rue", utilisateur.getRue());
		source.addValue("ville", utilisateur.getVille());
		source.addValue("motDePasse", utilisateur.getMotDePasse());
		source.addValue("credit", utilisateur.getCredit());
		source.addValue("administrateur", utilisateur.isAdministrateur());
		namedParameterJdbcTemplate.update(sql, source, key);
		if (key.getKey() != null) {
			utilisateur.setNoUtilisateur(key.getKey().longValue());
		}
				
	}
	
	@Override
	 public List<Utilisateur> findAll() {
	        String sql = "SELECT noUtilisateur, pseudo,"
	        		+ " nom, prenom, email, telephone, "
	        		+ "rue, codePostal, ville, motDePasse,"
	        		+ " credit, administrateur "
	        		+ "FROM UTILISATEURS";
	        
	        List<Utilisateur> list = jdbcTemplate.query(sql, new UtilisateurRowMapper());
	        
	        return list;
	    }
	
	
	@Override
	public Utilisateur getUtilisateurByID(long id) {
		String sql = "select * from Utilisateurs where noUtilisateur = :noUtilisateur";
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("noUtilisateur",id);
		Utilisateur utilisateur = namedParameterJdbcTemplate.queryForObject(sql, source, 
		new BeanPropertyRowMapper<>(Utilisateur.class));
        return utilisateur;
		
	}

	@Override
	public void supprimerUtilisateur(Utilisateur utilisateur) {
        String sql = "delete from Utilisateurs where noUtilisateur = :noUtilisateur";
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("noUtilisateur", utilisateur.getNoUtilisateur());
		namedParameterJdbcTemplate.update(sql, source);
		
	}

		
	@Override
	public void update(Utilisateur utilisateur) {
		String sql = "update Utilisateurs set "
				+ " pseudo = :pseudo,nom = :nom, prenom = :prenom,email = :email, telephone = :telephone, rue = :rue,codePostal= :codePostal,ville =:ville,motDePasse= :motDePasse,credit = :credit,administrateur = :administrateur where noUtilisateur=:noUtilisateur;";
		
		MapSqlParameterSource source = new MapSqlParameterSource();
		source.addValue("noUtilisateur", utilisateur.getNoUtilisateur());
		source.addValue("pseudo", utilisateur.getPseudo());
		source.addValue("nom", utilisateur.getNom());
		source.addValue("prenom", utilisateur.getPrenom());
		source.addValue("email", utilisateur.getEmail());
		source.addValue("telephone", utilisateur.getTelephone());
		source.addValue("rue", utilisateur.getRue());
		source.addValue("codePostal", utilisateur.getCodePostal());
		source.addValue("ville", utilisateur.getVille());
		source.addValue("motDePasse", utilisateur.getMotDePasse());
		source.addValue("credit", utilisateur.getCredit());
		source.addValue("administrateur", utilisateur.isAdministrateur());
		
	
		namedParameterJdbcTemplate.update(sql, source);
		
	}
	
	public Utilisateur findByPseudo(String user) {
		Utilisateur utilisateur;
		try {
			String sql = "select * from Utilisateurs where pseudo = :pseudo";
			
			MapSqlParameterSource source = new MapSqlParameterSource();
			source.addValue("pseudo",user);
			
			utilisateur = namedParameterJdbcTemplate.queryForObject(sql, source,
			new UtilisateurRowMapper());
		}
		catch(Exception e){
			return null;
		}
			//System.out.println(user);
	        return utilisateur;
		}
		
		public Utilisateur findByEmail(String user) {
			Utilisateur utilisateur=null;
			try {
			String sql = "select * from Utilisateurs where email = :email";
			MapSqlParameterSource source = new MapSqlParameterSource();
			source.addValue("email",user);
			
			utilisateur = namedParameterJdbcTemplate.queryForObject(sql, source,
					new UtilisateurRowMapper());
			}
			catch(Exception e) {
				System.out.println("user null");
				return null;
			}
	        return utilisateur;
		}
	 

		
}
