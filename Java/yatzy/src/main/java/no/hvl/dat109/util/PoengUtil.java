package no.hvl.dat109.util;

import java.util.List;

public class PoengUtil {

	
	public static int enere(List<Integer> terninger) {
		int poeng = 0;
		for(int i : terninger) {
			if(i == 1) {
				poeng++;
			}
		}
		return poeng;
	}
}
