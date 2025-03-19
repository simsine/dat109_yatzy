package no.hvl.dat109.yatzy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

/**
 * Klasse som representerer en kopp til å trille terningene med.
 */
@Service
public class Kopp {
	public static final int ANTALL_TERNINGER = 5;
	public static final int MAX_TERNINGVERDI = 6;

	private List<Integer> terningverdier;
	private Random random;

	public Kopp() {
		this.terningverdier = new ArrayList<>();
		this.random = new Random();
	}

	/**
	 * Henter terningverdier.
	 * 
	 * @return en liste av terningverdier.
	 */
	public List<Integer> getTerningverdier() {
		return terningverdier;
	}

	/**
	 * Setter terningverdier.
	 * 
	 * @param terningverdier
	 */
	public void setTerningverdier(List<Integer> terningverdier) {
		if (terningverdier.size() != ANTALL_TERNINGER) {
			throw new IllegalArgumentException("Koppen må ha eksakt " + ANTALL_TERNINGER + "terningverdier.");
		}
		this.terningverdier = new ArrayList<>(terningverdier);
	}

	/**
	 * Beholder de valgte terningene og triller resten av de
	 * {@value no.hvl.dat109.yatzy.Kopp#ANTALL_TERNINGER} terningene på nytt
	 * 
	 * @param beholdteTerninger En liste av terningverdier som skal beholdes
	 * @return En liste av {@value no.hvl.dat109.yatzy.Kopp#ANTALL_TERNINGER}
	 *         terningverdier inkludert de beholdte terningene
	 */
	public List<Integer> trillResten(List<Integer> beholdteTerninger) {
		terningverdier = new ArrayList<>(beholdteTerninger);

		for (int i = beholdteTerninger.size(); i < ANTALL_TERNINGER; i++) {
			terningverdier.add(random.nextInt(MAX_TERNINGVERDI) + 1);
		}

		return terningverdier;
	}

	/**
	 * Triller {@value no.hvl.dat109.yatzy.Kopp#ANTALL_TERNINGER} terninger.
	 * 
	 * @return En liste av {@value no.hvl.dat109.yatzy.Kopp#ANTALL_TERNINGER}
	 *         terningverdier.
	 */
	public List<Integer> trillResten() {
		return trillResten(List.of());
	}

}
