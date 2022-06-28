package it.uniroma3.siw.footballstats.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.footballstats.controller.validator.PrestazioneValidator;
import it.uniroma3.siw.footballstats.model.Giocatore;
import it.uniroma3.siw.footballstats.model.Prestazione;
import it.uniroma3.siw.footballstats.model.Squadra;
import it.uniroma3.siw.footballstats.service.GiocatoreService;
import it.uniroma3.siw.footballstats.service.PrestazioneService;
import it.uniroma3.siw.footballstats.service.SquadraService;

@Controller
public class PrestazioneController {

	@Autowired private PrestazioneService prestazioneService;
	@Autowired private PrestazioneValidator prestazioneValidator;
	@Autowired private GiocatoreService giocatoreService;
	@Autowired private SquadraService squadraService;

	/* ********************* */
	/* OPERAZIONI LATO ADMIN */
	/* ********************* */

	@GetMapping("/admin/inserisciPrestazione/{giocatoreId}")
	public String toPrestazioneForm(@PathVariable("giocatoreId") Long giocatoreId, Model model) {
		Giocatore giocatore = this.giocatoreService.findById(giocatoreId);
		model.addAttribute("giocatore", giocatore);

		List<Squadra> squadreAvversarie = this.squadraService.findAll();
		squadreAvversarie.remove(giocatore.getSquadra());
		model.addAttribute("squadreAvversarie", squadreAvversarie);
		model.addAttribute("prestazione", new Prestazione());
		return "/admin/form/prestazioneForm.html";
	}

	@PostMapping("/admin/inserimentoPrestazione/{giocatoreId}")
	public String addPrestazione(@PathVariable("giocatoreId") Long giocatoreId, @Valid @ModelAttribute ("prestazione") Prestazione prestazione, BindingResult bindingResult, Model model) {
		Giocatore giocatore = this.giocatoreService.findById(giocatoreId);
		prestazione.setGiocatore(giocatore);
		prestazione.formattaData();

		this.prestazioneValidator.validate(prestazione, bindingResult);

		if(!bindingResult.hasErrors()) {
			giocatore.aggiornaGiocatoreIncrementi(prestazione);
			this.giocatoreService.update(giocatore);
			this.prestazioneService.save(prestazione);

			return "/admin/addSuccesso/inserimentoPrestazione.html";
		}

		model.addAttribute("giocatore", giocatore);

		List<Squadra> squadreAvversarie = this.squadraService.findAll();
		squadreAvversarie.remove(giocatore.getSquadra());
		model.addAttribute("squadreAvversarie", squadreAvversarie);

		return "/admin/form/prestazioneForm.html";
	}

	@GetMapping("/admin/prestazioni/{giocatoreId}")
	public String getPrestazioniGiocatoreAdmin(@PathVariable ("giocatoreId") Long giocatoreId, Model model) {
		Giocatore giocatore = this.giocatoreService.findById(giocatoreId);
		model.addAttribute("giocatore", giocatore);

		List<Prestazione> elencoPrestazioni = this.prestazioneService.findAllByGiocatoreOrderByDataAsc(giocatore.getId());
		model.addAttribute("elencoPrestazioni", elencoPrestazioni);

		return "/admin/elenchi/prestazioni.html";
	}

	@GetMapping("/admin/prestazione/{prestazioneId}")
	public String getPrestazioneAdmin(@PathVariable ("prestazioneId") Long prestazioneId, Model model) {
		Prestazione prestazione = this.prestazioneService.findById(prestazioneId);
		model.addAttribute("prestazione", prestazione);

		return "/admin/visualizza/prestazione.html";
	}

	@GetMapping("/admin/toRimuoviPrestazione/{prestazioneId}")
	public String toRimuoviPrestazione(@PathVariable ("prestazioneId") Long prestazioneId, Model model) {
		Prestazione prestazione = this.prestazioneService.findById(prestazioneId);
		model.addAttribute("prestazione", prestazione);

		return "/admin/cancella/confermaCancellazionePrestazione.html";
	}

	@GetMapping("/admin/rimozionePrestazione/{prestazioneId}")
	public String rimozionePrestazione(@PathVariable ("prestazioneId") Long prestazioneId, Model model) {
		Prestazione prestazione = this.prestazioneService.findById(prestazioneId);

		Giocatore giocatore = prestazione.getGiocatore();
		giocatore.aggiornaGiocatoreDecrementi(prestazione);
		this.giocatoreService.update(giocatore);

		this.prestazioneService.delete(prestazione);

		return "/admin/cancella/confermaCancellazionePrestazioneConSuccesso.html";
	}

	@GetMapping("/admin/modifyPrestazione/{id}")
	public String modifyPrestazione(@PathVariable("id") Long id, Model model) {
		Prestazione prestazione =  this.prestazioneService.findById(id);
		model.addAttribute("prestazione", prestazione);
		Giocatore giocatore = prestazione.getGiocatore();
		model.addAttribute("giocatore", giocatore);
		List<Squadra> squadreAvversarie = this.squadraService.findAll();
		squadreAvversarie.remove(prestazione.getGiocatore().getSquadra());
		model.addAttribute("squadreAvversarie", squadreAvversarie);
		return "admin/form/modificaPrestazioneForm.html";
	}

	@PostMapping("/admin/confirmModifyPrestazione/{id}")
	public String confirmModifyPrestazione(@PathVariable("id") Long id, @ModelAttribute Prestazione prestazione, Model model) {
		Giocatore giocatore = prestazione.getGiocatore();
		giocatore.aggiornaGiocatoreDecrementi(this.prestazioneService.findById(prestazione.getId()));
		prestazione.setGiocatore(giocatore);
		this.prestazioneService.update(giocatore, prestazione.getDataInserita(), prestazione.getSquadraAvversaria(), prestazione.getId(), prestazione.getMinutiGiocati(),
				prestazione.getGolSegnati(), prestazione.getAssist(),prestazione.getPortaInviolata(),
				prestazione.getAmmonizioni(), prestazione.getEspulsione());
		giocatore.aggiornaGiocatoreIncrementi(prestazione);
		this.giocatoreService.save(giocatore);
		prestazione.formattaData();
		this.prestazioneService.save(prestazione);
		return this.getPrestazioniGiocatoreAdmin(giocatore.getId(), model);
	}

	/* ******************** */
	/* OPERAZIONI LATO USER */
	/* ******************** */

	@GetMapping("/user/prestazioni/{giocatoreId}")
	public String getPrestazioniGiocatoreUser(@PathVariable ("giocatoreId") Long giocatoreId, Model model) {
		Giocatore giocatore = this.giocatoreService.findById(giocatoreId);
		model.addAttribute("giocatore", giocatore);

		List<Prestazione> elencoPrestazioni = this.prestazioneService.findAllByGiocatoreOrderByDataAsc(giocatore.getId());
		model.addAttribute("elencoPrestazioni", elencoPrestazioni);

		return "/user/elenchi/prestazioni.html";
	}

	@GetMapping("/user/prestazione/{prestazioneId}")
	public String getPrestazioneUser(@PathVariable ("prestazioneId") Long prestazioneId, Model model) {
		Prestazione prestazione = this.prestazioneService.findById(prestazioneId);
		model.addAttribute("prestazione", prestazione);

		return "/user/visualizza/prestazione.html";
	}
}

