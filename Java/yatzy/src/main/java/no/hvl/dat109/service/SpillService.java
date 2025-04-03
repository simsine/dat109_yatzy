package no.hvl.dat109.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.hvl.dat109.entity.Poengtabell;
import no.hvl.dat109.entity.PoengtabellId;
import no.hvl.dat109.entity.Spill;
import no.hvl.dat109.entity.Spiller;
import no.hvl.dat109.repo.PoengtabellRepo;
import no.hvl.dat109.repo.SpillRepo;
import no.hvl.dat109.repo.SpillerRepo;
import no.hvl.dat109.util.PoengUtil;
import no.hvl.dat109.yatzy.Kopp;
import no.hvl.dat109.yatzy.PoengType;

/**
 * 
 */
@Service
public class SpillService {

	@Autowired
	Kopp kopp;

	@Autowired
	PoengtabellRepo poengtabellRepo;

	@Autowired
	SpillRepo spillRepo;

	@Autowired
	SpillerRepo spillerRepo;

	/**
	 * Opprettter ett nytt tomt spill
	 * 
	 * @return
	 */
	public Spill opprettNyttSpill(String brukernavn) {
		Optional<Spiller> spiller = spillerRepo.findById(brukernavn);

		Spill nyttSpill = new Spill();
		nyttSpill.setTidopprettet(LocalDateTime.now());
		nyttSpill.setOppretter(spiller.get());
		nyttSpill = spillRepo.save(nyttSpill);

		System.out.println("Lagrer nytt spill");

		Poengtabell poengtabell = new Poengtabell();
		System.out.println("Oppretter poengtabell");

		poengtabell.setPoengtabellId(new PoengtabellId(brukernavn, nyttSpill.getSpillnr()));
		System.out.println("Setter id");

		poengtabell = poengtabellRepo.save(poengtabell);

		System.out.println("Lagrer poengtabell");

		System.out.println("Spill: " + nyttSpill.getSpillnr());

		nyttSpill.setPoengtabeller(Arrays.asList(poengtabell));

		return nyttSpill;
	}

	/**
	 * Henter ett eksisterende spill
	 * 
	 * @return Optional spill
	 */
	public Optional<Spill> hentSpillEtterNr(Integer nr) {
		return spillRepo.findById(nr);
	}

	public List<Spill> hentAlleSpill() {
		return spillRepo.findAll();
	}

	public boolean erSpillFullt(Spill spill) {
		return antallSpillereISpill(spill) >= 6;
	}
	
	public int antallSpillereISpill(Spill spill) {
		return spill.getAntallSpillere();
	}

	/**
	 * Starter et nytt yatzy spill fra contolleren
	 */
	public void startSpill() {
		// TODO
	}

	/**
	 * Metode for å spille et trekk hvor man har oppgitt valgte terninger
	 * 
	 * @param beholdte terninger
	 * @return beholdte pluss trillede terninger
	 */
	public List<Integer> spillTrekk(List<Integer> valgteTerninger) {
		return kopp.trillResten(valgteTerninger);
	}

	public List<Integer> spillTrekkString(List<String> valgteTerninger) {
		if (valgteTerninger == null)
			return spillTrekk();
		List<Integer> omgjortliste = valgteTerninger.stream().map(Integer::parseInt).toList();
		return kopp.trillResten(omgjortliste);
	}

	public List<Integer> spillTrekk() {
		return kopp.trillResten();
	}

	/**
	 * @param brukernavn
	 * @param spillNr
	 * @param terningerString
	 * @return false om ferdig fylt poeng
	 */
	public boolean registrerPoeng(String brukernavn, Integer spillNr, List<String> terningerString) {

		List<Integer> terninger = terningerString.stream().map(Integer::parseInt).toList();

		Poengtabell poengTabell = poengtabellRepo.findByBrukernavnAndSpillnr(brukernavn, spillNr);

		PoengType type = poengTabell.finnForsteIkkeRegistrerteType();
		if (type == null)
			return false;

		if (terninger.size() > Kopp.ANTALL_TERNINGER) {
			throw new IllegalArgumentException("Kan ikke registrere mer enn " + Kopp.ANTALL_TERNINGER + "terninger.");
		}

		int poeng = 0;
		boolean erYatzy = PoengUtil.erYatzy(terninger);
		if (erYatzy && !poengTabell.getErYatzyRegistrert()) {
			if (!poengTabell.allePoengRegistrert()) {
				poengTabell.setHarTidligYatzy(true);
			}
			poeng = PoengUtil.yatzy(terninger);
			poengTabell.registrerPoeng(PoengType.YATZY, poeng);
			return true;
		}

		switch (type) {
		case ENERE:
			poeng = PoengUtil.enere(terninger);
			break;
		case TOERE:
			poeng = PoengUtil.toere(terninger);
			break;
		case TREERE:
			poeng = PoengUtil.treere(terninger);
			break;
		case FIRERE:
			poeng = PoengUtil.firere(terninger);
			break;
		case FEMERE:
			poeng = PoengUtil.femmere(terninger);
			break;
		case SEKSERE:
			poeng = PoengUtil.seksere(terninger);
			break;
		case ETT_PAR:
			poeng = PoengUtil.ettPar(terninger);
			break;
		case TO_PAR:
			poeng = PoengUtil.ToPar(terninger);
			break;
		case TRE_LIKE:
			poeng = PoengUtil.treLike(terninger);
			break;
		case FIRE_LIKE:
			poeng = PoengUtil.fireLike(terninger);
			break;
		case LITEN_STRAIGHT:
			poeng = PoengUtil.litenStraight(terninger);
			break;
		case STOR_STRAIGHT:
			poeng = PoengUtil.storStraight(terninger);
			break;
		case HUS:
			poeng = PoengUtil.hus(terninger);
			break;
		case SJANSE:
			poeng = PoengUtil.sjanse(terninger);
			break;
		case YATZY:
			poeng = PoengUtil.yatzy(terninger);
			break;
		default:
			System.out.println("Shit's fucked");
			break;
		}
		poengTabell.registrerPoeng(type, poeng);
		poengtabellRepo.save(poengTabell);
		return true;
	}

	public PoengType finnPoengType(String brukernavn, int spillNr) {
		Poengtabell poengTabell = poengtabellRepo.findByBrukernavnAndSpillnr(brukernavn, spillNr);

		return poengTabell.finnForsteIkkeRegistrerteType();
	}

	public Poengtabell hentPoengtabellEtterSpillnrOgBrukernavn(Integer spillnr, String brukernavn) {
		return poengtabellRepo.findByBrukernavnAndSpillnr(brukernavn, spillnr);
	}

	public void leggtilSpiller(String brukernavn, Integer spillId) {
		
		Spill spill = spillRepo.findById(spillId).get();
		Poengtabell poengtabell = new Poengtabell();
		poengtabell.setPoengtabellId(new PoengtabellId(brukernavn, spill.getSpillnr()));
		poengtabell = poengtabellRepo.save(poengtabell);
		spill.getPoengtabeller().add(poengtabell);
		spillRepo.save(spill);
	}

	public List<Poengtabell> hentPoengtabellerEtterSpillnr(Integer spillnr) {
		return poengtabellRepo.findBySpillnr(spillnr);
	}
	
	public boolean finnesPoengtabell(Integer spillId, String brukernavn) {
		return poengtabellRepo.findByBrukernavnAndSpillnr(brukernavn, spillId) != null;
	}


}
