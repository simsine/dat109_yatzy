package no.hvl.dat109.entity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import no.hvl.dat109.util.PoengConverter;
import no.hvl.dat109.yatzy.PoengType;

@Entity
@Table(schema = "yatzy")
public class Poengtabell {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "spillnr", referencedColumnName = "spillnr", insertable = false, updatable = false)
	private Spill spill;

	@EmbeddedId
	private PoengtabellId poengtabellId;

	public PoengtabellId getPoengtabellId() {
		return poengtabellId;
	}

	public void setPoengtabellId(PoengtabellId poengtabellId) {
		this.poengtabellId = poengtabellId;
	}

	@JdbcTypeCode(SqlTypes.JSON)
	@Column(columnDefinition = "jsonb")
	@Convert(converter = PoengConverter.class)
	private Map<PoengType, Integer> poeng = new HashMap<>();

	public Map<PoengType, Integer> getPoeng() {
		return poeng;
	}

	public void setPoeng(Map<PoengType, Integer> poeng) {
		this.poeng = poeng;
	}

	@Transient
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
		return this.poeng.values().stream().filter(t -> t != -1).reduce(0, Integer::sum) + getBonus();
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

	public Spill getSpill() {
		return spill;
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
	public Optional<PoengType> finnForsteIkkeRegistrerteType() {

		Map<PoengType, Integer> sortedMap = this.getAllePoeng().entrySet().stream().sorted(Map.Entry.comparingByKey())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
						LinkedHashMap::new));

		Optional<Entry<PoengType, Integer>> type = sortedMap.entrySet().stream().filter(t -> t.getValue().equals(-1))
				.findFirst();

		if (type.isEmpty())
			return Optional.empty();

		PoengType poengType = type.get().getKey();

		if (poengType.equals(PoengType.YATZY) && this.getErYatzyRegistrert()) {
			return Optional.empty();
		}

		return Optional.of(poengType);
	}

	public int getBonus() {

		int sum = poeng.entrySet().stream()
				.filter(e -> e.getKey().compareTo(PoengType.ETT_PAR) < 0 && e.getValue() != -1)
				.mapToInt(Map.Entry::getValue).sum();
		return sum >= 42? 50 : 0;
	}

	public int getSumOppTilOgMedSeksere() {
		int sum = poeng.entrySet().stream()
				.filter(e -> e.getKey().compareTo(PoengType.ETT_PAR) < 0 && e.getValue() != -1)
				.mapToInt(Map.Entry::getValue).sum();
		return sum;
	}

}
