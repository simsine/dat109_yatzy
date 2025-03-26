package no.hvl.dat109.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.hvl.dat109.entity.Poengtabell;
import no.hvl.dat109.entity.PoengtabellId;
import no.hvl.dat109.entity.Spill;
import no.hvl.dat109.repo.PoengtabellRepo;
import no.hvl.dat109.repo.SpillRepo;
import no.hvl.dat109.util.PoengUtil;
import no.hvl.dat109.yatzy.Kopp;
import no.hvl.dat109.yatzy.PoengType;

@Service
public class SpillService {
	
	@Autowired Kopp kopp;
	
	@Autowired PoengtabellRepo poengtabellRepo;
	
	@Autowired SpillRepo spillRepo;
	
	/**
	 * Opprettter ett nytt tomt spill
	 * 
	 * @return
	 */
	public Spill opprettNyttSpill(String brukernavn) {
		Spill nyttSpill = new Spill();
		nyttSpill = spillRepo.save(nyttSpill);
		
		System.out.println("Lagrer nytt spill");
		
		Poengtabell poengtabell = new Poengtabell();
		System.out.println("Oppretter poengtabell");
		
		poengtabell.setNøkkel(new PoengtabellId("XFaze", nyttSpill.getSpillnr()));
		System.out.println("Setter id");

		poengtabell = poengtabellRepo.save(poengtabell);
		
		
		System.out.println("Lagrer poengtabell");
		

		
		
		System.out.println("Spill: " + nyttSpill.getSpillnr());
		
		
		nyttSpill.setPoengtabeller(Arrays.asList(poengtabell));
		
		return nyttSpill;
	}
	
	/**
	 * Henter ett eksisterende spill
	 * @return Optional spill
	 */
	public Optional<Spill> hentSpillEtterNr(Integer nr ) {
		// TODO
		return null;
	}
	
	public List<Spill> hentAlleSpill() {
		return spillRepo.findAll();
	}
	
	public boolean erSpillFullt(Spill spill) {
		return false;
	}
	
	/**
	 * Starter et nytt yatzy spill fra contolleren
	 */
	public void startSpill() {
		//TODO
	}

	/**
	 * Metode for å spille et trekk hvor man har oppgitt valgte terninger
	 * @param beholdte terninger
	 * @return beholdte pluss trillede terninger
	 */
	public List<Integer> spillTrekk(List<Integer> valgteTerninger) {
		return kopp.trillResten(valgteTerninger);
	}

	/**
	 * Metode for å registrere poeng for endelig kast
	 * 
	 * @param poengTabell - midlertidig til vi får database
	 * @param type
	 * @param terninger
	 */
	public int registrerPoeng(String brukernavn, int spillNr, List<Integer> terninger) {
		
		Poengtabell poengTabell = poengtabellRepo.findByBrukernavnAndSpillnr(brukernavn, spillNr);
		
		PoengType type = poengTabell.finnForsteIkkeRegistrerteType();
		
		
		
		if (terninger.size() < Kopp.ANTALL_TERNINGER) {
			throw new IllegalArgumentException("Kan ikke registrere mer enn " + Kopp.ANTALL_TERNINGER + "terninger.");			
		}
		
		int poeng = 0;
		boolean erYatzy = PoengUtil.erYatzy(terninger);
		if (erYatzy && !poengTabell.getErYatzyRegistrert()) {
			if(!poengTabell.allePoengRegistrert()) {
				poengTabell.setHarTidligYatzy(true);
			}
			poeng = PoengUtil.yatzy(terninger);
			poengTabell.registrerPoeng(PoengType.YATZY, poeng);
			return poeng;
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
		return poeng;
	}
	
	public PoengType finnPoengType(String brukernavn, int spillNr) {
		Poengtabell poengTabell = poengtabellRepo.findByBrukernavnAndSpillnr(brukernavn, spillNr);
		
		return poengTabell.finnForsteIkkeRegistrerteType();
	}
	
}
