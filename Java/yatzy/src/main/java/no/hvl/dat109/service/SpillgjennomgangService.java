package no.hvl.dat109.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import no.hvl.dat109.entiteter.Poengtabell;
import no.hvl.dat109.repo.PoengtabellRepo;

@Service
public class SpillgjennomgangService {
	
	@Autowired 
	PoengtabellRepo poengtabellRepo;
	
	public List<Poengtabell> hentAlleTidligereSpillForSpiller(String brukernavn) {
		List<Poengtabell> poengtabellListe = poengtabellRepo.findByBrukernavn(brukernavn);
		return poengtabellListe;
	}
	
	public List<Poengtabell> hentPoengtabellerForEtSpill(int spillNr) {
		List<Poengtabell> poengtabellListe = poengtabellRepo.findBySpillnr(spillNr);
		return poengtabellListe;
	}
	
	
}
