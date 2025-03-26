package no.hvl.dat109.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@PostMapping("/spill/opprett/")
	public String postOpprettSpill(String brukernavn) {
		Spill nyttSpill = spillService.opprettNyttSpill(brukernavn);
		
		return "redirect:/spill/" + nyttSpill.getSpillnr();
	}
	
	@PostMapping("/spill/{id}/blimed")
	public String postBliMedISpill(@PathVariable("id") Integer spillId, Model model) {
		Optional<Spill> spillOption = spillService.hentSpillEtterNr(spillId);
		
		if (spillOption.isEmpty()) {
			model.addAttribute("feilmelding", "Fant ingen spill med den id'en");
			return "/lobby";
		}
		
		Spill spill = spillOption.get();
		
		if (spillService.erSpillFullt(spill)) {
			model.addAttribute("feilmelding", "Spill er allerede fullt");
			return "/lobby";
		}
		
//		spillService.leggtilSpiller();
		
		return "redirect:/spill/" + spill.getSpillnr();
	}
	
}
