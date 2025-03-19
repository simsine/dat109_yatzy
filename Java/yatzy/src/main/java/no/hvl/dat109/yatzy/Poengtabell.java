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
	 * @param rundeNr nummer på runde
	 * @param resultat resultatverdien for runden
	 */
	public void registrerPoeng(PoengType type, int resultat) { 
		poeng.put(type, resultat);
	}

	public int getPoeng(PoengType type) {
		return poeng.get(type);
	}
	
	public boolean getErYatzyRegistrert() {
		return erYatzyRegistrert;
	}
	
	public void setErYatzyRegistrert(boolean yatzyRegistrert) {
		this.erYatzyRegistrert = yatzyRegistrert;
	}
}
