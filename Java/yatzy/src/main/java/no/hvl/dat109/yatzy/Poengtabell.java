package no.hvl.dat109.yatzy;

import java.util.HashMap;

public class Poengtabell {

	private HashMap<PoengType, Integer> poeng;
	private boolean erYatzyRegistrert;

	public Poengtabell() {
		poeng = new HashMap<PoengType, Integer>();
	}

	/**
	 * Registrer et resultat for en runde
	 * 
	 * @param type     Kombinasjonstype som det skal registres poeng for
	 * @param resultat resultatverdien for runden
	 */
	public void registrerPoeng(PoengType type, int resultat) {
		poeng.put(type, resultat);
	}

	public int getPoeng(PoengType type) {
		return poeng.getOrDefault(type, 0);
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
		return this.poeng.values().stream().reduce(0, Integer::sum);
	}

	public boolean harTidligYatzy() {
		
		boolean ikkeRegistrertAllePoeng = this.poeng.entrySet().stream().anyMatch(t -> t.getValue() == null);
		
		return erYatzyRegistrert && ikkeRegistrertAllePoeng;
	}
}
