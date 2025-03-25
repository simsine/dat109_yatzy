package no.hvl.dat109.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import no.hvl.dat109.entiteter.Poengtabell;
import no.hvl.dat109.service.YatzyService;
import no.hvl.dat109.yatzy.PoengType;
import no.hvl.dat109.yatzy.YatzySimSpill;

@Controller
public class YatzyController {

	@Autowired
	YatzyService yatzyService;
	@Autowired
	YatzySimSpill yatzySimSpill;

	// GET

	@GetMapping("/")
	public String getIndex() {
		return "lobbyView";
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

	@GetMapping("/simuler")
	public String getSimuler(Model model) {
		Poengtabell poengTabell = yatzySimSpill.simulerSpill();
		Map<Object, Object> sortedMap = poengTabell.getAllePoeng().entrySet()
	            .stream()
	            .sorted(Map.Entry.comparingByKey())
	            .collect(Collectors.toMap(
	                Map.Entry::getKey,
	                Map.Entry::getValue,
	                (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		if (poengTabell.getPoeng(PoengType.YATZY) != 0) {
			model.addAttribute("fikkYatzy", "DU FIKK YATZY");
		}
		model.addAttribute("poengMap", sortedMap);
		model.addAttribute("poengSum", poengTabell.getSum());
		return "simView";
	}
	// POST

}
