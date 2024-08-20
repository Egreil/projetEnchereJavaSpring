package fr.eni.enchere.ihm.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.enchere.bll.CategorieService;
import fr.eni.enchere.bo.Categorie;

@Component
public class StringToCategorieConverter implements Converter<String, Categorie>{

	@Autowired
	CategorieService categorieService;
	
	@Override
	public Categorie convert(String source) {
		Categorie categorie= categorieService.getCategorieByID(Long.parseLong(source));
		return categorie;
	}

}
