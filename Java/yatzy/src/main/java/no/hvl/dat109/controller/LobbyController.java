package no.hvl.dat109.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import no.hvl.dat109.entity.Spill;
import no.hvl.dat109.service.SpillService;

@Controller
public class LobbyController {
	
	@Autowired
	SpillService spillService;
	
	@GetMapping("/")
	public String getIndex() {
		return "lobbyView";
	}
	
	@GetMapping("/lobby")
	public String getLobby() {
		List<Spill> spill = spillService.hentAlleSpill();
		
		return "lobbyView";
	}
}
