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
		this.prestazioneValidator.validate(prestazione, bindingResult);

		if(!bindingResult.hasErrors()) {
			giocatore.aggiornaGiocatore(prestazione);
			this.giocatoreService.save(giocatore);
			this.prestazioneService.save(prestazione);
//			giocatore.getPrestazioni().add(prestazione);
//			this.giocatoreService.save(giocatore);
			return "/admin/addSuccesso/inserimentoPrestazione.html";
		}

		return "/admin/form/prestazioneForm.html";
	}
}
