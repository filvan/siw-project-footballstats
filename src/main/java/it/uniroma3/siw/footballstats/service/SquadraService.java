package it.uniroma3.siw.footballstats.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.footballstats.model.Squadra;
import it.uniroma3.siw.footballstats.repository.SquadraRepository;

@Service
public class SquadraService {

	@Autowired private SquadraRepository squadraRepository;

	@Transactional
	public void save(Squadra squadra) {
		this.squadraRepository.save(squadra);
	}
	
	@Transactional
	public void update(Long id, String nome, String citta) {
		this.squadraRepository.updateNomeAndCittaById(id, nome, citta);
	}
	
	@Transactional
	public void deleteById(Long id) {
		squadraRepository.deleteById(id);
	}

	@Transactional
	public void delete(Squadra squadra) {
		squadraRepository.delete(squadra);
	}

	public List<Squadra> findAll() {
		return (List<Squadra>) squadraRepository.findAll();
	}

	public Squadra findById(Long id) {
		Optional<Squadra> optional = squadraRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	public boolean alreadyExists(Squadra squadra) {
		return this.squadraRepository.existsByNomeAndCitta(squadra.getNome(), squadra.getCitta());
	}

	public List<Squadra> findAllByOrderByNomeAsc() {
		return this.squadraRepository.findAllByOrderByNomeAsc();
	}
}
