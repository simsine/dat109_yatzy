package no.hvl.dat109.yatzy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.hvl.dat109.service.SpillService;

/**
 * Simulerer et fullstendig yatzyspill
 */
@Service
public class YatzySimSpill {

	@Autowired
	SpillService spillService;

	Poengtabell poengtabell;

	public YatzySimSpill() {
		poengtabell = new Poengtabell();
	}

	/**
	 * Simulerer spilling av et fullstendig spill med en spiller
	 * 
	 * @return fullført poengtabell
	 */
	public Poengtabell simulerSpill() {		
		poengtabell = new Poengtabell();
		System.out.println("---------------------------------------------------");
		System.out.println("Utfører simulering av fullstendig spill\n");
		boolean ferdig = false;

		PoengType type = PoengType.ENERE;
		do {
			System.out.println("Runde: " + type.toString());

			// POST /trill {tom liste} 1
			List<Integer> terningverdier = new ArrayList<Integer>();
			terningverdier = spillService.spillTrekk(terningverdier);
			System.out.println("Spiller kastet følgende terninger: " + terningverdier.toString());
			// return terningverdier

			// bruker velger terninger
			/*
			 * List<Integer> beholdte = new ArrayList<>(terningverdier);
			 * beholdte.removeLast(); beholdte.removeLast();
			 */
			List<Integer> beholdte = velgTerninger(terningverdier, type);
			System.out.println("Spiller valgte følgende terninger å beholde: " + beholdte.toString());

			// POST /trill {beholdte terninger} 2
			terningverdier = spillService.spillTrekk(beholdte);
			System.out.println("Spiller kastet følgende terninger: " + terningverdier.toString());
			// return terningverdier

			// bruker velger terninger
			/*
			 * beholdte = new ArrayList<>(terningverdier); beholdte.removeLast();
			 * beholdte.removeLast();
			 */
			beholdte = velgTerninger(terningverdier, type);
			System.out.println("Spiller valgte følgende terninger å beholde: " + beholdte.toString());

			// POST /trill {beholdte terninger} 3
			terningverdier = spillService.spillTrekk(beholdte);
			System.out.println("Spiller kastet følgende terninger: " + terningverdier.toString());

			int poeng = spillService.registrerPoeng(poengtabell, type, terningverdier);
			System.out.println("Spiller fikk " + (poeng == 50 ? PoengType.YATZY.toString() : type.toString()) + " resultat: " + poeng);
			// return poengtabell

			// GET /nesterunde
			type = spillService.getNesteType(type, poengtabell);
			if (type == null)
				ferdig = true;
		
		} while (!ferdig);

		System.out.println("\nSimulering er ferdig, sum er: " + poengtabell.getSum());
		System.out.println("---------------------------------------------------");
		return this.poengtabell;
	}

	private List<Integer> velgTerninger(List<Integer> terningverdier, PoengType type) {
		List<Integer> beholdte = new ArrayList<>(terningverdier);
		if (beholdte.stream().distinct().count() == 1)
			return beholdte;
		switch (type) {
		case ENERE:
			beholdte = beholdte.stream().filter(t -> t.equals(1)).toList();
			break;
		case TOERE:
			beholdte = beholdte.stream().filter(t -> t.equals(2)).toList();
			break;
		case TREERE:
			beholdte = beholdte.stream().filter(t -> t.equals(3)).toList();
			break;
		case FIRERE:
			beholdte = beholdte.stream().filter(t -> t.equals(4)).toList();
			break;
		case FEMERE:
			beholdte = beholdte.stream().filter(t -> t.equals(5)).toList();
			break;
		case SEKSERE:
			beholdte = beholdte.stream().filter(t -> t.equals(6)).toList();
			break;
		case ETT_PAR:
			beholdte = beholdte.stream().filter(t -> t.equals(6)).toList();
			break;
		case TO_PAR:
			beholdte = beholdte.stream().filter(t -> t.equals(6) || t.equals(5)).toList();
			break;
		case TRE_LIKE:
			beholdte = beholdte.stream().filter(t -> t.equals(6)).toList();
			break;
		case FIRE_LIKE:
			Set<Integer> seen = new HashSet<>();
			List<Integer> duplicates = beholdte.stream().filter(e -> !seen.add(e)).distinct().sorted().toList();
			if (duplicates.size() < 5 && duplicates.size() > 0)
				beholdte = beholdte.stream().filter(t -> t.equals(duplicates.get(duplicates.size() - 1))).toList();
			else
				beholdte = new ArrayList<Integer>();
			break;
		case LITEN_STRAIGHT:
			beholdte = beholdte.stream().distinct().filter(t -> t != 6).toList();
			break;
		case STOR_STRAIGHT:
			beholdte = beholdte.stream().distinct().filter(t -> t != 1).toList();
			break;
		case HUS:
			beholdte = beholdte.stream().filter(t -> t.equals(6) || t.equals(5)).toList();
			break;
		case SJANSE:
			beholdte = beholdte.stream().filter(t -> t.equals(6)).toList();
			break;
		case YATZY:
			seen = new HashSet<>();
			duplicates = beholdte.stream().filter(e -> !seen.add(e)).distinct().sorted().toList();
			if (duplicates.size() < 5 && duplicates.size() > 0)
				beholdte = beholdte.stream().filter(t -> t.equals(duplicates.get(duplicates.size() - 1))).toList();
			else
				beholdte = new ArrayList<Integer>();
			break;
		default:
			System.out.println("Shit's fucked");
			break;

		}
		return beholdte;
	}
	private List<Integer> velgTerningerBareYatzy(List<Integer> terningverdier) {
		Set<Integer> seen = new HashSet<>();
		List<Integer> duplicates = terningverdier.stream().filter(e -> !seen.add(e)).distinct().sorted().toList();
		if (duplicates.size() < 5 && duplicates.size() > 0)
			return terningverdier.stream().filter(t -> t.equals(duplicates.get(duplicates.size() - 1))).toList();
		else
			return new ArrayList<Integer>();
	}
	
}
