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
import no.hvl.dat109.entity.Spill;
import no.hvl.dat109.service.SpillService;
import no.hvl.dat109.yatzy.PoengType;

@Controller
public class SpillController {

	@Autowired
	SpillService spillService;

	/**
	 * Henter view for et startet spill
	 * 
	 * @param spillId for spillet man vil hente
	 * @return
	 */
	@GetMapping("/spill/{id}")
	public String getSpill(@PathVariable("id") String spillId, Model model, HttpSession session) {
		model.addAttribute("poengtyper", PoengType.values());
		model.addAttribute("poengtabeller", spillService.hentPoengtabellerEtterSpillnr(Integer.parseInt(spillId)));
		model.addAttribute("spillnr", spillId);
		List<Integer> terninger = (List<Integer>) session.getAttribute("terninger");
		if (terninger == null)
			terninger = spillService.spillTrekk();
		model.addAttribute("terninger", terninger);
		
		Integer antallkast = 0;
		if (session.getAttribute("antallkast" + spillId) != null)
			antallkast = (Integer) session.getAttribute("antallkast" + spillId);
		
		model.addAttribute("antallkastigjen", 3 - antallkast);
		
		
		//test
		session.setAttribute("brukernavn", "XFaze");
		
		return "spillView";
	}

	@PostMapping("/spill/opprett")
	public String postOpprettSpill(String brukernavn) {
		Spill nyttSpill = spillService.opprettNyttSpill(brukernavn);

		return "redirect:/spill/" + nyttSpill.getSpillnr();
	}

	@PostMapping("/spill/{id}/blimed")
	public String postBliMedISpill(@PathVariable("id") Integer spillId, String brukernavn, Model model, HttpSession session) {
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

		spillService.leggtilSpiller(brukernavn, spillId);
		session.setAttribute("antallkast" + spillId, 0);

		return "redirect:/spill/" + spill.getSpillnr();
	}

	@PostMapping("/spill/{id}/trill")
	public String trill(@PathVariable("id") Integer spillId, String brukernavn, HttpSession session,
			@RequestParam(required = false) List<String> valgteterninger) {

		List<Integer> terninger = (List<Integer>) session.getAttribute("terninger");
		
		terninger = spillService.spillTrekkString(valgteterninger);

		session.setAttribute("terninger", terninger);
		Object antallkast = session.getAttribute("antallkast" + spillId);
		if (antallkast == null)
			session.setAttribute("antallkast" + spillId, 1);
		else
			session.setAttribute("antallkast" + spillId, (Integer) antallkast + 1);

		return "redirect:/spill/" + spillId;
	}
	
	@PostMapping("/spill/{id}/registrer")
	public String registrer(@PathVariable("id") Integer spillId, String brukernavn, HttpSession session,
			@RequestParam(required = false) List<String> valgteterninger, @RequestParam String alleterninger) {
		List<String> terninger = Arrays.stream(alleterninger.replaceAll("[\\[\\] ]", "").split(","))
                .collect(Collectors.toList());
		
		boolean ferdig = false; 
		
		if (valgteterninger == null)
			ferdig = !spillService.registrerPoeng((String) session.getAttribute("brukernavn"), spillId, terninger);
		else
			ferdig = !spillService.registrerPoeng((String) session.getAttribute("brukernavn"), spillId, valgteterninger);
		
		if (ferdig)
			return "redirect:/spill/" + spillId;
		session.setAttribute("antallkast" + spillId, 0);
		
		return trill(spillId, brukernavn, session, new ArrayList<String>());
	}
}
