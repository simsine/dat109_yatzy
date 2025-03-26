package no.hvl.dat109.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import no.hvl.dat109.util.PoengConverter;
import no.hvl.dat109.yatzy.PoengType;

@Entity
public class Poengtabell {

	@EmbeddedId
	private PoengtabellId poengtabellId;

	public PoengtabellId getNøkkel() {
		return poengtabellId;
	}

	public void setNøkkel(PoengtabellId nøkkel) {
		this.poengtabellId = nøkkel;
	}

	@Column(columnDefinition = "jsonb")
	@Convert(converter = PoengConverter.class)
	private Map<PoengType, Integer> poeng = new HashMap<>();

	private boolean harTidligYatzy;

	public Poengtabell() {
		poeng = new HashMap<PoengType, Integer>();
		for (PoengType pt : PoengType.values())
			poeng.put(pt, -1);
		setHarTidligYatzy(false);
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

	public Map<PoengType, Integer> getAllePoeng() {
		return poeng;
	}

	public boolean getErYatzyRegistrert() {
		return this.poeng.get(PoengType.YATZY) != -1;
	}

	public int getSum() {
		return this.poeng.values().stream().filter(t -> t != -1).reduce(0, Integer::sum);
	}

	public boolean isHarTidligYatzy() {
		return harTidligYatzy;
	}

	public void setHarTidligYatzy(boolean harTidligYatzy) {
		this.harTidligYatzy = harTidligYatzy;
	}

	public boolean allePoengRegistrert() {
		return !poeng.entrySet().stream().anyMatch(t -> t.getValue() == -1);
	}

	@Override
	public String toString() {

		String ret = "";

		for (Entry<PoengType, Integer> set : this.poeng.entrySet()) {
			ret += set.toString();
		}

		return ret;
	}

	/**
	 * Finner forste ikke registrerte poengtypen til spiller basert på poengtabellen
	 * Returnerer null hvis alle er registrert
	 * 
	 * Hvis typen er yatzy, men yatzy allerede er registrert returneres null
	 * 
	 * @return PoengType
	 */
	public PoengType finnForsteIkkeRegistrerteType() {
		Entry<PoengType, Integer> type = this.getAllePoeng().entrySet().stream().sorted()
				.filter(t -> t.getValue().equals(-1)).findFirst().orElse(null);
		if (type == null)
			return null;
		PoengType poengType = type.getKey();
		if (poengType.equals(PoengType.YATZY) && this.getErYatzyRegistrert()) {
			return null;
		}	
		return type.getKey();
	}

}
