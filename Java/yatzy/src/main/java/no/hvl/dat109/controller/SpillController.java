package no.hvl.dat109.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import no.hvl.dat109.entity.Spill;
import no.hvl.dat109.service.SpillService;


@Controller
public class SpillController {
	
	@Autowired
	SpillService spillService;
	
	/**
	 * Henter view for et startet spill
	 * @param spillId for spillet man vil hente
	 * @return
	 */
	@GetMapping("/spill/{id}")
	public String getSpill(@PathVariable("id") String spillId) {
		
		return "spillView";
	}
	
	@PostMapping("/spill/opprett")
	public String postOpprettSpill() {
		Spill nyttSpill = spillService.opprettNyttSpill();
		
		return "redirect:/spill/" + nyttSpill.getId();
	}
	
	@PostMapping("/spill/{id}/blimed")
	public String postBliMedISpill(@PathVariable("id") String spillId, Model model) {
		Optional<Spill> spill = spillService.hentSpillEtterId(spillId);
		
		if (spill.)
		
		if (spillService.erSpillFullt()) {
			model.addAttribute("feilmelding", "Spill er allerede fullt");
		}
		
	}
	
}
