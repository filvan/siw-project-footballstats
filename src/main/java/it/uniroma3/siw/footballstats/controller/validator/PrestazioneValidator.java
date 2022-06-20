package it.uniroma3.siw.footballstats.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.footballstats.model.Prestazione;
import it.uniroma3.siw.footballstats.service.PrestazioneService;

@Component
public class PrestazioneValidator implements Validator {
	
	@Autowired
	private PrestazioneService prestazioneService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Prestazione.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Prestazione prestazione = (Prestazione)target;

		if(this.prestazioneService.alreadyExists(prestazione))
			errors.reject("prestazione.duplicato");
	}
}
