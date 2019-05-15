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
        int ID = Nuskaitymai.teigIntSkaiciausNuskaitymas();

        Preke rastaPreke = Sandelis.ieskotiSandelyjePrekesPagalID(ID);

        if (rastaPreke.ID != 0) {
            System.out.println("Preke su tokiu ID jau yra sandelyje! Galite tiesiog keisti jos kieki.");
            Navigacija.adminMeniu2111(rastaPreke);

        } else {
            System.out.println("Iveskite naujos prekes pavadinima:");
            String pavadinimas = scanner.nextLine();
            System.out.println();
            System.out.println("Iveskite uzsakoma naujos prekes kieki:");
            int kiekis = Nuskaitymai.teigIntSkaiciausNuskaitymas();
            System.out.println("Iveskite naujos prekes svori:");
            double svoris = Nuskaitymai.teigDoubleSkaiciausNuskaitymas();
            System.out.println("Iveskite naujos prekes didmenine kaina:");
            double didmKaina = Nuskaitymai.teigDoubleSkaiciausNuskaitymas();
            System.out.println("Iveskite naujos prekes mazmenine kaina:");
            double mazKaina = Nuskaitymai.teigDoubleSkaiciausNuskaitymas();
//		jei ivedamas 0, iskart suveikia grizimas atgal i adminMeniu21();

            Preke naujaPreke = new Preke();
            naujaPreke.ID = ID;
            naujaPreke.pavadinimas = pavadinimas;
            naujaPreke.svoris = svoris;
            naujaPreke.didmKaina = didmKaina;
            naujaPreke.mazKaina = mazKaina;

            if (PiniguLikutis.getKiekis() >= kiekis * didmKaina) {
                naujaPreke.kiekis = kiekis;
                System.out.println("Uzsakymas atliktas, preke ikelta i sandeli!");
                System.out.println();
                double uzpirkimoKaina = kiekis * didmKaina;
                PiniguLikutis.keiskPiniguLikuti(-uzpirkimoKaina);
            } else {
                naujaPreke.kiekis = 0;
                System.out.println("Nepakankamas pinigu likutis parduotuveje!");
                System.out.println();
                Navigacija.adminMeniu22();
            }
            Sandelis.irasytiPrekeToSandelioDB(naujaPreke);
        }
    }

    public static void keiskPrekesKiekiSandely(Preke keiciamaPreke) {
        System.out.println("Iveskite norima prekes kiekio korekcija (+/-)");
        int ivestaKiekioKorekcija = Nuskaitymai.kiekioIntNuskaitymas();

        if (ivestaKiekioKorekcija * keiciamaPreke.didmKaina > PiniguLikutis.getKiekis()) {
            System.out.println("Nepakankamas pinigu likutis parduotuveje!");
            System.out.println();
            Navigacija.adminMeniu2111(keiciamaPreke);
        } else if (-ivestaKiekioKorekcija > keiciamaPreke.kiekis) {
            System.out.println("Nepakankamas prekes likutis sandelyje!");
            System.out.println();
            Navigacija.adminMeniu2111(keiciamaPreke);
        } else {
            PiniguLikutis.keiskPiniguLikuti(-ivestaKiekioKorekcija * keiciamaPreke.didmKaina);
            Sandelis.keistiPrekesKiekiSandelioDB(keiciamaPreke.ID, ivestaKiekioKorekcija);
        }
    }

    public static void keiskPrekesKiekiKrepselyje(Preke rastaPrekeKrepselyje) {
        System.out.println("Kokio is tiesu sios prekes kiekio pageidaujate?");
        int patikslintasKiekis = Nuskaitymai.teigIntSkaiciausNuskaitymas();

        Preke rastaPrekeSandelyje =  Sandelis.ieskotiSandelyjePrekesPagalID(rastaPrekeKrepselyje.ID);

        if (patikslintasKiekis > rastaPrekeSandelyje.kiekis) {
            System.out.println("Nepakankamas prekes likutis parduotuveje!");
            System.out.println();
            Navigacija.pirkejoMeniu122();
        } else {
            Krepselis.krepselioKaina += (patikslintasKiekis - rastaPrekeKrepselyje.kiekis)
                    * rastaPrekeKrepselyje.mazKaina;
            rastaPrekeKrepselyje.kiekis = patikslintasKiekis;
            System.out.println("Pakeitimai krepselyje atlikti!");
        }
    }
}
