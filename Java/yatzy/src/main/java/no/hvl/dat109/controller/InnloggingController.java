package no.hvl.dat109.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import no.hvl.dat109.entity.Spiller;
import no.hvl.dat109.form.InnloggingForm;
import no.hvl.dat109.service.SpillerService;
import no.hvl.dat109.util.LoginUtil;

@Controller
public class InnloggingController {
	
	@Autowired
	SpillerService spillerService;
	
	@GetMapping("/innlogging")
	public String getInnlogging(HttpSession httpSession) {
		if (LoginUtil.erBrukerInnlogget(httpSession)) return "redirect:/lobby";
		return "logginnView";
	}
	
	@PostMapping("/innlogging")
	public String postInnlogging(
		Model model,
		@Valid InnloggingForm innloggingForm, BindingResult bindingResult, 
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse
	) {
		if (bindingResult.hasErrors()) {
			// Det har oppstått en valideringsfeil, vi rendrer siden på nytt og viser alle feilmeldinger
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(e -> e.getDefaultMessage())
					.toList();
			model.addAttribute("errors", errors);
			httpServletResponse.setStatus(400);
			return "logginnView";
		}
		
		Optional<Spiller> optionSpiller = spillerService.hentSpillerEtterBrukernavn(innloggingForm.getBrukernavn());
		
        if (optionSpiller.isEmpty()) {
            model.addAttribute("errors", List.of("Brukernavn eller passord er feil"));
            httpServletResponse.setStatus(400);
            return "logginnView";
        }
        
        Spiller spiller = optionSpiller.get();
        
        if (!spillerService.erKorrektPassord(innloggingForm.getPassord(), spiller.getSalt(), spiller.getHashetpassord())) {
                model.addAttribute("errors", List.of("Brukernavn eller passord er feil"));
                httpServletResponse.setStatus(400);
                return "logginnView";
            }
		
		spillerService.logginnSpiller(httpServletRequest, spiller);
		
		
		
		return "redirect:/lobby";
	}
	
	@GetMapping("/utlogging")
	public String postUtlogging(HttpSession httpSession) {
		spillerService.loggUtSpiller(httpSession);
		return "redirect:/innlogging";
	}
}
