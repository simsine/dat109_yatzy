package no.hvl.dat109.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import no.hvl.dat109.entity.Spiller;
import no.hvl.dat109.form.RegistreringForm;
import no.hvl.dat109.repo.SpillerRepo;
import no.hvl.dat109.util.PassordUtil;

@Service
public class RegistreringService {
	
	@Autowired
	SpillerRepo spillerRepo;
	
    public void validerUniktBrukernavn(BindingResult bindingResult, String brukernavn) {
        if (spillerRepo.existsById(brukernavn)) bindingResult.addError(new FieldError("Spiller", "brukernavn", "Brukernavn er allerede tatt"));
    }
    
    public void validerLiktPassord(BindingResult bindingResult, String passord, String passord_re) {
        if (!passord.equals(passord_re)) bindingResult.addError(new FieldError("Spiller", "passord", "Passord feltene må være like"));
    }
    
    public Spiller registrerNySpiller(RegistreringForm registreringForm) {
    	String salt = PassordUtil.genererTilfeldigSalt();
    	String hash = PassordUtil.hashMedSalt(registreringForm.getPassord(), salt);
    	
    	Spiller nySpiller = new Spiller(
    		registreringForm.getBrukernavn(),
    		registreringForm.getEmail(),
    		registreringForm.getFornavn(),
    		registreringForm.getEtternavn(),
    		hash,
    		salt
		);
    	
    	// Vi persisterer den nye spilleren
    	spillerRepo.save(nySpiller);
    	return nySpiller;
    }
}
