package no.hvl.dat109.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LobbyController {
	
	@GetMapping("/")
	public String getIndex() {
		return "lobbyView";
	}
	
	@GetMapping("/lobby")
	public String getLobby() {
		return "lobbyView";
	}
}
