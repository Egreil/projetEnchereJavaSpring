package fr.eni.enchere.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.UtilisateurRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Override
	public UserDetails loadUserByUsername(String utilisateur ) {

		Utilisateur user;
		try {
			   if (utilisateur.contains("@")) {
			        user = utilisateurRepository.findByEmail(utilisateur);
			    } else {
			        
			        user = utilisateurRepository.findByPseudo(utilisateur);
			    }

			if (user == null) {
				throw new UsernameNotFoundException(utilisateur);
			}
		} catch (Exception e) {
			throw new UsernameNotFoundException(utilisateur);
		}
		return new MyUserDetail(user);
	}
}




