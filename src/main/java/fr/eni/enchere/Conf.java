package fr.eni.enchere;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Conf {

	/**
	 * Définition de l'encodeur de mot de passe
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * connection à H2 avec execution du script d'initialisation de la BDD
	 */
//	@Bean
//	public DataSource dataSource() {
//	    return new EmbeddedDatabaseBuilder()
//	      .setType(EmbeddedDatabaseType.H2)
//	      .addScript("classpath:schema.sql").build();
//	}
//	


}
