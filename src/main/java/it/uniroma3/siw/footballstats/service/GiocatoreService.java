package it.uniroma3.siw.footballstats.service;

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

	public boolean alreadyExists(Giocatore giocatore) {
		return this.giocatoreRepository.existsByNomeAndCognomeAndNazionalitaAndDataNascita(giocatore.getNome(), giocatore.getCognome(), giocatore.getNazionalita(), giocatore.getDataNascita());
	}

}
