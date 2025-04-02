package no.hvl.dat109.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import no.hvl.dat109.service.SpillerService;

@Controller
public class AdminController {
	@Autowired
	SpillerService spillerService;
	
	@GetMapping("/admin")
	public String admin(Model model, HttpSession session){		
		
		if(spillerService.erSpillerInnlogget(session)) {
			return "redirect:/innlogging";
		}
		
		return spillerService.erAdmin(session) ? "adminVeiw" : "redirect:/lobby";
		
	}
}
