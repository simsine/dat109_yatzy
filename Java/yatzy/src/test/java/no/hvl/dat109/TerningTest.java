package no.hvl.dat109;

/**
 * En JUnit-test klasse med følgende metoder.
 * @author Lasse Udjus
 * @author Johan Haavik
 * @author Simen Strømsnes
 * @author Bendik Saxstad
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TerningTest {

	@Test
	void testKast() {
		Terning terning = new Terning();
		for (int i = 0; i < 10000; i++) {
			int kastRes = terning.kast();
			assertTrue(kastRes >= 1 && kastRes <= 6);
		}
	}
}