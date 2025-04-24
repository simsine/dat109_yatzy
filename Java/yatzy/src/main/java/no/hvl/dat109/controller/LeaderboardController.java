package no.hvl.dat109.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import no.hvl.dat109.service.LeaderboardService;
import no.hvl.dat109.service.SpillerService;

@Controller
public class LeaderboardController {

	@Autowired
	private SpillerService spillerService;

	@Autowired
	private LeaderboardService leaderboardService;

	@GetMapping("/leaderboard")
	public String getLeaderboard(HttpSession httpSession, Model model) {
		// Omdiriger om ikke innlogget
		if (!spillerService.erSpillerInnlogget(httpSession)) {
			return "redirect:/innlogging";
		}

		Map<String, Integer> leaderboard = leaderboardService.hentPoeng();
		model.addAttribute("leaderboard", leaderboard);

		return "leaderboardView";
	}
}
