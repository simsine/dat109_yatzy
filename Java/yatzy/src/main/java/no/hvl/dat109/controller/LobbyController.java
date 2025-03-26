package no.hvl.dat109.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import no.hvl.dat109.entity.Spill;
import no.hvl.dat109.service.SpillService;

@Controller
public class LobbyController {
	
	@Autowired
	SpillService spillService;
	
	@GetMapping("/")
	public String getIndex() {
		return "redirect:/lobby";
	}
	
	@GetMapping("/lobby")
	public String getLobby(Model model) {
		List<Spill> spillListe = spillService.hentAlleSpill();
		
		System.out.println("Spill liste: ");
		model.addAttribute("spillListe", spillListe);
		
		return "lobbyView";
	}
}
