package it.uniroma3.siw.footballstats.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.footballstats.model.Giocatore;
import it.uniroma3.siw.footballstats.model.Prestazione;
import it.uniroma3.siw.footballstats.model.Squadra;

public interface PrestazioneRepository  extends CrudRepository<Prestazione, Long> {

	public boolean existsByGiocatoreAndDataAndSquadraAvversaria (Giocatore giocatore, String data, Squadra squadraAvversaria);
	

}
