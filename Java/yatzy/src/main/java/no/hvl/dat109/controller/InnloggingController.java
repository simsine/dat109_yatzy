package no.hvl.dat109.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InnloggingController {
	
	@GetMapping("/innlogging")
	public String getInnlogging() {
		return "logginnView";
	}
}
