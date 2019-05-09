package projektinis;

import java.util.Scanner;

public class Preke {
    public int ID;
    public String pavadinimas;
    public double svoris;
    public int kiekis;
    public double didmKaina;
    public double mazKaina;

    static Scanner scanner = new Scanner(System.in);

    public static void nuskaitykNaujaPrekeToSandelis() {

//		System.out.println("0 - Grizti atgal");
        System.out.println("Iveskite naujos prekes ID");
        int ID = Projektinis.teigSkaiciausNuskaitymas();

        Preke rastaPreke = Sandelis.ieskotiPrekesPagalID(ID);

        if (rastaPreke.ID != 0) {
            System.out.println("Preke su tokiu ID jau yra sandelyje! Galite tiesiog keisti jos kieki.");
            Projektinis.adminMeniu2111(rastaPreke);

        } else {
            System.out.println("Iveskite naujos prekes pavadinima:");
            String pavadinimas = scanner.nextLine();
            System.out.println();
            System.out.println("Iveskite uzsakoma naujos prekes kieki:");
            int kiekis = Projektinis.teigSkaiciausNuskaitymas();
            System.out.println("Iveskite naujos prekes svori:");
            double svoris = Projektinis.teigRealSkaiciausNuskaitymas();
            System.out.println("Iveskite naujos prekes didmenine kaina:");
            double didmKaina = Projektinis.teigRealSkaiciausNuskaitymas();
            System.out.println("Iveskite naujos prekes mazmenine kaina:");
            double mazKaina = Projektinis.teigRealSkaiciausNuskaitymas();
//		jei ivedamas 0, iskart suveikia grizimas atgal i adminMeniu21();

            Preke naujaPreke = new Preke();
            naujaPreke.ID = ID;
            naujaPreke.pavadinimas = pavadinimas;
            naujaPreke.svoris = svoris;
            naujaPreke.didmKaina = didmKaina;
            naujaPreke.mazKaina = mazKaina;

            if (PiniguLikutis.getKiekis() >= kiekis * didmKaina) {
                naujaPreke.kiekis = kiekis;

                // Sandelis.prekes.add(naujaPreke);

                System.out.println("Uzsakymas atliktas, preke ikelta i sandeli!");
                System.out.println();
                double uzpirkimoKaina = kiekis * didmKaina;
                PiniguLikutis.keiskPiniguLikuti(-uzpirkimoKaina);
            } else {
                naujaPreke.kiekis = 0;

                // Sandelis.prekes.add(naujaPreke);
                System.out.println("Nepakankamas pinigu likutis parduotuveje!");
                System.out.println();
                Projektinis.adminMeniu22();
            }


            Sandelis.irasytiPrekeDB(naujaPreke);
        }
    }

    public static void keiskPrekesKiekiSandely(Preke keiciamaPreke) {
        System.out.println("Iveskite norima prekes kiekio korekcija (+/-)");
        int ivestaKiekioKorekcija = Projektinis.kiekioNuskaitymas();

		/*for (Preke p : Sandelis.prekes) {
			if (p.ID == prekesID) {
				if (ivestaKiekioKorekcija * p.didmKaina > PiniguLikutis.getKiekis()) {
					System.out.println("Nepakankamas pinigu likutis parduotuveje!");
					System.out.println();
					Projektinis.adminMeniu2111(prekesID);
				} else if (-ivestaKiekioKorekcija > p.kiekis) {
					System.out.println("Nepakankamas prekes likutis sandelyje!");
					System.out.println();
					Projektinis.adminMeniu2111(prekesID);
				} else {
					p.kiekis += ivestaKiekioKorekcija;
					double uzpirkimoKaina = ivestaKiekioKorekcija * p.didmKaina;
					PiniguLikutis.keiskPiniguLikuti(-uzpirkimoKaina);
					Sandelis.sandelioPrekesSpausdinimasAdminui(prekesID);
				}
			}
		}*/

        if (ivestaKiekioKorekcija * keiciamaPreke.didmKaina > PiniguLikutis.getKiekis()) {
            System.out.println("Nepakankamas pinigu likutis parduotuveje!");
            System.out.println();
            Projektinis.adminMeniu2111(keiciamaPreke);
        } else if (-ivestaKiekioKorekcija > keiciamaPreke.kiekis) {
            System.out.println("Nepakankamas prekes likutis sandelyje!");
            System.out.println();
            Projektinis.adminMeniu2111(keiciamaPreke);
        } else {
            keiciamaPreke.kiekis += ivestaKiekioKorekcija;
            PiniguLikutis.keiskPiniguLikuti(-ivestaKiekioKorekcija * keiciamaPreke.didmKaina);
            Sandelis.keistiPrekesKiekiDB(keiciamaPreke.ID, keiciamaPreke.kiekis);
        }
    }


    public static void keiskPrekesKiekiKrepselyje(int prekesID) {
        System.out.println("Kokio is tiesu sios prekes kiekio pageidaujate?");
        int patikslintasKiekis = Projektinis.teigSkaiciausNuskaitymas();

        for (Preke prekeKrepselyje : Krepselis.prekes) {
            for (Preke prekeSandely : Sandelis.prekes) {
                if (prekeKrepselyje.ID == prekesID && prekeSandely.ID == prekesID) {
                    if (patikslintasKiekis > prekeSandely.kiekis) {
                        System.out.println("Nepakankamas prekes likutis parduotuveje!");
                        System.out.println();
                        Projektinis.pirkejoMeniu122();
                    } else {
                        Krepselis.krepselioKaina += (patikslintasKiekis - prekeKrepselyje.kiekis)
                                * prekeKrepselyje.mazKaina;
                        prekeKrepselyje.kiekis = patikslintasKiekis;
                        System.out.println("Pakeitimai krepselyje atlikti!");
                    }
                }
            }
        }
    }
}
