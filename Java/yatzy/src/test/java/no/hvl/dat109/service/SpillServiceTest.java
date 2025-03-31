package no.hvl.dat109.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import no.hvl.dat109.entity.Poengtabell;
import no.hvl.dat109.entity.PoengtabellId;
import no.hvl.dat109.entity.Spill;
import no.hvl.dat109.repo.PoengtabellRepo;
import no.hvl.dat109.repo.SpillRepo;
import no.hvl.dat109.yatzy.Kopp;
import no.hvl.dat109.yatzy.PoengType;

@ExtendWith(MockitoExtension.class)
class SpillServiceTest {

	@Mock
	private Kopp kopp;

	@Mock
	private PoengtabellRepo poengtabellRepo;

	@Mock
	private SpillRepo spillRepo;

	@InjectMocks
	private SpillService spillService;

	@Test
	void testRegistrerPoengVanlig() {

		Poengtabell poengtabell = new Poengtabell();

		when(poengtabellRepo.findByBrukernavnAndSpillnr("lol", 1)).thenReturn(poengtabell);

		List<String> terninger = new ArrayList<>(List.of("1", "2", "3", "4", "5"));

		assertEquals(PoengType.ENERE, poengtabell.finnForsteIkkeRegistrerteType());

		spillService.registrerPoeng("lol", 1, terninger);

		assertEquals(1, poengtabell.getPoeng(PoengType.ENERE));
		
		assertEquals(PoengType.TOERE, poengtabell.finnForsteIkkeRegistrerteType());

		terninger = new ArrayList<>(List.of("1", "2", "3", "4", "5"));

		spillService.registrerPoeng("lol", 1, terninger);

		assertEquals(2, poengtabell.getPoeng(PoengType.TOERE));
		
		assertEquals(PoengType.TREERE, poengtabell.finnForsteIkkeRegistrerteType());

	}
	@Test
	void testRegistrerPoengTidligYatzy() {
		
		Poengtabell poengtabell = new Poengtabell();
		
		when(poengtabellRepo.findByBrukernavnAndSpillnr("lol", 1)).thenReturn(poengtabell);
		
		List<String> terninger = new ArrayList<>(List.of("1", "1", "1", "1", "1"));
		
		assertEquals(PoengType.ENERE, poengtabell.finnForsteIkkeRegistrerteType());
		
		spillService.registrerPoeng("lol", 1, terninger);
		
		assertEquals(-1, poengtabell.getPoeng(PoengType.ENERE));
		assertEquals(50, poengtabell.getPoeng(PoengType.YATZY));
		
		assertEquals(PoengType.ENERE, poengtabell.finnForsteIkkeRegistrerteType());
		
		terninger = new ArrayList<>(List.of("1", "1", "1", "1", "1"));
		
		spillService.registrerPoeng("lol", 1, terninger);
		
		assertEquals(5, poengtabell.getPoeng(PoengType.ENERE));
		
		assertEquals(PoengType.TOERE, poengtabell.finnForsteIkkeRegistrerteType());
	}

	@Test
		void testHentAlleSpill() {
			List<Spill> spillListe = List.of(new Spill(), new Spill());
			when(spillRepo.findAll()).thenReturn(spillListe);

			List<Spill> result = spillService.hentAlleSpill();

			assertEquals(2, result.size());
		}

	@Test
	void testHentSpillEtterNr() {
		Spill spill = new Spill();

		spill.setSpillnr(5);
		when(spillRepo.findById(5)).thenReturn(Optional.of(spill));

		Optional<Spill> result = spillService.hentSpillEtterNr(5);

		assertTrue(result.isPresent());
		assertEquals(5, result.get().getSpillnr());
	}

	@Test
	void testHentPoengTabellEtterSpillnr() {
		List <Poengtabell> ptList = List.of(new Poengtabell(), new Poengtabell());
		when(poengtabellRepo.findBySpillnr(3)).thenReturn(ptList);

		List<Poengtabell> result = spillService.hentPoengtabellerEtterSpillnr(3);

		assertEquals(2, result.size());

	}

	@Test
	void testHentPoengTabellEtterBrukernavnOgSpillnr() {
		Poengtabell pt = new Poengtabell();
		PoengtabellId ptId = new PoengtabellId("tester", 10);
		pt.setPoengtabellId(ptId);

		when(poengtabellRepo.findByBrukernavnAndSpillnr("tester", 10)).thenReturn(pt);

		Poengtabell result = spillService.hentPoengtabellEtterSpillnrOgBrukernavn(10, "tester");

		assertNotNull(result);
		assertEquals("tester", result.getPoengtabellId().getBrukernavn());
		assertEquals(10, result.getPoengtabellId().getSpillNr());
	}
}
