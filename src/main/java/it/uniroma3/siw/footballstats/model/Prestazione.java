package it.uniroma3.siw.footballstats.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Prestazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String data;
	
	private int minutiGiocati;
	
	private int golSegnati;
	
	private int assist;
	
	private int ammonizioni;
	
	private int espulsione;
	
	private int portaInviolata;
	
	@OneToOne
	private Squadra squadraAvversaria;
	
	@ManyToOne
	private Giocatore giocatore;
	
	public Prestazione() {
		
	}
	
	public Prestazione(String data, int minutiGiocati, int golSegnati, int assist, int ammonizioni,
			int espulsione, int portaInviolata) {
		this.data = data;
		this.minutiGiocati = minutiGiocati;
		this.golSegnati = golSegnati;
		this.assist = assist;
		this.ammonizioni = ammonizioni;
		this.espulsione = espulsione;
		this.portaInviolata = portaInviolata;
	}

	/* ************* */
	/* METODI GETTER */
	/* ************* */

	public Long getId() {
		return id;
	}

	public String getData() {
		return data;
	}

	public int getMinutiGiocati() {
		return minutiGiocati;
	}

	public int getGolSegnati() {
		return golSegnati;
	}

	public int getAssist() {
		return assist;
	}

	public int getAmmonizioni() {
		return ammonizioni;
	}

	public int getEspulsione() {
		return espulsione;
	}

	public int getPortaInviolata() {
		return portaInviolata;
	}
	
	public Squadra getSquadraAvversaria() {
		return squadraAvversaria;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	/* ************* */
	/* METODI SETTER */
	/* ************* */
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setMinutiGiocati(int minutiGiocati) {
		this.minutiGiocati = minutiGiocati;
	}

	public void setGolSegnati(int golSegnati) {
		this.golSegnati = golSegnati;
	}

	public void setAssist(int assist) {
		this.assist = assist;
	}

	public void setAmmonizioni(int ammonizioni) {
		this.ammonizioni = ammonizioni;
	}

	public void setEspulsione(int espulsione) {
		this.espulsione = espulsione;
	}

	public void setPortaInviolata(int portaInviolata) {
		this.portaInviolata = portaInviolata;
	}

	public void setSquadraAvversaria(Squadra squadraAvversaria) {
		this.squadraAvversaria = squadraAvversaria;
	}

	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}
}
