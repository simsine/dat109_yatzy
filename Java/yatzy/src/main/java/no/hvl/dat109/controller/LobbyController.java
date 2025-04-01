package no.hvl.dat109.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
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
		if (!spillerService.erSpillerInnlogget(httpSession)) {
			return "redirect:/innlogging";
		}
		
		return "redirect:/lobby";
	}
	
	@GetMapping("/lobby")
	public String getLobby(Model model, HttpSession httpSession) {
		if (!spillerService.erSpillerInnlogget(httpSession)) {
			return "redirect:/innlogging";
		}
		
		List<Spill> spillListe = spillService.hentAlleSpill();
		
		System.out.println("Spill liste: ");
		model.addAttribute("spillListe", spillListe);
		
		return "lobbyView";
	}
}
