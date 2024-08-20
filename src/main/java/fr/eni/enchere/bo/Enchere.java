package fr.eni.enchere.bo;

import java.time.LocalDate;

public class Enchere {
	private long noEnchere;
	private Utilisateur utilisateur;
	private ArticleVendu articleVendu;
	private LocalDate dateEnchere;
	private double montantEnchere;
	
	
	



	public Enchere() {
		super();
	}


	public Enchere(Utilisateur utilisateur, ArticleVendu articleVendu, LocalDate dateEnchere, double montantEnchere) {
		super();
		this.utilisateur = utilisateur;
		this.articleVendu = articleVendu;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}


	public Enchere(long noEnchere, Utilisateur utilisateur, ArticleVendu articleVendu, LocalDate dateEnchere,
			double montantEnchere) {
		super();
		this.noEnchere = noEnchere;
		this.utilisateur = utilisateur;
		this.articleVendu = articleVendu;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}


	public long getNoEnchere() {
		return noEnchere;
	}


	public void setNoEnchere(long noEnchere) {
		this.noEnchere = noEnchere;
	}


	public LocalDate getDateEnchere() {
		return dateEnchere;
	}


	public void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}


	public double getMontantEnchere() {
		return montantEnchere;
	}


	public void setMontantEnchere(double montantEnchere) {
		this.montantEnchere = montantEnchere;
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}


	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	public ArticleVendu getArticleVendu() {
		return articleVendu;
	}


	public void setArticleVendu(ArticleVendu articleVendu) {
		this.articleVendu = articleVendu;
	}


	@Override
	public String toString() {
		return "Enchere [noEnchere=" + noEnchere + ", dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere
				+ ", utilisateur=" + utilisateur + ", articleVendu=" + articleVendu + "]";
	}

	//Le max en premiere position
	public static int CompareTo(Enchere e1, Enchere e2) {
		if(e1.getMontantEnchere()<=e2.getMontantEnchere()) {
			return 1;
		}
		else return -1;
		

	}
}
