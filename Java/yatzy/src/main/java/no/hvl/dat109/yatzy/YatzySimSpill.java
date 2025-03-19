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

	public Poengtabell simulerSpill() {
		for (PoengType type : PoengType.values()) {
			List<Integer> beholdte = new ArrayList<Integer>();
			beholdte = yatzyService.spillTrekk(beholdte);
			beholdte.removeLast();
			beholdte.removeLast();
			beholdte = yatzyService.spillTrekk(beholdte);
			beholdte.removeLast();
			beholdte.removeLast();
			beholdte = yatzyService.spillTrekk(beholdte);
			yatzyService.registrerPoeng(poengtabell, type, beholdte);
		}
		return this.poengtabell;
	}
}
