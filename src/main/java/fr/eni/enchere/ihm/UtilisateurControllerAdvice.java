package fr.eni.enchere.ihm;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import fr.eni.enchere.bll.MonException;

@ControllerAdvice
public class UtilisateurControllerAdvice {
	 
   @ExceptionHandler(MonException.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        
        modelAndView.addObject("status", "1000");
        modelAndView.addObject("errorMessage", ex.getMessage());
        modelAndView.setViewName("error"); // Vue Thymeleaf pour afficher l'erreur
        return modelAndView;
    }
}