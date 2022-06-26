package it.uniroma3.siw.footballstats.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.footballstats.model.Credentials;
import it.uniroma3.siw.footballstats.repository.CredentialsRepository;

@Service
public class CredentialsService {

	@Autowired protected CredentialsRepository credentialsRepository;
	@Autowired protected PasswordEncoder passwordEncoder;
	@Autowired private UserService userService;
	
	@Transactional
	public void save(Credentials credentials) {
		
		if (this.userService.findAll().size() == 0 && credentials.getUsername().equals("admin"))
			credentials.setRole(Credentials.ADMIN_ROLE);
		
		else
			credentials.setRole(Credentials.DEFAULT_ROLE);
		
		credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
		this.credentialsRepository.save(credentials);
	}
	
	public boolean alreadyExists(Credentials credentials) {
		return this.credentialsRepository.existsByUsername(credentials.getUsername());
	}
	
	public Credentials getCredentials(String username) {
		return this.credentialsRepository.findByUsername(username).get();
	}
	
}
