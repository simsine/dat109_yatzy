package no.hvl.dat109.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import no.hvl.dat109.entity.Spill;
import no.hvl.dat109.entity.Spiller;
import no.hvl.dat109.service.SpillService;
import no.hvl.dat109.service.SpillerService;

@Controller
public class AdminController {

	@Autowired
	SpillService spillService;
	@Autowired
	SpillerService spillerService;

	@GetMapping("/admin")
	public String getAdmin(Model model, HttpSession session) {

		if (!spillerService.erSpillerInnlogget(session)) {
			return "redirect:/innlogging";
		}
		List<Spill> spillListe = spillService.hentAlleSpill();
		model.addAttribute("spillListe", spillListe);
		
		List<Spiller> spillerListe = spillerService.hentAlleSpillere();
		spillerListe.sort(Comparator.comparing(Spiller::getBrukernavn));
		model.addAttribute("spillerListe", spillerListe);

		return spillerService.erAdmin(session) ? "adminView" : "redirect:/lobby";

	}

	@PostMapping("/admin/slett/{id}")
	public String postSlettSpill(@PathVariable("id") Integer spillnr) {
		spillService.slettSpill(spillnr);
		return "redirect:/admin";
	}
	
	@PostMapping("/admin/deaktiver/{brukernavn}")
	public String postDeaktiverBruker(@PathVariable("brukernavn") String brukernavn) {
		spillerService.deaktiverBruker(brukernavn);
		return "redirect:/admin";
	}
	
	@PostMapping("/admin/aktiver/{brukernavn}")
	public String postAktiverBruker(@PathVariable("brukernavn") String brukernavn) {
		spillerService.aktiverBruker(brukernavn);
		return "redirect:/admin";
	}
}
