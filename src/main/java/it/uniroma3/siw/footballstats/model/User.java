package it.uniroma3.siw.footballstats.model;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users") // Perchè "User" in Postgres è una parola riservata
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String cognome;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Giocatore> giocatoriPreferiti;
	
	public User() {
		this.giocatoriPreferiti = new ArrayList<>();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public List<Giocatore> getGiocatoriPreferiti() {
		return giocatoriPreferiti;
	}

	public void setGiocatoriPreferiti(List<Giocatore> giocatoriPreferiti) {
		this.giocatoriPreferiti = giocatoriPreferiti;
	}

}
