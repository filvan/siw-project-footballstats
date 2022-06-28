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

import it.uniroma3.siw.footballstats.controller.validator.SquadraValidator;
import it.uniroma3.siw.footballstats.model.Giocatore;
import it.uniroma3.siw.footballstats.model.Squadra;
import it.uniroma3.siw.footballstats.service.GiocatoreService;
import it.uniroma3.siw.footballstats.service.SquadraService;

@Controller
public class SquadraController {

	@Autowired private SquadraService squadraService;
	@Autowired private SquadraValidator squadraValidator;
	@Autowired private GiocatoreService giocatoreService;

	/* ********************* */
	/* OPERAZIONI LATO ADMIN */
	/* ********************* */

	@GetMapping("/admin/squadraForm")
	public String toSquadraForm(Model model) {
		model.addAttribute(new Squadra());
		return "/admin/form/squadraForm.html";
	}

	@PostMapping("/admin/squadra")
	public String addSquadra(@Valid @ModelAttribute ("squadra") Squadra squadra, BindingResult bindingResult, Model model) {
		this.squadraValidator.validate(squadra, bindingResult);

		if(!bindingResult.hasErrors()) {
			this.squadraService.save(squadra);
			return "/admin/addSuccesso/inserimentoSquadra.html";
		}

		return "/admin/form/squadraForm.html";
	}

	@GetMapping("/admin/squadra/{id}")
	public String getSquadraAdmin(@PathVariable("id") Long id, Model model) {
		Squadra squadra = this.squadraService.findById(id);
		model.addAttribute("squadra", squadra);

		List<Giocatore> elencoGiocatori = this.giocatoreService.findAllBySquadraIdOrderByRuoloDesc(id);
		model.addAttribute("elencoGiocatori", elencoGiocatori);
		
		return "/admin/visualizza/squadra.html";
	}

	// richiede tutti i squadre (non viene specificato un id particolare)
	@GetMapping("/admin/squadre")
	public String getAdminSquadre(Model model) {
		List<Squadra> squadre = this.squadraService.findAllByOrderByNomeAsc();
		model.addAttribute("squadre", squadre);
		return "/admin/elenchi/squadre.html";
	}

	@GetMapping("/admin/toDeleteSquadra/{id}")
	public String toDeleteSquadraById(@PathVariable("id") Long id, Model model) {
		Squadra squadra =  this.squadraService.findById(id);
	
		for(Giocatore g: squadra.getGiocatori()) {
			g.setSquadra(null);
			this.giocatoreService.save(g);
		}
			
		model.addAttribute("squadra", squadra);
		return "/admin/cancella/confermaCancellazioneSquadra.html";
	}

	@GetMapping("/admin/confirmDeleteSquadra/{id}")
	public String confirmDeleteSquadraById(@PathVariable("id") Long id, Model model) {
		this.squadraService.deleteById(id);
		List<Squadra> squadre = this.squadraService.findAll();
		model.addAttribute("squadre", squadre);
		return this.getAdminSquadre(model);
	}
	
	@GetMapping("/admin/rimuoviGiocatore/{idGiocatore}")
	public String rimuoviGiocatore(@PathVariable("idGiocatore") Long id, Model model) {
		Giocatore giocatore = this.giocatoreService.findById(id);
		giocatore.setSquadra(null);
		this.giocatoreService.update(giocatore);
		
		return "/admin/rimuovi/rimuoviSquadraPerGiocatoreConSuccesso.html";
	}
	
	@GetMapping("/admin/modifySquadra/{id}")
	public String modifySquadra(@PathVariable("id") Long id, Model model) {
		Squadra squadra =  this.squadraService.findById(id);
		model.addAttribute("squadra", squadra);
		return "admin/form/modificaSquadraForm.html";
	}
	
	@PostMapping("/admin/confirmModifySquadra/{id}")
	public String confirmModifySquadra(@Valid @ModelAttribute("squadra") Squadra squadra, Model model, BindingResult bindingResult) {
		this.squadraValidator.validate(squadra, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.squadraService.update(squadra.getId(), squadra.getNome(), squadra.getCitta());
			model.addAttribute("squadre", this.squadraService.findAll());
			return "admin/elenchi/squadre.html";
		}
		return "admin/form/modificaSquadraForm.html";
	}
	
	/* ******************** */
	/* OPERAZIONI LATO USER */
	/* ******************** */


	@GetMapping("/user/squadra/{id}")
	public String getSquadraUser(@PathVariable("id") Long id, Model model) {
		Squadra squadra = this.squadraService.findById(id);
		model.addAttribute("squadra", squadra);
		
		List<Giocatore> elencoGiocatori = this.giocatoreService.findAllBySquadraIdOrderByRuoloDesc(id);
		model.addAttribute("elencoGiocatori", elencoGiocatori);
		
		return "/user/visualizza/squadra.html";
	}

	@GetMapping("/user/squadre")
	public String getUserSquadre(Model model) {
		List<Squadra> squadre = this.squadraService.findAllByOrderByNomeAsc();
		model.addAttribute("squadre", squadre);
		return "/user/elenchi/squadre.html";
	}
}