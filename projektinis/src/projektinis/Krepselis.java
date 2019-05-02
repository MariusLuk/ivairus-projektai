package projektinis;

import java.util.ArrayList;

public class Krepselis {
	public static ArrayList<Preke> prekes = new ArrayList<>();

	static double krepselioKaina = 0;

	public static void PrekeToKrepselis(Preke prekeIsSandelio, int kiekis) {

		Preke prekeKrepseliui = new Preke();
		prekeKrepseliui.ID = prekeIsSandelio.ID;
		prekeKrepseliui.didmKaina = prekeIsSandelio.didmKaina;
		prekeKrepseliui.mazKaina = prekeIsSandelio.mazKaina;
		prekeKrepseliui.pavadinimas = prekeIsSandelio.pavadinimas;
		prekeKrepseliui.svoris = prekeIsSandelio.svoris;
		prekeKrepseliui.kiekis = kiekis;

		prekes.add(prekeKrepseliui);
		krepselioKaina += prekeKrepseliui.kiekis * prekeKrepseliui.mazKaina;
		System.out.println("Jusu pageidaujamas prekes kiekis ikeltas i krepseli.");

	}

	public static void krepselioSpausdinimasPirkejui() {
		System.out.println("Jusu krepselis siuo metu:");

		for (Preke p : Krepselis.prekes) {
			System.out.println("ID " + p.ID + " " + p.pavadinimas + " --- " + "kiekis " + p.kiekis + " vnt. kaina "
					+ p.mazKaina + " EUR, is viso " + p.kiekis * p.mazKaina + " EUR");
		}
		System.out.println("Bendra moketina suma uz visa krepseli: " + krepselioKaina + " EUR");
		System.out.println();
	}

	public static void krepselioPrekesSpausdinimasPirkejui(int prekesID) {
		System.out.println();

		for (Preke p : Krepselis.prekes) {
			if (p.ID == prekesID) {
				System.out.println(
						"Prekes ID " + p.ID + ", pavadinimas " + p.pavadinimas + ", kiekis krepselyje siuo metu: " + p.kiekis);
				System.out.println("Mazmenine kaina " + p.mazKaina + " EUR");
			}
		}
		System.out.println();
	}

	public static void istustinkKrepseli() {
		prekes.clear();
		krepselioKaina = 0;
	}
}
