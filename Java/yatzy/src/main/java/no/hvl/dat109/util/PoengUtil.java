package no.hvl.dat109.util;

import java.util.HashMap;
import java.util.List;

import no.hvl.dat109.yatzy.Kopp;

public class PoengUtil {

	public static int enere(List<Integer> liste) {
		int poeng = 0;
		for (int i : liste) {
			if (i == 1) {
				poeng++;
			}
		}
		return poeng;
	}

	public static Integer toere(List<Integer> liste) {
		int poeng = 0;
		for (int i : liste) {
			if (i == 2) {
				poeng += 2;
			}
		}
		return poeng;
	}

	public static Integer treere(List<Integer> liste) {
		int poeng = 0;
		for (int i : liste) {
			if (i == 3) {
				poeng += 3;
			}
		}
		return poeng;
	}

	public static Integer firere(List<Integer> liste) {
		int poeng = 0;
		for (int i : liste) {
			if (i == 4) {
				poeng += 4;
			}
		}
		return poeng;
	}

	public static Integer femmere(List<Integer> liste) {
		int poeng = 0;
		for (int i : liste) {
			if (i == 5) {
				poeng += 5;
			}
		}
		return poeng;
	}

	public static Integer seksere(List<Integer> liste) {
		int poeng = 0;
		for (int i : liste) {
			if (i == 6) {
				poeng += 6;
			}
		}
		return poeng;
	}

	public static Integer ettPar(List<Integer> liste) {
		HashMap<Integer, Integer> map = hashMapAvListe(liste);
		for (int i = 6; i > 0; i--) {
			if (map.get(i) >= 2) {
				return i * 2;
			}
		}
		return 0;
	}

	private static HashMap<Integer, Integer> hashMapAvListe(List<Integer> liste) {
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(1, 0);
		map.put(2, 0);
		map.put(3, 0);
		map.put(4, 0);
		map.put(5, 0);
		map.put(6, 0);
		for (Integer i : liste) {

			map.put(i, map.get(i) + 1);
		}
		return map;
	}

	public static Integer ToPar(List<Integer> liste) {
		HashMap<Integer, Integer> map = hashMapAvListe(liste);
		int par1 = 0;
		for (int i = 6; i > 0; i--) {
			if (map.get(i) >= 2) {
				par1 = i;
				break;
			}
		}
		int par2 = 0;
		for (int i = par1 - 1; i > 0; i--) {
			if (map.get(i) >= 2 && i != par1) {
				par2 = i;
				break;
			}
		}
		if (par1 == 0 || par2 == 0) {
			return 0;
		}
		return 2 * (par1 + par2);
	}

	public static Integer treLike(List<Integer> liste) {
		HashMap<Integer, Integer> map = hashMapAvListe(liste);
		int poeng = 0;
		for (int i = 6; i > 0; i--) {
			if (map.get(i) >= 3) {
				poeng = i * 3;
				break;
			}
		}
		return poeng;
	}

	public static Integer fireLike(List<Integer> liste) {
		HashMap<Integer, Integer> map = hashMapAvListe(liste);
		int poeng = 0;
		for (int i = 6; i > 0; i--) {
			if (map.get(i) >= 4) {
				poeng = i * 4;
				break;
			}
		}
		return poeng;
	}

	public static Integer litenStraight(List<Integer> liste) {
		List<Integer> sortert = liste.stream().sorted((a, b) -> a - b).toList();

		if (sortert.size() != Kopp.ANTALL_TERNINGER) {
			return 0;
		}

		for (int i = 0; i < sortert.size(); i++) {
			if (i + 1 != sortert.get(i)) {
				return 0;
			}
		}
		return 15;
	}

	public static Integer storStraight(List<Integer> liste) {
		List<Integer> sortert = liste.stream().sorted((a, b) -> a - b).toList();

		if (sortert.size() != Kopp.ANTALL_TERNINGER) {
			return 0;
		}

		for (int i = 0; i < sortert.size(); i++) {
			if (i + 2 != sortert.get(i)) {
				return 0;
			}
		}
		return 20;
	}

	public static Integer hus(List<Integer> liste) {
		HashMap<Integer, Integer> map = hashMapAvListe(liste);
		int trePar = 0;
		for (int i = 6; i > 0; i--) {
			if (map.get(i) == 3) {
				trePar = i;
				break;
			}
		}
		int toPar = 0;
		for (int i = 6; i > 0; i--) {
			if (map.get(i) == 2) {
				toPar = i;
				break;
			}
		}
		if (trePar == 0 || toPar == 0) {
			return 0;
		}
		return trePar * 3 + toPar * 2;
	}

	public static Integer sjanse(List<Integer> liste) {
		int sum = 0;
		for (Integer i : liste) {
			sum += i;
		}
		return sum;
	}

	public static Integer yatzy(List<Integer> liste) {
		if (erYatzy(liste)) {
			return 50;
		}
		return 0;
	}

	public static boolean erYatzy(List<Integer> liste) {
		HashMap<Integer, Integer> map = hashMapAvListe(liste);
		for (int i = 6; i > 0; i--) {
			if (map.get(i) >= 5) {
				return true;
			}
		}
		return false;
	}
}
