package no.hvl.dat109.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpillhistorikkController {

	@GetMapping("/spillhistorikk")
	public String getSpillhistorikk() {
		return "spillHistorikkView";
	}
	
}
