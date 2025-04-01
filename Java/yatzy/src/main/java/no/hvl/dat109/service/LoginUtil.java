package no.hvl.dat109.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LoginUtil {
	public static void loggUtBruker(HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
	}
	
	public static void loggInnBruker(HttpServletRequest request, Bruker bruker) {
		loggUtBruker(request.getSession());
		HttpSession sesjon = request.getSession();
		sesjon.setAttribute("bruker", bruker);
		sesjon.setMaxInactiveInterval(30); //sekunder		
	}
	public static boolean erBrukerInnlogget(HttpSession session) {
		return session != null && session.getAttribute("bruker") != null;
	}
}
