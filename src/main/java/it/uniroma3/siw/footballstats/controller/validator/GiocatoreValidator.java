package it.uniroma3.siw.footballstats.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.footballstats.model.Giocatore;
import it.uniroma3.siw.footballstats.service.GiocatoreService;

@Component
public class GiocatoreValidator implements Validator {

	@Autowired private GiocatoreService giocatoreService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Giocatore.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Giocatore giocatore = (Giocatore)target;
		
		if(this.giocatoreService.alreadyExists(giocatore))
			errors.reject("giocatore.duplicato");
	}
}
