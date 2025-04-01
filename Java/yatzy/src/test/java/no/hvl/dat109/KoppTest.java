package no.hvl.dat109;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import no.hvl.dat109.yatzy.Kopp;

@TestInstance(Lifecycle.PER_CLASS)
public class KoppTest {

	private Kopp kopp;

	// Dynamiske terningverdier som skal brukes i testTrillResten
	private Stream<List<Integer>> genererForskjelligAntallBeholdteTerninger() {
		List<Integer> forsteTrill = kopp.trillResten();
		return Stream.of(List.of(), // Behold null terninger
				List.of(forsteTrill.get(0)), // Behold én
				List.of(forsteTrill.get(0), forsteTrill.get(1)), // Behold to
				List.of(forsteTrill.get(0), forsteTrill.get(1), forsteTrill.get(2)), // Behold tre
				List.of(forsteTrill.get(0), forsteTrill.get(1), forsteTrill.get(2), forsteTrill.get(3)), // Behold fire
				forsteTrill // Behold alle terningene
		);
	}

	@BeforeAll
	public void setup() {
		kopp = new Kopp();
	}

	@Test
	public void testKonstruktor() {
		kopp = new Kopp();
		assertTrue(kopp.getTerningverdier().isEmpty());
	}

	@Test
	public void testForsteTrill() {
		kopp.trillResten();

		// terningverdier skal alltid være 5 integers fra 1-6
		assertEquals(Kopp.ANTALL_TERNINGER, kopp.getTerningverdier().size());
		assertTrue(kopp.getTerningverdier().stream().allMatch(t -> t >= 1 && t <= 6));
	}

	@ParameterizedTest
	@MethodSource("genererForskjelligAntallBeholdteTerninger")
	public void testTrillResten(List<Integer> beholdteTerninger) {
		kopp.trillResten(beholdteTerninger);

		// Sjekker at terningene ble beholdt
		for (int terningverdi : beholdteTerninger) {
			assertTrue(kopp.getTerningverdier().contains(terningverdi));
		}

		// terningverdier skal alltid være 5 integers fra 1-6
		assertEquals(Kopp.ANTALL_TERNINGER, kopp.getTerningverdier().size());
		assertTrue(kopp.getTerningverdier().stream().allMatch(verdi -> verdi >= 1 && verdi <= 6));
	}

	@Test
	public void testGetTerningverdier() {
		List<Integer> trill = kopp.trillResten();
		assertEquals(trill, kopp.getTerningverdier());
	}

	@Test
	public void testSetTerningverdier() {
		List<Integer> gyldigListe = List.of(1, 1, 1, 1, 1);
		kopp.setTerningverdier(gyldigListe);
		assertEquals(gyldigListe, kopp.getTerningverdier());
	}

	@Test
	public void testSetTerningverdierMedFeilAntallTerninger() {
		assertThrows(IllegalArgumentException.class, () -> kopp.setTerningverdier(List.of()));
		assertThrows(IllegalArgumentException.class, () -> kopp.setTerningverdier(List.of(1)));
		assertThrows(IllegalArgumentException.class, () -> kopp.setTerningverdier(List.of(1, 2)));
		assertThrows(IllegalArgumentException.class, () -> kopp.setTerningverdier(List.of(1, 2, 3)));
		assertThrows(IllegalArgumentException.class, () -> kopp.setTerningverdier(List.of(1, 2, 3, 4)));
		assertThrows(IllegalArgumentException.class, () -> kopp.setTerningverdier(List.of(1, 2, 3, 4, 5, 6)));
	}

}
