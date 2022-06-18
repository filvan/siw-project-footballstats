package it.uniroma3.siw.footballstats.model;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class Giocatore {

	/* ******************* */
	/* VARIABILI D'ISTANZA */
	/* ******************* */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String cognome;
	
	private String nazionalita;
	
	private String ruolo;
	
	private LocalDate dataNascita; //Formato: aaaa-mm-gg
	
	@ManyToOne
	private Squadra squadra;
	
	/* I seguenti attributi sono derivati e gestiti in modo materializzato */
	private int presenzeTotali;
	
	private int minutiGiocatiTotali;
	
	private int golSegnatiTotali;
	
	private int cleanSheetTotali;
	
	private int assistTotali;
	
	private int ammonizioniTotali;
	
	private int espulsioniTotali;
	
	
	/* *********** */
	/* COSTRUTTORI */
	/* *********** */
	
	public Giocatore() {}
	
	public Giocatore(String nome, String cognome, String nazionalita, String ruolo, LocalDate dataNascita,
			Squadra squadra) {
		this.nome = nome;
		this.cognome = cognome;
		this.nazionalita = nazionalita;
		this.ruolo = ruolo;
		this.dataNascita = dataNascita;
		this.squadra = squadra;
		
		this.presenzeTotali = 0;
		this.minutiGiocatiTotali = 0;
		this.golSegnatiTotali = 0;
		this.cleanSheetTotali = 0;
		this.assistTotali = 0;
		this.ammonizioniTotali = 0;
		this.espulsioniTotali = 0;
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

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public void setSquadra(Squadra squadra) {
		this.squadra = squadra;
	}

	public void setPresenzeTotali(int presenzeTotali) {
		this.presenzeTotali = presenzeTotali;
	}

	public void setMinutiGiocatiTotali(int minutiGiocatiTotali) {
		this.minutiGiocatiTotali = minutiGiocatiTotali;
	}

	public void setGolSegnatiTotali(int golSegnatiTotali) {
		this.golSegnatiTotali = golSegnatiTotali;
	}

	public void setCleanSheetTotali(int cleanSheetTotali) {
		this.cleanSheetTotali = cleanSheetTotali;
	}

	public void setAssistTotali(int assistTotali) {
		this.assistTotali = assistTotali;
	}

	public void setAmmonizioniTotali(int ammonizioniTotali) {
		this.ammonizioniTotali = ammonizioniTotali;
	}

	public void setEspulsioniTotali(int espulsioniTotali) {
		this.espulsioniTotali = espulsioniTotali;
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
	
	public String getCognome() {
		return cognome;
	}
	
	public String getNazionalita() {
		return nazionalita;
	}
	
	public String getRuolo() {
		return ruolo;
	}
	
	public LocalDate getDataNascita() {
		return dataNascita;
	}
	
	public Squadra getSquadra() {
		return squadra;
	}
	
	public int getPresenzeTotali() {
		return presenzeTotali;
	}
	
	public int getMinutiGiocatiTotali() {
		return minutiGiocatiTotali;
	}
	
	public int getGolSegnatiTotali() {
		return golSegnatiTotali;
	}
	
	public int getCleanSheetTotali() {
		return cleanSheetTotali;
	}
	
	public int getAssistTotali() {
		return assistTotali;
	}
	
	public int getAmmonizioniTotali() {
		return ammonizioniTotali;
	}
	
	public int getEspulsioniTotali() {
		return espulsioniTotali;
	}
}
