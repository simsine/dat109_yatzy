package no.hvl.dat109.controller;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpSession;
import no.hvl.dat109.entity.Spill;
import no.hvl.dat109.entity.Spiller;
import no.hvl.dat109.service.SpillService;
import no.hvl.dat109.service.SpillerService;
import no.hvl.dat109.service.SpillgjennomgangService;

@Controller
public class SpillhistorikkController {


	@Autowired
	private SpillgjennomgangService spillgjennomgangService;
	
	@Autowired
	private SpillService spillService;

	
	@GetMapping("/yatzy-1.0/spillhistorikk/{brukernavn}")
	public String getSpillhistorikk(@PathVariable("brukernavn") String brukernavn, Model model, HttpSession session) {
		List<Spill> aktivespill = new ArrayList<>(spillService.hentAlleIkkeFerdigeSpillForSpiller(brukernavn));
		aktivespill.sort((o1, o2) -> o1.getTidopprettet().compareTo(o2.getTidopprettet()));
		List<Spill> avsluttedespill = new ArrayList<>(spillService.hentAlleFerdigeSpillForSpiller(brukernavn));
		avsluttedespill.sort((o1, o2) -> o1.getTidopprettet().compareTo(o2.getTidopprettet()));
		model.addAttribute("aktivespill", aktivespill);
		model.addAttribute("avsluttedespill", avsluttedespill);
		model.addAttribute("brukernavn", brukernavn);
		Spiller spiller = (Spiller) session.getAttribute("spiller");
		if (!spiller.getBrukernavn().equals(brukernavn)) {
			return "redirect:/yatzy-1.0/lobby";
		}
		
		return "spillHistorikkView";
	}
}
