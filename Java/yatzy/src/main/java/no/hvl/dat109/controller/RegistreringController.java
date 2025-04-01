package no.hvl.dat109.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import no.hvl.dat109.entity.Spiller;
import no.hvl.dat109.form.RegistreringForm;
import no.hvl.dat109.service.RegistreringService;
import no.hvl.dat109.util.LoginUtil;

@Controller
public class RegistreringController {

	@Autowired
	RegistreringService registreringService;
	
	@GetMapping("/registrering")
	public String getRegistrering() {
		return "registrerView";
	}
	
	@PostMapping("/registrering")
	public String postRegistrering(
		Model model,
		@Valid RegistreringForm registreringForm, BindingResult bindingResult,
		@RequestParam String passord_re,
		RedirectAttributes redirectAttributes,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse
	) {
		// Vi gjennomfører programmatisk validering
		registreringService.validerUniktBrukernavn(bindingResult, registreringForm.getBrukernavn());
		registreringService.validerLiktPassord(bindingResult, registreringForm.getPassord(), passord_re);
		
		if (bindingResult.hasErrors()) {
			// Det har oppstått en valideringsfeil, vi rendrer siden på nytt og viser alle feilmeldinger
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(e -> e.getDefaultMessage())
					.toList();
			model.addAttribute("errors", errors);
			httpServletResponse.setStatus(400);
			return "registrerView";
		}

		Spiller nySpiller = registreringService.registrerNySpiller(registreringForm);
		
		LoginUtil.loggInnBruker(httpServletRequest, nySpiller);
		
		return "redirect:/lobby";
	}
}
