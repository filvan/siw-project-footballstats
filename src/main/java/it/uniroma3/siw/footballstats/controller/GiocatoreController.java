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

import it.uniroma3.siw.footballstats.controller.validator.GiocatoreValidator;
import it.uniroma3.siw.footballstats.model.Giocatore;
import it.uniroma3.siw.footballstats.service.GiocatoreService;

@Controller
public class GiocatoreController {

	@Autowired private GiocatoreService giocatoreService;
	@Autowired private GiocatoreValidator giocatoreValidator;
	
	/* ********************* */
	/* OPERAZIONI LATO ADMIN */
	/* ********************* */
	
	@GetMapping("/admin/giocatoreForm")
	public String toGiocatoreForm(Model model) {
		model.addAttribute(new Giocatore());
		return "/admin/form/giocatoreForm.html";
	}
	
	@PostMapping("/admin/giocatore")
	public String addGiocatore(@Valid @ModelAttribute ("giocatore") Giocatore giocatore, BindingResult bindingResult, Model model) {
		this.giocatoreValidator.validate(giocatore, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			this.giocatoreService.save(giocatore);
			return "/admin/addSuccesso/inserimentoGiocatore.html";
		}
		
		return "/admin/form/giocatoreForm.html";
	}
	
	// richiede tutti i giocatori (non viene specificato un id particolare)
		@GetMapping("/admin/giocatori")
		public String getAdminGiocatori(Model model) {
			List<Giocatore> giocatori = this.giocatoreService.findAll();
			model.addAttribute("giocatori", giocatori);
			return "admin/elenchi/giocatori.html";
		}
	
	@GetMapping("/admin/toDeleteGiocatore/{id}")
	public String toDeleteGiocatoreById(@PathVariable("id") Long id, Model model) {
		Giocatore giocatore =  this.giocatoreService.findById(id);
		model.addAttribute("giocatore", giocatore);
		return "admin/cancella/confermaCancellazioneGiocatore.html";
	}

	@GetMapping("/admin/confirmDeleteGiocatore/{id}")
	public String confirmDeleteGiocatoreById(@PathVariable("id") Long id, Model model) {
		this.giocatoreService.deleteById(id);
		List<Giocatore> giocatori = this.giocatoreService.findAll();
		model.addAttribute("giocatori", giocatori);
		return this.getAdminGiocatori(model);
	}
	
	@GetMapping("/admin/giocatore/{id}")
	public String getGiocatoreAdmin(@PathVariable("id") Long id, Model model) {
		Giocatore giocatore = this.giocatoreService.findById(id);
		model.addAttribute("giocatore", giocatore);
		return "admin/visualizza/giocatore.html";
	}
	
	
	/* ******************** */
	/* OPERAZIONI LATO USER */
	/* ******************** */
	
	
	@GetMapping("/user/giocatori")
	public String getUserGiocatori(Model model) {
		List<Giocatore> giocatori = this.giocatoreService.findAll();
		model.addAttribute("giocatori", giocatori);
		return "user/elenchi/giocatori.html";
	}
	
	@GetMapping("/user/giocatore/{id}")
	public String getGiocatoreUser(@PathVariable("id") Long id, Model model) {
		Giocatore giocatore = this.giocatoreService.findById(id);
		model.addAttribute("giocatore", giocatore);
		return "user/visualizza/giocatore.html";
	}
}