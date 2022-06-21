package it.uniroma3.siw.footballstats.model;

import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Squadra {

	/* ******************* */
	/* VARIABILI D'ISTANZA */
	/* ******************* */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	private String citta;
	
	@OneToMany(mappedBy = "squadra")
	private List<Giocatore> giocatori;
	
	/* *********** */
	/* COSTRUTTORI */
	/* *********** */
	
	public Squadra() {
		super();
	}
	
	public Squadra(String nome, String citta) {
		super();
		this.nome = nome;
		this.citta = citta;
		this.giocatori = new ArrayList<>();
	}


	/* ************* */
	/* METODI SETTER */
	/* ************* */
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCitta(String citta) {
		this.citta = citta;
	}

	public void setGiocatori(List<Giocatore> giocatori) {
		this.giocatori = giocatori;
	}
	
	/* ************* */
	/* METODI GETTER */
	/* ************* */
	
	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCitta() {
		return citta;
	}

	public List<Giocatore> getGiocatori() {
		return giocatori;
	}	
}
