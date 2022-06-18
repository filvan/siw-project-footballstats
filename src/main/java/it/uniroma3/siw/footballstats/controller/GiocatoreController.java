package it.uniroma3.siw.footballstats.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	
	
	
	/* ******************** */
	/* OPERAZIONI LATO USER */
	/* ******************** */
}
