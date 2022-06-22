package it.uniroma3.siw.footballstats.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.footballstats.model.Squadra;

public interface SquadraRepository extends CrudRepository<Squadra, Long> {

	public boolean existsByNomeAndCitta(String nome, String citta);

	public List<Squadra> findAllByOrderByNomeAsc();

}
