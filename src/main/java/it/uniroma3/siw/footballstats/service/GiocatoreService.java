package it.uniroma3.siw.footballstats.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.footballstats.model.Giocatore;
import it.uniroma3.siw.footballstats.repository.GiocatoreRepository;

@Service
public class GiocatoreService {

	@Autowired GiocatoreRepository giocatoreRepository;

	@Transactional
	public void save(@Valid Giocatore giocatore) {
		this.giocatoreRepository.save(giocatore);
	}

	@Transactional
	public List<Giocatore> findAll() {
		return (List<Giocatore>) giocatoreRepository.findAll();
	}

	@Transactional
	public Giocatore findById(Long id) {
		Optional<Giocatore> optional = giocatoreRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	public boolean alreadyExists(Giocatore giocatore) {
		return this.giocatoreRepository.existsByNomeAndCognomeAndNazionalitaAndDataNascita(giocatore.getNome(), giocatore.getCognome(), giocatore.getNazionalita(), giocatore.getDataNascita());
	}

	@Transactional
	public void deleteById(Long id) {
		giocatoreRepository.deleteById(id);
	}

	@Transactional
	public void delete(Giocatore giocatore) {
		giocatoreRepository.delete(giocatore);
	}

	public List<Giocatore> findAllBySquadraIdOrderByRuoloDesc(Long id) {
		return this.giocatoreRepository.findAllBySquadraIdOrderByRuoloDesc(id);
	}

}
