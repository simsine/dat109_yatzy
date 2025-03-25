package no.hvl.dat109.entiteter;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import no.hvl.dat109.yatzy.PoengType;
@Entity
public class Poengtabell {

	@Id
	private SammensattNøkkel nøkkel;
	private boolean harTidligYatzy;
	
	@ElementCollection
    @MapKeyColumn(name="name")
    @Column(name="value")
    @CollectionTable(name="example_attributes", joinColumns=@JoinColumn(name="example_id"))
	private HashMap<PoengType, Integer> poeng;
	

	public Poengtabell() {
		poeng = new HashMap<PoengType, Integer>();
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

	public HashMap<PoengType, Integer> getAllePoeng() {
		return poeng;
	}

	public boolean getErYatzyRegistrert() {
		return this.poeng.containsKey(PoengType.YATZY);
	}


	public int getSum() {
		return this.poeng.values().stream().reduce(0, Integer::sum);
	}
	
	
	public boolean isHarTidligYatzy() {
		return harTidligYatzy;
	}
	
	public void setHarTidligYatzy(boolean harTidligYatzy) {
		this.harTidligYatzy = harTidligYatzy;
	}
	
	public boolean allePoengRegistrert() {
		return poeng.keySet().containsAll(Set.of(PoengType.values()));
	}


	
	@Override
	public String toString() {
		
		String ret = "";
		
		for (Entry<PoengType, Integer> set : this.poeng.entrySet()) {
			ret += set.toString();
		}
		
		return ret;
	}

}
