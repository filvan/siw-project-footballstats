package it.uniroma3.siw.footballstats.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.footballstats.model.User;
import it.uniroma3.siw.footballstats.repository.UserRepository;

@Service
public class UserService {

	@Autowired private UserRepository userRepository;
	
	public User findById(Long id) {
		return this.userRepository.findById(id).get();
	}

	@Transactional
	public void update(User user) {
		this.userRepository.save(user);
	}
}
