package no.hvl.dat109.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import no.hvl.dat109.entity.Spiller;
import no.hvl.dat109.repo.SpillerRepo;
import no.hvl.dat109.util.LoginUtil;
import no.hvl.dat109.util.PassordUtil;

@Service
public class SpillerService {
	@Autowired
	SpillerRepo spillerRepo;
	
	public Optional<Spiller> hentSpillerEtterBrukernavn(String brukernavn) {
		return spillerRepo.findById(brukernavn);
	}
	
	public Spiller hentInnloggetSpiller(HttpSession httpSession) {
		return (Spiller) httpSession.getAttribute("spiller");
	}
	
	public void logginnSpiller(HttpServletRequest httpServletRequest, Spiller spiller) {
		LoginUtil.loggInnBruker(httpServletRequest, spiller);
	}
	
	public boolean erKorrektPassord(String passord, String salt, String hash) {
		return PassordUtil.validerMedSalt(passord, salt, hash);
	}
	
	public void loggUtSpiller(HttpSession httpSession) {
		LoginUtil.loggUtBruker(httpSession);
	}
	
	public boolean erSpillerInnlogget(HttpSession httpSession) {
		return LoginUtil.erBrukerInnlogget(httpSession);
	}
}
