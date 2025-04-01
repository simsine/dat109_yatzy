package no.hvl.dat109.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import no.hvl.dat109.entity.Spiller;

public class LoginUtil {
	public static void loggUtBruker(HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
	}
	
	public static void loggInnBruker(HttpServletRequest request, Spiller spiller) {
		loggUtBruker(request.getSession());
		HttpSession sesjon = request.getSession();
		sesjon.setAttribute("spiller", spiller);
	}
	public static boolean erBrukerInnlogget(HttpSession session) {
		return session != null && session.getAttribute("spiller") != null;
	}
}
