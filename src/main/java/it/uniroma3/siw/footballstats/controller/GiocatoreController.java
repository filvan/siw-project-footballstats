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
import it.uniroma3.siw.footballstats.model.Squadra;
import it.uniroma3.siw.footballstats.model.User;
import it.uniroma3.siw.footballstats.service.GiocatoreService;
import it.uniroma3.siw.footballstats.service.SquadraService;
import it.uniroma3.siw.footballstats.service.UserService;

@Controller
public class GiocatoreController {

	@Autowired private GiocatoreService giocatoreService;
	@Autowired private GiocatoreValidator giocatoreValidator;
	@Autowired private SquadraService squadraService;
	@Autowired private UserService userService;
		
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

	@GetMapping("/admin/giocatore/{id}")
	public String getGiocatoreAdmin(@PathVariable("id") Long id, Model model) {
		Giocatore giocatore = this.giocatoreService.findById(id);		
		model.addAttribute("giocatore", giocatore);
		return "admin/visualizza/giocatore.html";
	}

	// richiede tutti i giocatori (non viene specificato un id particolare)
	@GetMapping("/admin/giocatori")
	public String getAdminGiocatori(Model model) {
		List<Giocatore> giocatori = this.giocatoreService.findAllByOrderBySquadraNomeAscRuoloDesc();
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

	@GetMapping("/admin/assegnaSquadra/{idGiocatore}")
	public String toAssegnaSquadra(@PathVariable("idGiocatore") Long id, Model model) {
		Giocatore giocatore = this.giocatoreService.findById(id);
		model.addAttribute("giocatore", giocatore);
		
		List<Squadra> elencoSquadre = this.squadraService.findAll();
		model.addAttribute("elencoSquadre", elencoSquadre);
		model.addAttribute("squadra", new Squadra());
		
		return "/admin/assegna/assegnaSquadraPerGiocatore.html";
	}
	
	@PostMapping("/admin/assegnazioneSquadra/{idGiocatore}")
	public String assegnazioneSquadra(@PathVariable("idGiocatore") Long id, @ModelAttribute("squadra") Squadra squadra, Model model) {
		Giocatore giocatore = this.giocatoreService.findById(id);
		giocatore.setSquadra(squadra);
		this.giocatoreService.update(giocatore);
		
		return "/admin/assegna/assegnaSquadraPerGiocatoreConSuccesso.html";
	}
	
	@GetMapping("/admin/giocatoriPreferiti")
	public String getClassificaPreferenze(Model model) {
		List<Giocatore> elencoGiocatori = this.giocatoreService.findAllByOrderByNumeroPreferenzeDesc();
		model.addAttribute("elencoGiocatori", elencoGiocatori);
		
		return "/admin/elenchi/giocatoriPreferiti.html";	
	}
	
	/* ******************** */
	/* OPERAZIONI LATO USER */
	/* ******************** */

	@GetMapping("/user/giocatore/{id}")
	public String getGiocatoreUser(@PathVariable("id") Long id, Model model) {
		Giocatore giocatore = this.giocatoreService.findById(id);
		model.addAttribute("giocatore", giocatore);
		
		User user = AuthenticationController.user;
		model.addAttribute("user", user);
		
		boolean traIPreferiti = false;
		if (user.getGiocatoriPreferiti().contains(giocatore))
			traIPreferiti = true;
		model.addAttribute("condizione", traIPreferiti);
		

		return "user/visualizza/giocatore.html";
	}

	@GetMapping("/user/giocatori")
	public String getUserGiocatori(Model model) {
		List<Giocatore> giocatori = this.giocatoreService.findAllByOrderBySquadraNomeAscRuoloDesc();
		model.addAttribute("giocatori", giocatori);
		return "user/elenchi/giocatori.html";
	}
	
	@GetMapping("/user/classifiche")
	public String toMenuClassifiche(Model model) {
		return "/user/classifiche/menuClassifiche.html";
	}
	
	@GetMapping("/user/classifiche/presenze")
	public String getClassificaPresenze(Model model) {
		List<Giocatore> elencoGiocatori = this.giocatoreService.findAllByOrderByPresenzeTotaliDesc();
		model.addAttribute("elencoGiocatori", elencoGiocatori);
		return "/user/classifiche/classificaPresenze.html";
	}
	
	@GetMapping("/user/classifiche/minutiGiocati")
	public String getClassificaMinutiGiocati(Model model) {
		List<Giocatore> elencoGiocatori = this.giocatoreService.findAllByOrderByMinutiGiocatiTotaliDesc();
		model.addAttribute("elencoGiocatori", elencoGiocatori);
		return "/user/classifiche/classificaMinuti.html";
	}
	
	@GetMapping("/user/classifiche/golSegnati")
	public String getClassificaGol(Model model) {
		List<Giocatore> elencoGiocatori = this.giocatoreService.findAllByOrderByGolSegnatiTotaliDesc();
		model.addAttribute("elencoGiocatori", elencoGiocatori);
		return "/user/classifiche/classificaGol.html";
	}
	
	@GetMapping("/user/classifiche/assist")
	public String getClassificaAssist(Model model) {
		List<Giocatore> elencoGiocatori = this.giocatoreService.findAllByOrderByAssistTotaliDesc();
		model.addAttribute("elencoGiocatori", elencoGiocatori);
		return "/user/classifiche/classificaAssist.html";
	}
	
	@GetMapping("/user/classifiche/cleanSheet")
	public String getClassificaCleanSheet(Model model) {
		List<Giocatore> elencoGiocatori = this.giocatoreService.findAllByOrderByCleanSheetTotaliDesc();
		model.addAttribute("elencoGiocatori", elencoGiocatori);
		return "/user/classifiche/classificaCleanSheet.html";
	}
	
	@GetMapping("/user/classifiche/ammonizioni")
	public String getClassificaAmmonizioni(Model model) {
		List<Giocatore> elencoGiocatori = this.giocatoreService.findAllByOrderByAmmonizioniTotaliDesc();
		model.addAttribute("elencoGiocatori", elencoGiocatori);
		return "/user/classifiche/classificaAmmonizioni.html";
	}
	
	@GetMapping("/user/classifiche/espulsioni")
	public String getClassificaEspulsioni(Model model) {
		List<Giocatore> elencoGiocatori = this.giocatoreService.findAllByOrderByEspulsioniTotaliDesc();
		model.addAttribute("elencoGiocatori", elencoGiocatori);
		return "/user/classifiche/classificaEspulsioni.html";
	}
	
	@GetMapping("/user/toConfrontaGiocatori")
	public String toConfrontaGiocatori(Model model) {
		model.addAttribute("giocatore1", new Giocatore());
		model.addAttribute("giocatore2", new Giocatore());
		
		List<Giocatore> elencoGiocatori = this.giocatoreService.findAllByOrderBySquadraNomeAscRuoloDesc();
		model.addAttribute("elencoGiocatori", elencoGiocatori);
		
		return "/user/confronta/toConfrontaGiocatori.html";
	}
	
	@GetMapping("/user/confrontaGiocatori")
	public String confrontaGiocatori(@ModelAttribute ("giocatore1") Giocatore giocatore1, @ModelAttribute ("giocatore2") Giocatore giocatore2, Model model) {
		model.addAttribute("giocatore1", giocatore1);
		model.addAttribute("giocatore2", giocatore2);
		
		return "/user/confronta/confrontaGiocatori.html";
	}
	
	@GetMapping("/user/giocatoriPreferiti")
	public String getGiocatoriPreferiti(Model model) {
		
		User user = AuthenticationController.user;
		model.addAttribute("user", user);
		
		List<Giocatore> giocatoriPreferiti = user.getGiocatoriPreferiti();
		model.addAttribute("giocatoriPreferiti", giocatoriPreferiti);
		
		return "/user/elenchi/giocatoriPreferiti.html";
	}
	
	@GetMapping("/user/addGiocatorePreferito/{idGiocatore}")
	public String addGiocatorePreferito(@PathVariable ("idGiocatore") Long idGiocatore, Model model) {
		Giocatore giocatore = this.giocatoreService.findById(idGiocatore);
		giocatore.setNumeroPreferenze(giocatore.getNumeroPreferenze() + 1);
		this.giocatoreService.update(giocatore);
		
		User user = AuthenticationController.user;
		user.getGiocatoriPreferiti().add(giocatore);
		this.userService.update(user);
		
		return this.getGiocatoreUser(giocatore.getId(), model);		
	}
	
	@GetMapping("/user/rimuoviGiocatorePreferito/{idGiocatore}")
	public String rimuoviGiocatorePreferito(@PathVariable ("idGiocatore") Long idGiocatore, Model model) {
		Giocatore giocatore = this.giocatoreService.findById(idGiocatore);
		if (giocatore.getNumeroPreferenze() > 0)
			giocatore.setNumeroPreferenze(giocatore.getNumeroPreferenze() - 1);
		this.giocatoreService.update(giocatore);
		
		User user = AuthenticationController.user;
		user.getGiocatoriPreferiti().remove(giocatore);
		this.userService.update(user);
		
		model.addAttribute("giocatoriPreferiti", user.getGiocatoriPreferiti());
		return "/user/elenchi/giocatoriPreferiti.html";
		
	}
}