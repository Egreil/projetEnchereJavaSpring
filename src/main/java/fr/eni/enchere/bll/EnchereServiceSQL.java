package fr.eni.enchere.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.dal.EnchereRepository;


@Service
public class EnchereServiceSQL implements EnchereService {

	EnchereRepository enchereRepository;
	ArticleVenduService articleVenduService;
	


	public EnchereServiceSQL(EnchereRepository enchereRepository, ArticleVenduService articleVenduService) {
		super();
		this.enchereRepository = enchereRepository;
		this.articleVenduService = articleVenduService;
	}
	@Transactional
	@Override
	public void ajouterEnchere(Enchere enchere) {	
		if(enchere.getMontantEnchere()>enchere.getArticleVendu().getPrixVente() && 
				enchere.getArticleVendu().getEtatVente().equals("En cours")){
		enchereRepository.ajouterEnchere(enchere);
		enchere.getArticleVendu().setPrixVente(enchere.getMontantEnchere());
		articleVenduService.update(enchere.getArticleVendu());
		}
		else {
			System.out.println("Enchere trop faible ou indisponible");
		}
		
	}

	@Override
	public void supprimerEnchere(Enchere enchere) {
		enchereRepository.supprimerEnchere(enchere);

	}

	@Override
	public Enchere getEnchereByID(long id) {
		return enchereRepository.getEnchereByID(id);
	}

	@Override
	public List<Enchere> findAll() {
		return enchereRepository.findAll();
	}

	@Override
	public void update(Enchere enchere) {
		enchereRepository.update(enchere);

	}
	@Override
	public List<Enchere> rechercheEncheres(List<ArticleVendu> articlesRecherches) {
		List<Enchere> encheres=enchereRepository.findAll();
		List<Enchere> enchereResult= new ArrayList<Enchere>();
		if(articlesRecherches.size()>0) {
		System.out.println(articlesRecherches.size());
//		encheres=encheres.stream().filter(e-> articleVendus.contains(e.getArticleVendu())).toList();
//		}
		articlesRecherches.forEach(a->{
			encheres.forEach(e->{
				//System.out.println("n°article enchere : "+e.getArticleVendu().getNoArticle()+"     n°Article Article° : "+a.getNoArticle());
				if(e.getArticleVendu().getNoArticle()==a.getNoArticle()) {
					
					enchereResult.add(e);
				}
			});
		});
		}
		return enchereResult;
	}
	
	public List<Enchere> encheresSurArticle(ArticleVendu articleVendu) {
		List<Enchere> encheresSurArticleEncheres=enchereRepository.findAll().stream().filter(a->a.getArticleVendu().getNoArticle()==(articleVendu.getNoArticle())).toList();
		encheresSurArticleEncheres=encheresSurArticleEncheres.stream().sorted((e1,e2)->Enchere.CompareTo(e1,e2)).toList();
		return encheresSurArticleEncheres;
	}
	
	public Enchere enchereMaxParArticle(List<Enchere> encheresSurArticle) {
		Enchere enchere=encheresSurArticle.get(0);
		return enchere;
	}
	
}
