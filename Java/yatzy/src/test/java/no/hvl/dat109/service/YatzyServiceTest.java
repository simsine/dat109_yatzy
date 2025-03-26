package no.hvl.dat109.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import no.hvl.dat109.entity.Poengtabell;
import no.hvl.dat109.yatzy.Kopp;
import no.hvl.dat109.yatzy.PoengType;

@ExtendWith(MockitoExtension.class)
class YatzyServiceTest {

	@Mock
	private Kopp kopp;

	@InjectMocks
	private SpillService yatzyService;

	private Poengtabell poengtabell;

	@BeforeEach
	void setUp() {
		poengtabell = new Poengtabell();
	}

	@Test
	void testRegistrerPoengTidligYatzy() {
		List<Integer> terninger = List.of(1, 1, 1, 1, 1);
		PoengType typeEnere = PoengType.ENERE;
		PoengType typeYatzy = PoengType.YATZY;

		int poeng = yatzyService.registrerPoeng(poengtabell, typeEnere, terninger);

		assertEquals(50, poeng);
		assertEquals(50, poengtabell.getPoeng(typeYatzy));
		assertEquals(0, poengtabell.getPoeng(typeEnere));
	}

	@Test
	void testRegistrerPoengTypeYatzy() {
		List<Integer> terninger = List.of(1, 1, 1, 1, 1);
		PoengType typeYatzy = PoengType.YATZY;

		int poeng = yatzyService.registrerPoeng(poengtabell, typeYatzy, terninger);

		assertEquals(50, poeng);
		assertEquals(50, poengtabell.getPoeng(typeYatzy));
	}

	@Test
	void testGetNesteTypeVanlig() {
		List<Integer> terninger = List.of(1, 2, 3, 4, 5);
		PoengType typeEnere = PoengType.ENERE;

		int poeng = yatzyService.registrerPoeng(poengtabell, typeEnere, terninger);

		PoengType nesteType = yatzyService.getNesteType(typeEnere, poengtabell);

		assertEquals(1, poeng);
		assertEquals(PoengType.TOERE, nesteType);

		terninger = List.of(1, 2, 3, 4, 5);
		PoengType typeToere = PoengType.TOERE;

		poeng = yatzyService.registrerPoeng(poengtabell, typeToere, terninger);

		nesteType = yatzyService.getNesteType(typeToere, poengtabell);

		assertEquals(2, poeng);
		assertEquals(PoengType.TREERE, nesteType);

	}

	@Test
	void testGetNesteTypeTidligYatzy() {
		List<Integer> terninger = List.of(1, 1, 1, 1, 1);
		PoengType typeEnere = PoengType.ENERE;

		int poeng = yatzyService.registrerPoeng(poengtabell, typeEnere, terninger);

		PoengType nesteType = yatzyService.getNesteType(typeEnere, poengtabell);

		assertEquals(PoengType.ENERE, nesteType);
		assertNotEquals(PoengType.TOERE, nesteType);

	}

	@Test
	void testGetNesteTypeTidligYatzySåYatzyIgjen() {
		List<Integer> terninger = List.of(1, 1, 1, 1, 1);
		PoengType typeEnere = PoengType.ENERE;

		int enerpoeng = yatzyService.registrerPoeng(poengtabell, typeEnere, terninger);

		assertTrue(poengtabell.getErYatzyRegistrert());

		PoengType nesteType = yatzyService.getNesteType(typeEnere, poengtabell);

		assertEquals(PoengType.ENERE, nesteType);
		assertNotEquals(PoengType.TOERE, nesteType);
		assertEquals(50, poengtabell.getPoeng(PoengType.YATZY));
		assertEquals(0, poengtabell.getPoeng(PoengType.ENERE));

		terninger = List.of(1, 1, 1, 1, 1);
		enerpoeng = yatzyService.registrerPoeng(poengtabell, typeEnere, terninger);

		assertTrue(poengtabell.getErYatzyRegistrert());

		nesteType = yatzyService.getNesteType(typeEnere, poengtabell);

		assertEquals(PoengType.TOERE, nesteType);
		assertNotEquals(PoengType.ENERE, nesteType);
		assertEquals(5 * 1, enerpoeng);
		assertEquals(50, poengtabell.getPoeng(PoengType.YATZY));

	}

	@Test
	void testGetNesteTypeTidligYatzyNesteTypeYatzy() {
		List<Integer> terninger = List.of(1, 1, 1, 1, 1);
		PoengType typeSjanse = PoengType.SJANSE;

		int poeng = yatzyService.registrerPoeng(poengtabell, typeSjanse, terninger);

		PoengType nesteType = yatzyService.getNesteType(typeSjanse, poengtabell);

		assertEquals(null, nesteType);
		assertNotEquals(PoengType.YATZY, nesteType);
	}

	@Test
	void testGetNesteTypeNesteTypeFerdig() {
		List<Integer> terninger = List.of(1, 1, 1, 1, 1);
		PoengType typeYatzy = PoengType.YATZY;

		int poeng = yatzyService.registrerPoeng(poengtabell, typeYatzy, terninger);

		PoengType nesteType = yatzyService.getNesteType(typeYatzy, poengtabell);

		assertEquals(null, nesteType);
		assertNotEquals(PoengType.YATZY, nesteType);
	}

	@Test
	void testRegistrerPoengIkkeYatzyHverGang() {
		List<Integer> terninger = List.of(1, 2, 3, 4, 5);

		int poeng = yatzyService.registrerPoeng(poengtabell, PoengType.ENERE, terninger);

		PoengType nesteType = yatzyService.getNesteType(PoengType.ENERE, poengtabell);

		assertEquals(PoengType.TOERE, nesteType);
		
		assertEquals(1, poeng);
		assertEquals(1, poengtabell.getPoeng(PoengType.ENERE));
		
		terninger = List.of(1, 2, 3, 4, 5);
		poeng = yatzyService.registrerPoeng(poengtabell, PoengType.TOERE, terninger);

		nesteType = yatzyService.getNesteType(PoengType.TOERE, poengtabell);

		assertEquals(PoengType.TREERE, nesteType);
		assertEquals(2, poeng);
		assertEquals(2, poengtabell.getPoeng(PoengType.TOERE));
		
		terninger = List.of(1, 2, 3, 4, 5);
		poeng = yatzyService.registrerPoeng(poengtabell, PoengType.TREERE, terninger);
		
		nesteType = yatzyService.getNesteType(PoengType.TREERE, poengtabell);
		
		assertEquals(PoengType.FIRERE, nesteType);
		assertEquals(3, poeng);
		assertEquals(3, poengtabell.getPoeng(PoengType.TREERE));
	}

}
