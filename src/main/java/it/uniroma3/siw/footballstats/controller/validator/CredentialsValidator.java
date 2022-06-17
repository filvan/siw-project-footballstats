package it.uniroma3.siw.footballstats.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.footballstats.model.Credentials;
import it.uniroma3.siw.footballstats.model.User;
import it.uniroma3.siw.footballstats.service.CredentialsService;

@Component
public class CredentialsValidator implements Validator {
    
	@Autowired CredentialsService credentialsService;
	
	@Override
	public void validate(Object o, Errors errors) {
		Credentials credentials = (Credentials)o;
		if (this.credentialsService.alreadyExists(credentials))
			errors.rejectValue("username", "duplicato");
	}
	
	@Override
    public boolean supports(Class<?> clazz) {
        return Credentials.class.equals(clazz);
    }
}
