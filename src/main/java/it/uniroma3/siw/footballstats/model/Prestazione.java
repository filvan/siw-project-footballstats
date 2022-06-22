package it.uniroma3.siw.footballstats.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

@Entity
public class Prestazione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String dataInserita;
	
	private String data;
	
	@NotNull
	@Min(value = 1)
	private Integer minutiGiocati;
	
	@NotNull
	@Min(value = 0)
	private Integer golSegnati;
	
	@NotNull
	@Min(value = 0)
	private Integer assist;
	
	@NotNull
	@Min(value = 0)
	@Max(value = 2)
	private Integer ammonizioni;
	
	@NotNull
	@Min(value = 0)
	@Max(value = 1)
	private Integer espulsione;
	
	@NotNull
	@Min(value = 0)
	@Max(value = 1)
	private Integer portaInviolata;
	
	@OneToOne
	private Squadra squadraAvversaria;
	
	@ManyToOne
	private Giocatore giocatore;
	
	/* *********** */
	/* COSTRUTTORI */
	/* *********** */
	
	public Prestazione() {
		this.minutiGiocati = 0;
		this.golSegnati = 0;
		this.assist = 0;
		this.ammonizioni = 0;
		this.espulsione = 0;
		this.portaInviolata = 0;
	}
	
	public Prestazione(String data, Integer minutiGiocati, Integer golSegnati, Integer assist, Integer ammonizioni,
			Integer espulsione, Integer portaInviolata) {
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

	public Integer getMinutiGiocati() {
		return minutiGiocati;
	}

	public Integer getGolSegnati() {
		return golSegnati;
	}

	public Integer getAssist() {
		return assist;
	}

	public Integer getAmmonizioni() {
		return ammonizioni;
	}

	public Integer getEspulsione() {
		return espulsione;
	}

	public Integer getPortaInviolata() {
		return portaInviolata;
	}
	
	public Squadra getSquadraAvversaria() {
		return squadraAvversaria;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}
	
	public String getDataInserita() {
		return dataInserita;
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

	public void setMinutiGiocati(Integer minutiGiocati) {
		this.minutiGiocati = minutiGiocati;
	}

	public void setGolSegnati(Integer golSegnati) {
		this.golSegnati = golSegnati;
	}

	public void setAssist(Integer assist) {
		this.assist = assist;
	}

	public void setAmmonizioni(Integer ammonizioni) {
		this.ammonizioni = ammonizioni;
	}

	public void setEspulsione(Integer espulsione) {
		this.espulsione = espulsione;
	}

	public void setPortaInviolata(Integer portaInviolata) {
		this.portaInviolata = portaInviolata;
	}

	public void setSquadraAvversaria(Squadra squadraAvversaria) {
		this.squadraAvversaria = squadraAvversaria;
	}

	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}

	public void setDataInserita(String dataInserita) {
		this.dataInserita = dataInserita;
	}
	
	/* ****************** */
	/* METODI DI SUPPORTO */
	/* ****************** */
	public void formattaData() {
		String dataInserita = this.getDataInserita();
		
		String anno = dataInserita.substring(6, 10);
		String mese = dataInserita.substring(2, 6);
		String giorno = dataInserita.substring(0, 2);
		
		String data = anno + mese + giorno;
		this.setData(data);
	}
}
