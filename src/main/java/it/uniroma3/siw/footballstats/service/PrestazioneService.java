package it.uniroma3.siw.footballstats.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.footballstats.model.Prestazione;
import it.uniroma3.siw.footballstats.repository.PrestazioneRepository;

@Service
public class PrestazioneService {

	@Autowired PrestazioneRepository prestazioneRepository;

	@Transactional
	public void save(Prestazione prestazione) {
		this.prestazioneRepository.save(prestazione);
	}
	
	@Transactional
	public void update(Long id, Integer minutiGiocati, Integer golSegnati, Integer assist, Integer portaInviolata,
			Integer ammonizioni, Integer espulsione) {
		this.prestazioneRepository.updateDataAndSquadraAvversariaIdAndMinutiGiocatiAndGolSegnatiAndAssistAndPortaInviolataAndAmmonizioniAndEspulsioneById(minutiGiocati, golSegnati, assist, portaInviolata, ammonizioni, espulsione, id);
	}
	
	@Transactional
	public void delete(Prestazione prestazione) {
		this.prestazioneRepository.delete(prestazione);
	}

	public List<Prestazione> findAll() {
		return (List<Prestazione>) prestazioneRepository.findAll();
	}

	public Prestazione findById(Long id) {
		Optional<Prestazione> optional = prestazioneRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	public boolean alreadyExists(Prestazione prestazione) {
		return this.prestazioneRepository.existsByGiocatoreAndDataAndSquadraAvversaria(prestazione.getGiocatore(), prestazione.getData(), prestazione.getSquadraAvversaria());
	}

	public List<Prestazione> findAllByGiocatoreOrderByDataAsc(Long giocatoreId) {
		return this.prestazioneRepository.findAllByGiocatoreIdOrderByDataAsc(giocatoreId);
	}
}
