package no.hvl.dat109.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import no.hvl.dat109.service.YatzyService;
import no.hvl.dat109.yatzy.Poengtabell;
import no.hvl.dat109.yatzy.YatzySimSpill;

@Controller
public class YatzyController {

	@Autowired
	YatzyService yatzyService;
	
	//GET
	
	@GetMapping("/")
	public String getIndex() {
		return "lobbyView";
	}
	
	@GetMapping("/lobby")
	public String getLobby() {
		return "lobbyView";
	}
	
	@GetMapping("/innlogging")
	public String getInnlogging() {
		return "logginnView";
	}
	
	@GetMapping("/registrering")
	public String getRegistrering() {
		return "registrerView";
	}
	
	@GetMapping("/spill")
	public String getSpill() {
		return "spillView";
	}
	
	@GetMapping("/spillhistorikk")
	public String getSpillhistorikk() {
		return "spillHistorikkView";
	}
	
	//POST
	
	@PostMapping("/simuler")
	public String postSimuler() {
		YatzySimSpill yatzySimSpill = new YatzySimSpill();
		Poengtabell poengTabell = yatzySimSpill.simulerSpill();
		
		return "spillView";
	}
	
}
