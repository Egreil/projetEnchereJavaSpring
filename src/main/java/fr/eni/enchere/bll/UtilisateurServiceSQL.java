package fr.eni.enchere.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ArticleVenduRepository;
import fr.eni.enchere.dal.UtilisateurRepository;


@Service
public class UtilisateurServiceSQL implements UtilisateurService{
	
	UtilisateurRepository utilisateurRepository;
	ArticleVenduRepository articleVenduRepository;
	@Autowired
	PasswordEncoder encodeur;
	

	public UtilisateurServiceSQL(UtilisateurRepository utilisateurRepository,
			ArticleVenduRepository articleVenduRepository) {
		this.utilisateurRepository = utilisateurRepository;
		this.articleVenduRepository = articleVenduRepository;
		
	}
/*
 * Création d'un utilisateur après vérification que le pseudo 
 * ou l'email renseigné ne soient pas déjà utilisés.
 */
	@Transactional
	@Override
	public void ajouterUtilisateur(Utilisateur utilisateur) {
		System.out.println("ici");
		if(utilisateurRepository.findByPseudo(utilisateur.getPseudo())==null && utilisateurRepository.findByEmail(utilisateur.getEmail())==null) {
			System.out.println( utilisateurRepository.findByPseudo(utilisateur.getPseudo()));
		utilisateur.setMotDePasse(encodeur.encode(utilisateur.getMotDePasse()));
		utilisateurRepository.ajouterUtilisateur(utilisateur);
		}
//		else {
//			throw new 
//		}
	}

	@Override
	public void supprimerUtilisateur(Utilisateur utilisateur) {
		utilisateurRepository.supprimerUtilisateur(utilisateur);
		
	}

	@Override
	public Utilisateur getUtilisateurByID(long id) {
		return utilisateurRepository.getUtilisateurByID(id);
		
	}

	@Override
	public List<Utilisateur> findAll() {
		
		return utilisateurRepository.findAll();
	}

	@Override
	public void update(Utilisateur utilisateur) {
		utilisateurRepository.update(utilisateur);
		
	}
	
	@Override
	public List<ArticleVendu> getArticlesByUser(Utilisateur utilisateur) {
		articleVenduRepository.findAll().forEach(a-> System.out.println(a.getVendeur()));
		List<ArticleVendu> articles= articleVenduRepository.findAll().stream().filter(a->a.getVendeur()!=null && utilisateur.getNoUtilisateur()==a.getVendeur().getNoUtilisateur()).toList();
		return articles;
		
	}
	
	@Override
	public Utilisateur getUtilisateurByPseudo(String pseudo) {
		return utilisateurRepository.findByPseudo(pseudo);
	}
	@Override
	public Utilisateur getUtilisateurByEmail(String email) {
		return utilisateurRepository.findByEmail(email);
	}
	


}
