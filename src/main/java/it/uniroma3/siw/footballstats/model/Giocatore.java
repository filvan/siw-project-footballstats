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
	
	@OneToMany(mappedBy = "giocatore", cascade = {CascadeType.REMOVE})
	private List<Prestazione> prestazioni;
	
	/* I seguenti attributi sono derivati e gestiti in modo materializzato */
	private Integer presenzeTotali;
	
	private Integer minutiGiocatiTotali;
	
	private Integer golSegnatiTotali;
	
	private Integer cleanSheetTotali;
	
	private Integer assistTotali;
	
	private Integer ammonizioniTotali;
	
	private Integer espulsioniTotali;
	
	/* *********** */
	/* COSTRUTTORI */
	/* *********** */
	
	public Giocatore() {
		this.presenzeTotali = 0;
		this.minutiGiocatiTotali = 0;
		this.golSegnatiTotali = 0;
		this.cleanSheetTotali = 0;
		this.assistTotali = 0;
		this.ammonizioniTotali = 0;
		this.espulsioniTotali = 0;
	}
	
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

	public void setPresenzeTotali(Integer presenzeTotali) {
		this.presenzeTotali = presenzeTotali;
	}

	public void setMinutiGiocatiTotali(Integer minutiGiocatiTotali) {
		this.minutiGiocatiTotali = minutiGiocatiTotali;
	}

	public void setGolSegnatiTotali(Integer golSegnatiTotali) {
		this.golSegnatiTotali = golSegnatiTotali;
	}

	public void setCleanSheetTotali(Integer cleanSheetTotali) {
		this.cleanSheetTotali = cleanSheetTotali;
	}

	public void setAssistTotali(Integer assistTotali) {
		this.assistTotali = assistTotali;
	}

	public void setAmmonizioniTotali(Integer ammonizioniTotali) {
		this.ammonizioniTotali = ammonizioniTotali;
	}

	public void setEspulsioniTotali(Integer espulsioniTotali) {
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
	
	public Integer getPresenzeTotali() {
		return presenzeTotali;
	}
	
	public Integer getMinutiGiocatiTotali() {
		return minutiGiocatiTotali;
	}
	
	public Integer getGolSegnatiTotali() {
		return golSegnatiTotali;
	}
	
	public Integer getCleanSheetTotali() {
		return cleanSheetTotali;
	}
	
	public Integer getAssistTotali() {
		return assistTotali;
	}
	
	public Integer getAmmonizioniTotali() {
		return ammonizioniTotali;
	}
	
	public Integer getEspulsioniTotali() {
		return espulsioniTotali;
	}

	public List<Prestazione> getPrestazioni() {
		return prestazioni;
	}

	/* ****************** */
	/* METODI DI SUPPORTO */
	/* ****************** */
	
	public void aggiornaGiocatoreIncrementi(Prestazione prestazione) {
		this.setPresenzeTotali(this.getPresenzeTotali() + 1);
		this.setMinutiGiocatiTotali(this.getMinutiGiocatiTotali() + prestazione.getMinutiGiocati());
		this.setGolSegnatiTotali(this.getGolSegnatiTotali() + prestazione.getGolSegnati());
		this.setCleanSheetTotali(this.getCleanSheetTotali() + prestazione.getPortaInviolata());
		this.setAssistTotali(this.getAssistTotali() + prestazione.getAssist());
		this.setAmmonizioniTotali(this.getAmmonizioniTotali() + prestazione.getAmmonizioni());
		this.setEspulsioniTotali(this.getEspulsioniTotali() + prestazione.getEspulsione());
	}
	
	public void aggiornaGiocatoreDecrementi(Prestazione prestazione) {
		this.setPresenzeTotali(this.getPresenzeTotali() - 1);
		this.setMinutiGiocatiTotali(this.getMinutiGiocatiTotali() - prestazione.getMinutiGiocati());
		this.setGolSegnatiTotali(this.getGolSegnatiTotali() - prestazione.getGolSegnati());
		this.setCleanSheetTotali(this.getCleanSheetTotali() - prestazione.getPortaInviolata());
		this.setAssistTotali(this.getAssistTotali() - prestazione.getAssist());
		this.setAmmonizioniTotali(this.getAmmonizioniTotali() - prestazione.getAmmonizioni());
		this.setEspulsioniTotali(this.getEspulsioniTotali() - prestazione.getEspulsione());
	}
}
