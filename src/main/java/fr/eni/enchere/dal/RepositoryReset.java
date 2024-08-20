package fr.eni.enchere.dal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryReset {
	
	private JdbcTemplate jdbcTemplate;

	public RepositoryReset(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void resetBDD() {
		String sqlString="-- Script de création de la base de données ENCHERES\r\n"
				+ "--   type :      SQL Server 2012\r\n"
				+ "--\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "drop table encheres;\r\n"
				+ "drop table retraits;\r\n"
				+ "drop table articles_vendus;\r\n"
				+ "drop table Utilisateurs;\r\n"
				+ "drop table categories;\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "CREATE TABLE CATEGORIES (\r\n"
				+ "    noCategorie   INTEGER IDENTITY(1,1) NOT NULL,\r\n"
				+ "    libelle        VARCHAR(30) NOT NULL\r\n"
				+ ")\r\n"
				+ "\r\n"
				+ "ALTER TABLE CATEGORIES ADD constraint categorie_pk PRIMARY KEY (noCategorie)\r\n"
				+ "\r\n"
				+ "-----------------------------------------------------------------------------------------------\r\n"
				+ "\r\n"
				+ "CREATE TABLE RETRAITS (\r\n"
				+ "	noArticle         INTEGER NOT NULL,\r\n"
				+ "    rue              VARCHAR(30) NOT NULL,\r\n"
				+ "    codePostal      VARCHAR(15) NOT NULL,\r\n"
				+ "    ville            VARCHAR(30) NOT NULL\r\n"
				+ ")\r\n"
				+ "\r\n"
				+ "ALTER TABLE RETRAITS ADD constraint retrait_pk PRIMARY KEY  (noArticle)\r\n"
				+ "\r\n"
				+ "-----------------------------------------------------------------------------------------------\r\n"
				+ "CREATE TABLE UTILISATEURS (\r\n"
				+ "    noUtilisateur   INTEGER IDENTITY(1,1) NOT NULL,\r\n"
				+ "    pseudo           VARCHAR(30) NOT NULL,\r\n"
				+ "    nom              VARCHAR(30) NOT NULL,\r\n"
				+ "    prenom           VARCHAR(30) NOT NULL,\r\n"
				+ "    email            VARCHAR(255) NOT NULL,\r\n"
				+ "    telephone        VARCHAR(15),\r\n"
				+ "    rue              VARCHAR(30) NOT NULL,\r\n"
				+ "    codePostal       VARCHAR(10) NOT NULL,\r\n"
				+ "    ville            VARCHAR(30) NOT NULL,\r\n"
				+ "    motDePasse       VARCHAR(100) NOT NULL,\r\n"
				+ "    credit           FLOAT NOT NULL,\r\n"
				+ "    administrateur   bit NOT NULL\r\n"
				+ ")\r\n"
				+ "\r\n"
				+ "ALTER TABLE UTILISATEURS ADD constraint utilisateur_pk PRIMARY KEY (noUtilisateur)\r\n"
				+ "-----------------------------------------------------------------------------------------------\r\n"
				+ "\r\n"
				+ "CREATE TABLE ARTICLES_VENDUS (\r\n"
				+ "    noArticle                    INTEGER IDENTITY(1,1) NOT NULL,\r\n"
				+ "    nomArticle                   VARCHAR(30) NOT NULL,\r\n"
				+ "    [description]                VARCHAR(255) NOT NULL,\r\n"
				+ "	dateDebutEncheres            DATE NOT NULL,\r\n"
				+ "    dateFinEncheres              DATE NOT NULL,\r\n"
				+ "    prixInitial                  FLOAT,\r\n"
				+ "    prixVente                    FLOAT,\r\n"
				+ "    etatVente                   VARCHAR(50),\r\n"
				+ "    noUtilisateur                INTEGER NOT NULL,\r\n"
				+ "    noCategorie                  INTEGER NOT NULL\r\n"
				+ ")\r\n"
				+ "\r\n"
				+ "ALTER TABLE ARTICLES_VENDUS ADD constraint articles_vendus_pk PRIMARY KEY (noArticle)\r\n"
				+ "\r\n"
				+ "-----------------------------------------------------------------------------------------------\r\n"
				+ "\r\n"
				+ "CREATE TABLE ENCHERES (\r\n"
				+ "    noEnchere		INTEGER IDENTITY NOT NULL,\r\n"
				+ "    noUtilisateur   INTEGER NOT NULL,\r\n"
				+ "    noArticle       INTEGER NOT NULL,\r\n"
				+ "    dateEnchere     datetime NOT NULL,\r\n"
				+ "	montantEnchere  FLOAT NOT NULL\r\n"
				+ "\r\n"
				+ ")\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "ALTER TABLE ENCHERES ADD constraint enchere_pk PRIMARY KEY (noEnchere)\r\n"
				+ "-----------------------------------------------------------------------------------------------\r\n"
				+ "\r\n"
				+ "ALTER TABLE ENCHERES\r\n"
				+ "    ADD CONSTRAINT encheres_utilisateur_fk FOREIGN KEY ( noUtilisateur ) REFERENCES UTILISATEURS ( noUtilisateur )\r\n"
				+ "ON DELETE NO ACTION \r\n"
				+ "    ON UPDATE no action \r\n"
				+ "\r\n"
				+ "ALTER TABLE ENCHERES\r\n"
				+ "    ADD CONSTRAINT encheres_articles_vendus_fk FOREIGN KEY ( noArticle )\r\n"
				+ "        REFERENCES ARTICLES_VENDUS ( noArticle )\r\n"
				+ "ON DELETE NO ACTION \r\n"
				+ "    ON UPDATE no action \r\n"
				+ "\r\n"
				+ "ALTER TABLE RETRAITS\r\n"
				+ "    ADD CONSTRAINT retraits_articles_vendus_fk FOREIGN KEY ( noArticle )\r\n"
				+ "        REFERENCES ARTICLES_VENDUS ( noArticle )\r\n"
				+ "ON DELETE NO ACTION \r\n"
				+ "    ON UPDATE no action \r\n"
				+ "\r\n"
				+ "ALTER TABLE ARTICLES_VENDUS\r\n"
				+ "    ADD CONSTRAINT articles_vendus_categories_fk FOREIGN KEY ( noCategorie )\r\n"
				+ "        REFERENCES categories ( noCategorie )\r\n"
				+ "ON DELETE NO ACTION \r\n"
				+ "    ON UPDATE no action \r\n"
				+ "\r\n"
				+ "ALTER TABLE ARTICLES_VENDUS\r\n"
				+ "    ADD CONSTRAINT ventes_utilisateur_fk FOREIGN KEY ( noUtilisateur )\r\n"
				+ "        REFERENCES utilisateurs ( noUtilisateur )\r\n"
				+ "ON DELETE NO ACTION \r\n"
				+ "    ON UPDATE no action \r\n";
		jdbcTemplate.execute(sqlString);
	}
	
}
