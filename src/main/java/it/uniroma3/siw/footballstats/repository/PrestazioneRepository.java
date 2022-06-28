package it.uniroma3.siw.footballstats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.footballstats.model.Giocatore;
import it.uniroma3.siw.footballstats.model.Prestazione;
import it.uniroma3.siw.footballstats.model.Squadra;

public interface PrestazioneRepository  extends CrudRepository<Prestazione, Long> {

	public boolean existsByGiocatoreAndDataAndSquadraAvversaria (Giocatore giocatore, String data, Squadra squadraAvversaria);

	public List<Prestazione> findAllByGiocatoreIdOrderByDataAsc(Long giocatoreId);
	
	@Modifying
	@Query("update Prestazione p set p.data = :data, p.squadraAvversaria = :squadraAvversaria, p.minutiGiocati = :minutiGiocati, p.golSegnati = :golSegnati, p.assist = :assist, p.portaInviolata = :portaInviolata, p.ammonizioni = :ammonizioni, p.espulsione = :espulsione where p.id = :id")
	public void updateDataAndSquadraAvversariaIdAndMinutiGiocatiAndGolSegnatiAndAssistAndPortaInviolataAndAmmonizioniAndEspulsioneById(
			@Param ("id") Long id, @Param ("data") String data, @Param ("squadraAvversaria") Squadra squadraAvversaria, @Param ("minutiGiocati") Integer minutiGiocati, @Param ("golSegnati") Integer golSegnati, @Param ("assist") Integer assist,
			@Param ("portaInviolata") Integer portaInviolata, @Param ("ammonizioni") Integer ammonizioni, @Param ("espulsione") Integer espulsione);
}
