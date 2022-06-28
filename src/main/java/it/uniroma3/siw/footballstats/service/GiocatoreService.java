package it.uniroma3.siw.footballstats.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.footballstats.model.Giocatore;
import it.uniroma3.siw.footballstats.repository.GiocatoreRepository;

@Service
public class GiocatoreService {

	@Autowired private GiocatoreRepository giocatoreRepository;

	@Transactional
	public void save(Giocatore giocatore) {
		this.giocatoreRepository.save(giocatore);
	}
	
	@Transactional
	public void update(Giocatore giocatore) {
		this.giocatoreRepository.save(giocatore);
	}
	
	@Transactional
	public void update(Long id, String nome, String cognome, String dataNascita, String ruolo) {
		this.giocatoreRepository.updateNomeAndCognomeAndDataNascitaAndRuoloById(id, nome, cognome, dataNascita, ruolo);
	}
	
	@Transactional
	public void deleteById(Long id) {
		giocatoreRepository.deleteById(id);
	}

	@Transactional
	public void delete(Giocatore giocatore) {
		giocatoreRepository.delete(giocatore);
	}

	public List<Giocatore> findAll() {
		return (List<Giocatore>) giocatoreRepository.findAll();
	}

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

	public List<Giocatore> findAllBySquadraIdOrderByRuoloDesc(Long id) {
		return this.giocatoreRepository.findAllBySquadraIdOrderByRuoloDesc(id);
	}

	public List<Giocatore> findAllByOrderByPresenzeTotaliDesc() {
		return this.giocatoreRepository.findAllByOrderByPresenzeTotaliDesc();
	}

	public List<Giocatore> findAllByOrderByMinutiGiocatiTotaliDesc() {
		return this.giocatoreRepository.findAllByOrderByMinutiGiocatiTotaliDesc();
	}

	public List<Giocatore> findAllByOrderByGolSegnatiTotaliDesc() {
		return this.giocatoreRepository.findAllByOrderByGolSegnatiTotaliDesc();
	}

	public List<Giocatore> findAllByOrderByAssistTotaliDesc() {
		return this.giocatoreRepository.findAllByOrderByAssistTotaliDesc();
	}

	public List<Giocatore> findAllByOrderByCleanSheetTotaliDesc() {
		return this.giocatoreRepository.findAllByOrderByCleanSheetTotaliDesc();
	}

	public List<Giocatore> findAllByOrderByAmmonizioniTotaliDesc() {
		return this.giocatoreRepository.findAllByOrderByAmmonizioniTotaliDesc();
	}

	public List<Giocatore> findAllByOrderByEspulsioniTotaliDesc() {
		return this.giocatoreRepository.findAllByOrderByEspulsioniTotaliDesc();
	}

	public List<Giocatore> findAllByOrderBySquadraNomeAscRuoloDesc() {
		return this.giocatoreRepository.findAllByOrderBySquadraNomeAscRuoloDesc();
	}

	public List<Giocatore> findAllByOrderByNumeroPreferenzeDesc() {
		return this.giocatoreRepository.findAllByOrderByNumeroPreferenzeDesc();
	}

}
