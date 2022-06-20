package it.uniroma3.siw.footballstats.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Giocatore {

	/* ******************* */
	/* VARIABILI D'ISTANZA */
	/* ******************* */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank private String nome;
	
	@NotBlank private String cognome;
	
	@NotBlank private String nazionalita;
	
	private String ruolo;
	
	@NotBlank private String dataNascita; //Formato: gg-mm-aaaa
	
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
	
	@OneToMany(mappedBy = "giocatore", cascade = {CascadeType.REMOVE})
	private List<Prestazione> prestazioni;
	
	/* *********** */
	/* COSTRUTTORI */
	/* *********** */
	
	public Giocatore() {}
	
	public Giocatore(String nome, String cognome, String nazionalita, String ruolo, String dataNascita,
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

	public void setDataNascita(String dataNascita) {
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
	
	public void setPrestazioni(List<Prestazione> prestazioni) {
		this.prestazioni = prestazioni;
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
	
	public String getDataNascita() {
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

	public List<Prestazione> getPrestazioni() {
		return prestazioni;
	}

	public void aggiornaGiocatoreIncrementi(Prestazione prestazione) {
		this.setPresenzeTotali(presenzeTotali + 1);
		this.setMinutiGiocatiTotali(this.getMinutiGiocatiTotali() + prestazione.getMinutiGiocati());
		this.setGolSegnatiTotali(this.getGolSegnatiTotali() + prestazione.getGolSegnati());
		this.setCleanSheetTotali(this.getCleanSheetTotali() + prestazione.getPortaInviolata());
		this.setAssistTotali(this.getAssistTotali() + prestazione.getAssist());
		this.setAmmonizioniTotali(this.getAmmonizioniTotali() + prestazione.getAmmonizioni());
		this.setEspulsioniTotali(this.getEspulsioniTotali() + prestazione.getEspulsione());
	}
	
	public void aggiornaGiocatoreDecrementi(Prestazione prestazione) {
		this.setPresenzeTotali(presenzeTotali - 1);
		this.setMinutiGiocatiTotali(this.getMinutiGiocatiTotali() - prestazione.getMinutiGiocati());
		this.setGolSegnatiTotali(this.getGolSegnatiTotali() - prestazione.getGolSegnati());
		this.setCleanSheetTotali(this.getCleanSheetTotali() - prestazione.getPortaInviolata());
		this.setAssistTotali(this.getAssistTotali() - prestazione.getAssist());
		this.setAmmonizioniTotali(this.getAmmonizioniTotali() - prestazione.getAmmonizioni());
		this.setEspulsioniTotali(this.getEspulsioniTotali() - prestazione.getEspulsione());
	}
}
