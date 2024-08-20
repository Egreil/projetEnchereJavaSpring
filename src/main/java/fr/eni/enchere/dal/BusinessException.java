package fr.eni.enchere.dal;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> errors = new ArrayList<>();

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public void add(String error) {
		errors.add(error);
	}
	

}
