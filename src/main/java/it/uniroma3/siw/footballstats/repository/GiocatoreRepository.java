package it.uniroma3.siw.footballstats.repository;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.footballstats.model.Giocatore;

public interface GiocatoreRepository extends CrudRepository<Giocatore, Long> {

	public boolean existsByNomeAndCognomeAndNazionalitaAndDataNascita(String nome, String cognome, String nazionalita, String dataNascita);

}
