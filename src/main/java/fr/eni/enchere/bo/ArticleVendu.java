package fr.eni.enchere.bo;

import java.sql.Date;
import java.time.LocalDate;

public class ArticleVendu {
	private long noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private double prixInitial;
	private double prixVente;
	private String etatVente;
	private Utilisateur vendeur;
	private Categorie categorie;
	
	
	
	
	
	public ArticleVendu(long noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, double prixInitial, String etatVente, Utilisateur vendeur,
			Categorie categorie) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = prixInitial;
		this.prixVente = prixInitial;
		this.etatVente = etatVente;
		this.vendeur = vendeur;
		this.categorie = categorie;
	}



	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, double miseAPrix, String etatVente, Utilisateur vendeur,Categorie categorie) {
		
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.prixInitial = miseAPrix;
		this.prixVente = miseAPrix;
		this.etatVente = etatVente;
		this.vendeur = vendeur;
		this.categorie=categorie;
	}



	public ArticleVendu() {
		// TODO Auto-generated constructor stub
	}



	public long getNoArticle() {
		return noArticle;
	}



	public void setNoArticle(long noArticle) {
		this.noArticle = noArticle;
	}



	public String getNomArticle() {
		return nomArticle;
	}



	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}



	public void setDateDebutEncheres(LocalDate localDate) {
		this.dateDebutEncheres = localDate;
	}



	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}



	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}



	public double getPrixInitial() {
		return prixInitial;
	}



	public void setPrixInitial(double miseAPrix) {
		this.prixInitial = miseAPrix;
	}



	public double getPrixVente() {
		return prixVente;
	}



	public void setPrixVente(double prixVente) {
		this.prixVente = prixVente;
	}



	public String getEtatVente() {
		return etatVente;
	}



	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}



	public Utilisateur getVendeur() {
		return vendeur;
	}



	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}



	public Categorie getCategorie() {
		return categorie;
	}



	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}



	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", prixInitial="
				+ prixInitial + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", vendeur=" + vendeur.toString()
				+ ", categorie=" + categorie.toString() + "]";
	}




	
	
	
}
