package no.hvl.dat109.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import no.hvl.dat109.entity.Poengtabell;
import no.hvl.dat109.entity.Spill;
import no.hvl.dat109.repo.SpillRepo;
import no.hvl.dat109.service.SpillService;
import no.hvl.dat109.service.SpillgjennomgangService;

@Controller
public class SpillhistorikkController {


	@Autowired
	private SpillgjennomgangService spillgjennomgangService;
	
	@Autowired
	private SpillService spillService;
	
	@GetMapping("/spillhistorikk/{brukernavn}")
	public String getSpillhistorikk(@PathVariable("brukernavn") String brukernavn, Model model) {
		List<Poengtabell> tidligereSpill = spillgjennomgangService.hentAlleTidligereSpillForSpiller(brukernavn);
		List<Spill> aktivespill = spillService.hentAlleIkkeFerdigeSpillForSpiller(brukernavn);
		List<Spill> avsluttedespill = spillService.hentAlleFerdigeSpillForSpiller(brukernavn);
		tidligereSpill.sort((o1, o2) -> o1.getSpill().getTidopprettet().compareTo(o2.getSpill().getTidopprettet()));
		model.addAttribute("aktivespill", aktivespill);
		model.addAttribute("avsulttedespill", avsluttedespill);
		model.addAttribute("brukernavn", brukernavn);
		
		return "spillHistorikkView";
	}
}
