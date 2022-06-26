package it.uniroma3.siw.footballstats.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.footballstats.model.User;
import it.uniroma3.siw.footballstats.repository.UserRepository;

@Service
public class UserService {

	@Autowired private UserRepository userRepository;
	
	@Transactional
	public void update(User user) {
		this.userRepository.save(user);
	}
	
	public User findById(Long id) {
		return this.userRepository.findById(id).get();
	}

	public List<User> findAll() {
		return (List<User>) this.userRepository.findAll();
	}
}
