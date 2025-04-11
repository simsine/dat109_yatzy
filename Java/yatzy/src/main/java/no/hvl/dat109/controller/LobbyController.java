package no.hvl.dat109.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import no.hvl.dat109.entity.Spill;
import no.hvl.dat109.service.SpillService;
import no.hvl.dat109.service.SpillerService;

@Controller
public class LobbyController {
	
	@Autowired
	SpillService spillService;
	@Autowired
	SpillerService spillerService;
	
	@GetMapping("/")
	public String getIndex(HttpSession httpSession) {
		// Omdiriger om ikke innlogget
		if (!spillerService.erSpillerInnlogget(httpSession)) {
			return "redirect:/yatzy-1.0/innlogging";
		}
		
		return "redirect:/yatzy-1.0/lobby";
	}
	@GetMapping("/yatzy-1.0/")
	public String getIndexYatzy(HttpSession httpSession) {
		// Omdiriger om ikke innlogget
		if (!spillerService.erSpillerInnlogget(httpSession)) {
			return "redirect:/yatzy-1.0/innlogging";
		}
		
		return "redirect:/yatzy-1.0/lobby";
	}
	
	@GetMapping("/yatzy-1.0/lobby")
	public String getLobby(Model model, HttpSession httpSession) {
		// Omdiriger om ikke innlogget
		if (!spillerService.erSpillerInnlogget(httpSession)) {
			return "redirect:/yatzy-1.0/innlogging";
		}
		
		List<Spill> spillListe = spillService.hentAlleIkkeFulleOgIkkeStartetSpill();
		
		model.addAttribute("spillListe", spillListe);
		
		return "lobbyView";
	}
}
