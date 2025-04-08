package no.hvl.dat109.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import no.hvl.dat109.entity.Poengtabell;
import no.hvl.dat109.entity.Spill;
import no.hvl.dat109.entity.Spiller;
import no.hvl.dat109.service.SpillService;
import no.hvl.dat109.service.SpillerService;
import no.hvl.dat109.yatzy.PoengType;

@Controller
public class SpillController {

	@Autowired
	SpillService spillService;
	@Autowired
	SpillerService spillerService;

	/**
	 * Henter view for et startet spill
	 * 
	 * @param spillNr for spillet man vil hente
	 * @return
	 */
	@GetMapping("/spill/{id}")
	public String getSpill(
		@PathVariable("id") Integer spillNr, 
		Model model, 
		HttpSession session
	) {
		// Omdiriger om ikke innlogget
		if (!spillerService.erSpillerInnlogget(session)) {
			return "redirect:/innlogging";
		}
		
		Spiller spiller = spillerService.hentInnloggetSpiller(session);
		
		model.addAttribute("poengtyper", PoengType.values());
		model.addAttribute("poengtabeller", spillService.hentPoengtabellerEtterSpillnr(spillNr));
		model.addAttribute("spillnr", spillNr);
		model.addAttribute("typenaa", spillService.finnPoengType(spiller.getBrukernavn(), spillNr).orElse(null));
		
		List<Integer> terninger = (List<Integer>) session.getAttribute("terninger" + spillNr);
		if (terninger == null)
			terninger = spillService.spillTrekk();
		model.addAttribute("terninger" + spillNr, terninger);
		
		Integer antallkast = 0;
		if (session.getAttribute("antallkast" + spillNr) != null)
			antallkast = (Integer) session.getAttribute("antallkast" + spillNr);
		
		model.addAttribute("antallkastigjen", 3 - antallkast);
		
		return "spillView";
	}

	@PostMapping("/spill/opprett")
	public String postOpprettSpill(HttpSession httpSession) {
		// Omdiriger om ikke innlogget
		if (!spillerService.erSpillerInnlogget(httpSession)) {
			return "redirect:/innlogging";
		}
		Spiller spiller = spillerService.hentInnloggetSpiller(httpSession);
		
		Spill nyttSpill = spillService.opprettNyttSpill(spiller.getBrukernavn());

		return "redirect:/spill/" + nyttSpill.getSpillnr();
	}

	@PostMapping("/spill/{id}/blimed")
	public String postBliMedISpill(@PathVariable("id") Integer spillId, Model model, HttpSession httpSession) {
		// Omdiriger om ikke innlogget
		if (!spillerService.erSpillerInnlogget(httpSession)) {
			return "redirect:/innlogging";
		}
		Spiller spiller = spillerService.hentInnloggetSpiller(httpSession);
		
		Optional<Spill> spillOption = spillService.hentSpillEtterNr(spillId);

		if (spillOption.isEmpty()) {
			model.addAttribute("feilmelding", "Fant ingen spill med den id'en");
			return "/lobby";
		}

		Spill spill = spillOption.get();

		if (spillService.finnesPoengtabell(spillId, spiller.getBrukernavn())) {
			System.out.println("finnes fra for av");
			return "redirect:/spill/" + spill.getSpillnr();			
		}
			
		if (spillService.erSpillFullt(spill)) {
			model.addAttribute("feilmelding", "Spill er allerede fullt");
			return "/lobby";
		}
		

		spillService.leggtilSpiller(spiller.getBrukernavn(), spillId);
		System.out.println("finnes ikke fra for av opretter ny poengtabell");
		httpSession.setAttribute("antallkast" + spillId, 0);

		return "redirect:/spill/" + spill.getSpillnr();
	}

	@PostMapping("/spill/{id}/trill")
	public String trill(
		@PathVariable("id") Integer spillId,
		HttpSession httpSession,
		@RequestParam(required = false) List<String> valgteterninger
	) {
		// Omdiriger om ikke innlogget
		if (!spillerService.erSpillerInnlogget(httpSession)) {
			return "redirect:/innlogging";
		}

		@SuppressWarnings("unchecked")
		List<Integer> terninger = (List<Integer>) httpSession.getAttribute("terninger" + spillId);
		
		terninger = spillService.spillTrekkString(valgteterninger);

		httpSession.setAttribute("terninger", terninger);
		Integer antallkast = (Integer) httpSession.getAttribute("antallkast" + spillId);
		if (antallkast == null)
			httpSession.setAttribute("antallkast" + spillId, 1);
		else
			httpSession.setAttribute("antallkast" + spillId, antallkast + 1);

		return "redirect:/spill/" + spillId;
	}
	
	@PostMapping("/spill/{id}/registrer")
	public String registrer(
		@PathVariable("id") Integer spillId, 
		HttpSession httpSession,
		@RequestParam(required = false) List<String> valgteterninger, 
		@RequestParam String alleterninger
	) {
		// Omdiriger om ikke innlogget
		if (!spillerService.erSpillerInnlogget(httpSession)) {
			return "redirect:/innlogging";
		}
		
		List<String> terninger = Arrays.stream(alleterninger.replaceAll("[\\[\\] ]", "").split(","))
                .collect(Collectors.toList());
		
		boolean ferdig = false; 
		
		Spiller spiller = spillerService.hentInnloggetSpiller(httpSession);
		
		if (valgteterninger == null) 
			ferdig = !spillService.registrerPoeng(spiller.getBrukernavn(), spillId, terninger);
		else
			ferdig = !spillService.registrerPoeng(spiller.getBrukernavn(), spillId, valgteterninger);
		
		if (ferdig)
			return "redirect:/spill/" + spillId;
		httpSession.setAttribute("antallkast" + spillId, 0);
		
		return trill(spillId, httpSession, new ArrayList<String>());
	}
}
