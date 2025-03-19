package no.hvl.dat109.yatzy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Poengtabell {

	private HashMap<PoengType, Integer> poeng;
	private boolean erYatzyRegistrert;
	private int rundeNr;

	public Poengtabell() {
		poeng = new HashMap<PoengType, Integer>();
		this.setRundeNr(0);
	}

	/**
	 * Registrer et resultat for en runde
	 * 
	 * @param type  Kombinasjonstype som det skal registres poeng for
	 * @param resultat resultatverdien for runden
	 */
	public void registrerPoeng(PoengType type, int resultat) {
		poeng.put(type, resultat);
		this.setRundeNr(this.getRundeNr() + 1);
	}

	public int getPoeng(PoengType type) {
		return poeng.get(type);
	}

	public HashMap<PoengType, Integer> getAllePoeng() {
		return poeng;
	}

	public boolean getErYatzyRegistrert() {
		return erYatzyRegistrert;
	}

	public void setErYatzyRegistrert(boolean yatzyRegistrert) {
		this.erYatzyRegistrert = yatzyRegistrert;
	}

	public int getSum() {
		return this.poeng.values().stream().reduce(0, (acc, x) -> x + acc);
	}

	public int getRundeNr() {
		return rundeNr;
	}

	public void setRundeNr(int rundeNr) {
		this.rundeNr = rundeNr;
	}


	
}
