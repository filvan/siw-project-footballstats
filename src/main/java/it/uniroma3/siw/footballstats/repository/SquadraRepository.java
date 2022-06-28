package it.uniroma3.siw.footballstats.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.footballstats.model.Squadra;

public interface SquadraRepository extends CrudRepository<Squadra, Long> {

	public boolean existsByNomeAndCitta(String nome, String citta);

	public List<Squadra> findAllByOrderByNomeAsc();

	@Modifying
	@Query("update Squadra s set s.nome = :nome, s.citta = :citta where s.id = :id")
	public void updateNomeAndCittaById(@Param ("id") Long id, @Param("nome") String nome, @Param("citta") String citta);
}
