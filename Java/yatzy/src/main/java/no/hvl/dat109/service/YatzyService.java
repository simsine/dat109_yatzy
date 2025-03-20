package no.hvl.dat109.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.hvl.dat109.util.PoengUtil;
import no.hvl.dat109.yatzy.Kopp;
import no.hvl.dat109.yatzy.PoengType;
import no.hvl.dat109.yatzy.Poengtabell;

@Service
public class YatzyService {
	
	@Autowired Kopp kopp;
	
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
	public int registrerPoeng(Poengtabell poengTabell, PoengType type, List<Integer> terninger) {
		if (terninger.size() < Kopp.ANTALL_TERNINGER) {
			throw new IllegalArgumentException("Kan ikke registrere mer enn " + Kopp.ANTALL_TERNINGER + "terninger.");			
		}
		
		int poeng = 0;
		boolean erYatzy = PoengUtil.erYatzy(terninger);
		if (erYatzy && !poengTabell.getErYatzyRegistrert()) {
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
		return poeng;
	}

	public PoengType getNesteType(PoengType typeNaa, Poengtabell poengtabell) {
		if (typeNaa.equals(PoengType.YATZY)) {
			return null;
		}
		List<PoengType> typeListe = new ArrayList<>(List.of(PoengType.values()));
		typeListe.sort(Comparator.naturalOrder());
		ListIterator<PoengType> it = typeListe.listIterator(typeListe.indexOf(typeNaa) + 1);
		PoengType nesteType = it.next();
		
		if (nesteType.equals(PoengType.YATZY) && poengtabell.getErYatzyRegistrert()) {
			return null;
		}
		
		if (poengtabell.harTidligYatzy()) {
			return typeNaa;
		}
		
		return nesteType;
	}
	
}
