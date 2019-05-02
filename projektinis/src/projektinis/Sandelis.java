package projektinis;

import java.util.ArrayList;

public class Sandelis {
	public static ArrayList<Preke> prekes = new ArrayList<>();

	public static void sandelioSpausdinimasAdminui() {
		System.out.println("Prekiu likuciai sandelyje siuo metu:");

		for (Preke p : Sandelis.prekes) {
			System.out.println(
					"ID " + p.ID + " pavadinimas " + p.pavadinimas + " --- " + "kiekis " + p.kiekis + " vnt., svoris " + p.svoris
							+ " kg, didmenine kaina " + p.didmKaina + " EUR, mazmenine kaina " + p.mazKaina + " EUR");
		}

		System.out.println();
	}

	public static void sandelioSpausdinimasPirkejui() {
		System.out.println("Prekiu likuciai sandelyje siuo metu:");

		for (Preke p : Sandelis.prekes) {
			System.out.println("ID " + p.ID + " pavadinimas " + p.pavadinimas + " --- " + "kiekis " + p.kiekis + " vnt., svoris "
					+ p.svoris + " kg, kaina " + p.mazKaina + " EUR");
		}

		System.out.println();
	}

	public static void sandelioPrekesSpausdinimasAdminui(int prekesID) {
		System.out.println();

		for (Preke p : Sandelis.prekes) {
			if (p.ID == prekesID) {
				System.out.println(
						"Prekes ID " + p.ID + " pavadinimas " + p.pavadinimas + " likutis sandelyje siuo metu: " + p.kiekis);
				System.out.println("Didmenine kaina " + p.didmKaina + " EUR");
			}
		}
		System.out.println();
	}

	public static void sandelioPrekesSpausdinimasPirkejui(int prekesID) {
		System.out.println();

		for (Preke p : Sandelis.prekes) {
			if (p.ID == prekesID) {
				System.out.println(
						"Prekes ID " + p.ID + ", pavadinimas " + p.pavadinimas + ", likutis sandelyje siuo metu: " + p.kiekis);
				System.out.println("Mazmenine kaina " + p.mazKaina + " EUR");
			}
		}
		System.out.println();
	}

	public static void krepselioPrekiuIsemimasIsSandelio() {
		System.out.println();

		for (Preke krepselioPreke : Krepselis.prekes) {
			for (Preke sandelioPreke : Sandelis.prekes) {
				if (krepselioPreke.ID == sandelioPreke.ID) {
					sandelioPreke.kiekis -= krepselioPreke.kiekis;
//					System.out.println("Is sandelio isimta preke: " + sandelioPreke.pavadinimas + " kiekis: "
//							+ sandelioPreke.kiekis);
				}
			}
		}
		System.out.println();
	}
}
