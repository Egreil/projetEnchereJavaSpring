package fr.eni.enchere.bo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Utilisateur {

	private long noUtilisateur;
	@Size(min=3, max=30)
	private String pseudo;
	@Size(min=3,max=30,message= "Le prenom doit comporter entre 3 et 30 caractères")
	private String nom;
	@Size(min=3,max=30, message= "Le Nom doit comporter entre 3 et 30 caractères")
	private String prenom;
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	private String email;
	@Size(min=4,max=15, message= "Le numéro de téléphone doit contenir entre 4 et 15 chiffre")
	private String telephone;
	@Size(min=3,max=255, message= "Votre adresse ne peut contenir moins de 3 caratères")
	private String rue;
	@Size(min=1,max=10, message= "Veuillez renseigner votre code postal s'il vous plait")
	private String codePostal;
	@Size(min=2,max=30, message= "Veuillez renseigner votre ville s'il vous plait")
	private String ville;
	@Size(min=8,max=255, message= "Le motDePasse doit comporter entre 8 et 255 caractères.")
	//(?=.*\\d) signifie n'importe quel chiffre base 10, 0 inclus
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",message = "Le motDePasse doit comporter un caractère minuscule minimum,"
			+ " une majuscule minimum "
			+ "et un chiffre entre 0 et 9 minimum.")
	private String motDePasse;
	private int credit;
	private  boolean administrateur;
	
	
	public Utilisateur() {
		super();
	}

	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, int credit, boolean administrateur) {
		super();
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
	}

	public Utilisateur(long noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, int credit, boolean administrateur) {
		super();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.motDePasse = motDePasse;
		this.credit = credit;
		this.administrateur = administrateur;
	}

	public long getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(long noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public boolean isAdministrateur() {
		return administrateur;
	}

	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

	@Override
	public String toString() {
		return "Utilisateur [noUtilisateur=" + noUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", telephone=" + telephone + ", rue=" + rue + ", codePostal="
				+ codePostal + ", ville=" + ville + ", motDePasse=" + motDePasse + ", credit=" + credit
				+ ", administrateur=" + administrateur + "]";
	}

    public String getAdministrateurToString() {
		String roles="";
		if(this.isAdministrateur()) {
			roles+="ADMIN,";
		}
		roles+="USER";
		return roles;
	}
	

	
	

	
}
