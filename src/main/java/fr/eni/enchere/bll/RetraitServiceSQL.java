package fr.eni.enchere.bll;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.dal.RetraitRepository;

@Service
public class RetraitServiceSQL implements RetraitService{

	RetraitRepository retraitRepository;

	
	public RetraitServiceSQL(RetraitRepository retraitRepository) {
		super();
		this.retraitRepository = retraitRepository;
	}

	@Override
	public void ajouterRetrait(Retrait retrait) {
		retraitRepository.ajouterRetrait(retrait);
		
	}

	@Override
	public void supprimerRetrait(Retrait retrait) {
		retraitRepository.supprimerRetrait(retrait);
		
	}

	@Override
	public void updateRetrait(Retrait retrait) {
		retraitRepository.updateRetrait(retrait);
		
	}

	@Override
	public Retrait getRetraitById(long idRetrait) {
		
		return retraitRepository.getRetraitById(idRetrait);
	}

	@Override
	public List<Retrait> findAll() {
		
		return retraitRepository.findAll();
	}
}
