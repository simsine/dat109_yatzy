package no.hvl.dat109.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.hvl.dat109.entity.Spill;
import no.hvl.dat109.entity.Spiller;

@Service
public class LeaderboardService {

	@Autowired
	private SpillService spillService;

	@Autowired
	private SpillerService spillerService;

	public Map<String, Integer> hentPoeng() {
		List<Spill> spillListe = spillService.hentAlleSpill();
		List<Spill> ferdigeSpill = spillListe.stream().filter(t -> t.erSpillFerdig()).toList();

		Map<String, Integer> spillerPoengMap = new LinkedHashMap<String, Integer>();

		List<Spiller> alleSpillere = spillerService.hentAlleSpillere();

		alleSpillere.stream().forEach(t -> spillerPoengMap.put(t.getBrukernavn(), 0));

		ferdigeSpill.stream().flatMap(t -> t.getPoengtabeller().stream()) // konverter List til Stream
				.forEach(poeng -> {
					String brukernavn = poeng.getPoengtabellId().getBrukernavn();
					if (poeng.getSum()>spillerPoengMap.get(brukernavn)) {
						spillerPoengMap.put(brukernavn, poeng.getSum());						
					}
				});

		return spillerPoengMap.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

}
