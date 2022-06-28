package it.uniroma3.siw.footballstats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.footballstats.model.Giocatore;

public interface GiocatoreRepository extends CrudRepository<Giocatore, Long> {

	public boolean existsByNomeAndCognomeAndNazionalitaAndDataNascita(String nome, String cognome, String nazionalita, String dataNascita);

	public List<Giocatore> findAllBySquadraIdOrderByRuoloDesc(Long id);

	@Query("SELECT g FROM Giocatore g WHERE g.presenzeTotali > 0 ORDER BY g.presenzeTotali DESC")
	public List<Giocatore> findAllByOrderByPresenzeTotaliDesc();

	@Query("SELECT g FROM Giocatore g WHERE g.minutiGiocatiTotali > 0 ORDER BY g.minutiGiocatiTotali DESC")
	public List<Giocatore> findAllByOrderByMinutiGiocatiTotaliDesc();

	@Query("SELECT g FROM Giocatore g WHERE g.golSegnatiTotali > 0 ORDER BY g.golSegnatiTotali DESC")
	public List<Giocatore> findAllByOrderByGolSegnatiTotaliDesc();

	@Query("SELECT g FROM Giocatore g WHERE g.assistTotali > 0 ORDER BY g.assistTotali DESC")
	public List<Giocatore> findAllByOrderByAssistTotaliDesc();

	@Query("SELECT g FROM Giocatore g WHERE g.cleanSheetTotali > 0 AND g.ruolo = 'Portiere' ORDER BY g.cleanSheetTotali DESC")
	public List<Giocatore> findAllByOrderByCleanSheetTotaliDesc();

	@Query("SELECT g FROM Giocatore g WHERE g.ammonizioniTotali > 0 ORDER BY g.ammonizioniTotali DESC")
	public List<Giocatore> findAllByOrderByAmmonizioniTotaliDesc();

	@Query("SELECT g FROM Giocatore g WHERE g.espulsioniTotali > 0 ORDER BY g.espulsioniTotali DESC")
	public List<Giocatore> findAllByOrderByEspulsioniTotaliDesc();

	public List<Giocatore> findAllByOrderBySquadraNomeAscRuoloDesc();

	@Query("SELECT g FROM Giocatore g WHERE g.numeroPreferenze > 0 ORDER BY g.numeroPreferenze DESC")
	public List<Giocatore> findAllByOrderByNumeroPreferenzeDesc();

	@Modifying
	@Query("update Giocatore g set g.nome = :nome, g.cognome = :cognome, g.nazionalita = :nazionalita, g.dataNascita = :dataNascita, g.ruolo = :ruolo where g.id = :id")
	public void updateNomeAndCognomeAndDataNascitaAndRuoloById(@Param ("id") Long id, @Param("nome") String nome,
			@Param("cognome") String cognome, @Param("nazionalita") String nazionalita, @Param("dataNascita") String dataNascita, @Param("ruolo") String ruolo);
}
