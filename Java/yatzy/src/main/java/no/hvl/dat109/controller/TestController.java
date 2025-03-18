package no.hvl.dat109.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("/")
	public String index() {
		return "testView";
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
}
