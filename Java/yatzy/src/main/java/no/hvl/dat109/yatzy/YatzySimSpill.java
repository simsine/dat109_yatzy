package no.hvl.dat109.yatzy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.hvl.dat109.service.YatzyService;

/**
 * Simulerer et fullstendig yatzyspill
 */
@Service
public class YatzySimSpill {
	
	@Autowired YatzyService yatzyService;
	
	Poengtabell poengtabell;

	public YatzySimSpill() {
		poengtabell = new Poengtabell();
	}

	/**
	 * Simulerer spilling av et fullstendig spill med en spiller
	 * @return fullført poengtabell
	 */
	public Poengtabell simulerSpill() {
		System.out.println("---------------------------------------------------");
		System.out.println("Utfører simulering av fullstendig spill\n");
		for (PoengType type : PoengType.values()) {
			System.out.println("Runde: " + type.toString());
			
			//POST /trill {tom liste} 1
			List<Integer> beholdte = new ArrayList<Integer>();
			beholdte = yatzyService.spillTrekk(beholdte);
			System.out.println("Spiller kastet følgende terninger: " + beholdte.toString());
			//return terningverdier
			
			//bruker velger terninger
			beholdte.removeLast();
			beholdte.removeLast();
			System.out.println("Spiller valgte følgende terninger å beholde: " + beholdte.toString());
			
			//POST /trill {beholdte terninger} 2
			beholdte = yatzyService.spillTrekk(beholdte);
			System.out.println("Spiller kastet følgende terninger: " + beholdte.toString());
			//return terningverdier

			//bruker velger terninger
			beholdte.removeLast();
			beholdte.removeLast();
			System.out.println("Spiller valgte følgende terninger å beholde: " + beholdte.toString());
			
			//POST /trill {beholdte terninger} 3
			beholdte = yatzyService.spillTrekk(beholdte);
			System.out.println("Spiller kastet følgende terninger: " + beholdte.toString());

			int poeng = yatzyService.registrerPoeng(poengtabell, type, beholdte);
			System.out.println("Spiller fikk " + type.toString() + " resultat: " + poeng);
			//return poeng

		}
		System.out.println("\nSimulering er ferdig, sum er: " + poengtabell.getSum());
		System.out.println("---------------------------------------------------");
		return this.poengtabell;
	}
}
