package no.hvl.dat109.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import no.hvl.dat109.entity.Poengtabell;
import no.hvl.dat109.repo.PoengtabellRepo;
import no.hvl.dat109.yatzy.Kopp;
import no.hvl.dat109.yatzy.PoengType;

@ExtendWith(MockitoExtension.class)
class SpillServiceTest {

	@Mock
	private Kopp kopp;

	@Mock
	private PoengtabellRepo poengtabellRepo;

	@InjectMocks
	private SpillService spillService;

	@Test
	void testRegistrerPoengVanlig() {

		Poengtabell poengtabell = new Poengtabell();

		when(poengtabellRepo.findByBrukernavnAndSpillnr("lol", 1)).thenReturn(poengtabell);

		List<Integer> terninger = new ArrayList<>(List.of(1, 2, 3, 4, 5));

		assertEquals(PoengType.ENERE, poengtabell.finnForsteIkkeRegistrerteType());

		spillService.registrerPoeng("lol", 1, terninger);

		assertEquals(1, poengtabell.getPoeng(PoengType.ENERE));
		
		assertEquals(PoengType.TOERE, poengtabell.finnForsteIkkeRegistrerteType());

		terninger = new ArrayList<>(List.of(1, 2, 3, 4, 5));

		spillService.registrerPoeng("lol", 1, terninger);

		assertEquals(2, poengtabell.getPoeng(PoengType.TOERE));
		
		assertEquals(PoengType.TREERE, poengtabell.finnForsteIkkeRegistrerteType());

	}
	@Test
	void testRegistrerPoengTidligYatzy() {
		
		Poengtabell poengtabell = new Poengtabell();
		
		when(poengtabellRepo.findByBrukernavnAndSpillnr("lol", 1)).thenReturn(poengtabell);
		
		List<Integer> terninger = new ArrayList<>(List.of(1, 1, 1, 1, 1));
		
		assertEquals(PoengType.ENERE, poengtabell.finnForsteIkkeRegistrerteType());
		
		spillService.registrerPoeng("lol", 1, terninger);
		
		assertEquals(-1, poengtabell.getPoeng(PoengType.ENERE));
		assertEquals(50, poengtabell.getPoeng(PoengType.YATZY));
		
		assertEquals(PoengType.ENERE, poengtabell.finnForsteIkkeRegistrerteType());
		
		terninger = new ArrayList<>(List.of(1, 1, 1, 1, 1));
		
		spillService.registrerPoeng("lol", 1, terninger);
		
		assertEquals(5, poengtabell.getPoeng(PoengType.ENERE));
		
		assertEquals(PoengType.TOERE, poengtabell.finnForsteIkkeRegistrerteType());
		
		
		
	}


}
