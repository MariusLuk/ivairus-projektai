package projektinis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Projektinis {

    static Krepselis krepselis = new Krepselis();

    static Scanner scanner = new Scanner(System.in);

    public static int nuskaitymas(int maxIvestis) {
        boolean ivestisTinkama = false;
        int pasirinkimas = 0;

        do {
            if (scanner.hasNextInt()) {
                pasirinkimas = scanner.nextInt();
                if (pasirinkimas >= 0 && pasirinkimas <= maxIvestis) {
                    ivestisTinkama = true;
                } else {
                    System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
                }
            } else {
                System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
            }

            scanner.nextLine();
        } while (!ivestisTinkama);
        System.out.println();
        return pasirinkimas;
    }

    public static int teigIntSkaiciausNuskaitymas() {
        boolean ivestisTinkama = false;
        int pasirinkimas = 0;

        do {
            if (scanner.hasNextInt()) {
                pasirinkimas = scanner.nextInt();
                if (pasirinkimas > 0) {
                    ivestisTinkama = true;
                } else {
                    System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
                }
            } else {
                System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
            }
            scanner.nextLine();
        } while (!ivestisTinkama);
        System.out.println();
        return pasirinkimas;
    }

    public static double teigDoubleSkaiciausNuskaitymas() {
        boolean ivestisTinkama = false;
        double pasirinkimas = 0;

        do {
            if (scanner.hasNextDouble()) {
                pasirinkimas = scanner.nextDouble();
                if (pasirinkimas > 0) {
                    ivestisTinkama = true;
                } else {
                    System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
                }
            } else {
                System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
            }
            scanner.nextLine();
        } while (!ivestisTinkama);
        System.out.println();
        return pasirinkimas;
    }

    public static double doubleSkaiciausNuskaitymas() {
        boolean ivestisTinkama = false;
        double pasirinkimas = 0;

        do {
            if (scanner.hasNextDouble()) {
                pasirinkimas = scanner.nextDouble();
                ivestisTinkama = true;
            } else {
                System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
            }
            scanner.nextLine();
        } while (!ivestisTinkama);
        System.out.println();
        return pasirinkimas;
    }

    public static int kiekioIntNuskaitymas() {
        boolean ivestisTinkama = false;
        int pasirinkimas = 0;

        do {
            if (scanner.hasNextInt()) {
                pasirinkimas = scanner.nextInt();
                ivestisTinkama = true;
            } else {
                System.out.println("Nekorektiska ivestis! Pakartokite dar karta.");
            }
            scanner.nextLine();
        } while (!ivestisTinkama);
        System.out.println();
        return pasirinkimas;
    }

    public static void pradzia() {
        System.out.println("Sveiki atvyke i e-parduotuve!");
        System.out.println();
        System.out.println("Jei esate pirkejas - spauskite 1");
        System.out.println("Jei esate administratorius - spauskite 2");
        System.out.println("Jei norite baigti darba ir iseiti is programos - spauskite 3");

        switch (nuskaitymas(3)) {
            case 0:
                pradzia();
                break;
            case 1:
                pirkejoMeniu1();
                break;
            case 2:
                tikrinkAdminPassword();
                break;
            case 3:
                System.out.println("Dekojame, kad apsilankete! Laukiame sugriztant!");
                System.exit(0);
            default:
                System.out.println("Nekorektiska ivestis!");
                pradzia();
        }
    }

    public static void pirkejoMeniu1() {
        System.out.println("Naviguojate PIRKEJO MENIU. Pasirinkite norima veiksma:");
        System.out.println();
        System.out.println("0 - Grizti i pradzia");
        System.out.println("1 - Rinktis prekes is e-parduotuves");
        System.out.println("2 - Perziureti krepseli");
        System.out.println("3 - Baigti darba ir iseiti is programos");

        switch (nuskaitymas(3)) {
            case 0:
                pradzia();
                break;
            case 1:
                pirkejoMeniu11();
                break;
            case 2:
                pirkejoMeniu12();
                break;
            case 3:
                System.out.println("Dekojame, kad apsilankete! Laukiame sugriztant!");
                System.exit(0);
            default:
                System.out.println("Nekorektiska ivestis!");
                pirkejoMeniu1();
        }
    }

    public static void pirkejoMeniu11() {
        Sandelis.sandelioSpausdinimasPirkejui();
        System.out.println("0 - Grizti atgal");
        System.out.println("1 - Perziureti krepseli");
        System.out.println("2 - Pasirinkti preke pagal ID");

        switch (nuskaitymas(2)) {
            case 0:
                pirkejoMeniu1();
                break;
            case 1:
                pirkejoMeniu12();
                break;
            case 2:
                pirkejoMeniu111();
                break;
            default:
                System.out.println("Nekorektiska ivestis!");
                pirkejoMeniu11();
        }
    }

    public static void pirkejoMeniu111() {
        Sandelis.sandelioSpausdinimasPirkejui();
        System.out.println("Iveskite prekes ID");

        int prekesID = teigIntSkaiciausNuskaitymas();

        Preke rastaPrekeKrepselyje = Krepselis.ieskotiPrekeKrepselyjePagalId(prekesID);

        if (rastaPrekeKrepselyje != null) {
            System.out.println("Preke su tokiu ID jau yra Jusu krepselyje! Galite tiesiog keisti jos kieki.");
            pirkejoMeniu12211(rastaPrekeKrepselyje);
        } else {

            Preke rastaPrekeSandelyje = Sandelis.ieskotiSandelyjePrekesPagalID(prekesID);

            if (rastaPrekeSandelyje.ID != 0) {
                pirkejoMeniu1111(rastaPrekeSandelyje, rastaPrekeKrepselyje);
            } else {
                System.out.println("Toks prekes ID nerastas!");
                pirkejoMeniu11();
            }
        }
    }

    public static void pirkejoMeniu1111(Preke rastaPrekeSandelyje, Preke rastaPrekeKrepselyje) {
        Sandelis.rastosPrekesSpausdinimasPirkejui(rastaPrekeSandelyje);
        System.out.println("Iveskite norima prekes kieki");
        int pirkejoNorimasKiekis = Projektinis.kiekioIntNuskaitymas();

        if (rastaPrekeSandelyje.kiekis >= pirkejoNorimasKiekis) { /*- krepselio prekiu masyve jau esancios tos pacios prekes kiekis(jei toks jau yra)*/
            Krepselis.PrekeToKrepselis(rastaPrekeSandelyje, pirkejoNorimasKiekis);
        } else {
            System.out.println("Nepakankamas prekes likutis parduotuveje!");
            Projektinis.pirkejoMeniu11();
        }
        pirkejoMeniu12();
    }

    public static void pirkejoMeniu12() {
        Krepselis.krepselioSpausdinimasPirkejui();
        System.out.println("0 - Grizti atgal");
        System.out.println("1 - Patvirtinti uzsakyma ir apmoketi");
        System.out.println("2 - Modifikuoti krepselio turini");

        switch (nuskaitymas(2)) {
            case 0:
                pradzia();
                break;
            case 1:
                pirkejoMeniu121();
                break;
            case 2:
                pirkejoMeniu122();
                break;
            default:
                System.out.println("Nekorektiska ivestis!");
                pirkejoMeniu12();
        }
    }

    public static void pirkejoMeniu121() {
        if (Krepselis.krepselioKaina <= 0) {
            System.out.println("Uzsakymas NEGALIMAS, nes Jusu krepselis kolkas TUSCIAS!");
            pirkejoMeniu122();
        } else {
            Krepselis.krepselioSpausdinimasPirkejui();
            System.out.println("0 - Grizti atgal");
            System.out.println("1 - Ivesti pirkejo duomenis, patvirtinti ir apmoketi uzsakyma");

            switch (nuskaitymas(1)) {
                case 0:
                    pirkejoMeniu12();
                    break;
                case 1:
                    nuskaitykPirkejoRekvizitus();
                    break;
                default:
                    System.out.println("Nekorektiska ivestis!");
                    pirkejoMeniu121();
            }
        }
    }

    public static void nuskaitykPirkejoRekvizitus() {
        System.out.println("Iveskite savo ID");
        int pirkejoID = teigIntSkaiciausNuskaitymas();
        System.out.println("Iveskite savo varda ir pavarde arba imones pavadinima:");
        String pirkejoPavadinimas = scanner.nextLine();
        System.out.println("Iveskite savo e-mail adresa:");
        String pirkejoEmail = scanner.nextLine();
        System.out.println("Iveskite pristatymo adresa:");
        String pirkejoAdresas = scanner.nextLine();

        // TODO: jei viskas ok, tai toliau:

        Sandelis.krepselioPrekiuIsemimasIsSandelio();
        uzsakymasToAtaskaita(pirkejoID, pirkejoPavadinimas, pirkejoEmail, pirkejoAdresas, Krepselis.krepselioKaina);
        PiniguLikutis.keiskPiniguLikuti(Krepselis.krepselioKaina);
        Krepselis.istustinkKrepseli();

        System.out.println("Uzsakymas patvirtintas ir artimiausiu metu bus issiustas Jums!");
        System.out.println("Dekojame, kad pirkote!");
        System.out.println();

        pirkejoMeniu1();
    }

    public static void uzsakymasToAtaskaita(int pirkejoID, String pirkejoPavadinimas, String pirkejoEmail,
                                            String pirkejoAdresas, double krepselioKaina) {
        String url = "jdbc:sqlite:projektinioDuomenuBaze.db";
        String sql = "INSERT INTO uzsakymai(pirkejoID,pirkejoPavadinimas,pirkejoEmail,pirkejoAdresas,krepselioKaina) VALUES(?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, pirkejoID);
            pstmt.setString(2, pirkejoPavadinimas);
            pstmt.setString(3, pirkejoEmail);
            pstmt.setString(4, pirkejoAdresas);
            pstmt.setDouble(5, krepselioKaina);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void pirkejoMeniu122() {
        Krepselis.krepselioSpausdinimasPirkejui();
        System.out.println("0 - Grizti atgal");
        System.out.println("1 - Prideti nauja preke i krepseli");
        System.out.println("2 - Rinktis preke, kuria norima keisti");

        switch (nuskaitymas(2)) {
            case 0:
                pirkejoMeniu12();
                break;
            case 1:
                pirkejoMeniu11();
                break;
            case 2:
                pirkejoMeniu1221();
                break;
            default:
                System.out.println("Nekorektiska ivestis!");
                pirkejoMeniu122();
        }
    }

    public static void pirkejoMeniu1221() {
        Krepselis.krepselioSpausdinimasPirkejui();
        System.out.println("Iveskite ID prekes, kuria norite keisti");
        int prekesID = teigIntSkaiciausNuskaitymas();

        Preke rastaPreke = Krepselis.ieskotiPrekeKrepselyjePagalId(prekesID);

        if (rastaPreke != null) {
            pirkejoMeniu12211(rastaPreke);
        } else {
            System.out.println("Prekes su tokiu ID Jusu krepselyje nera!");
            pirkejoMeniu122();
        }
    }

    public static void pirkejoMeniu12211(Preke rastaPrekeKrepselyje) {
//		Rodomas prekes kiekis, jau esantis krepselyje:
        Krepselis.krepselioPrekesSpausdinimasPirkejui(rastaPrekeKrepselyje);
//		Rodomas likes aktualios prekes kiekis sandelyje:
        Sandelis.sandelioPrekesSpausdinimasPirkejui(rastaPrekeKrepselyje.ID);

        Preke.keiskPrekesKiekiKrepselyje(rastaPrekeKrepselyje);

        pirkejoMeniu12();
    }

    public static void tikrinkAdminPassword() {
        String adminPassword = "1234";
        String ivestasPassword = null;
//        String ivestasPassword = "1234";
        int blogoPasswordSkaitliukas = 0;

        while (!adminPassword.equals(ivestasPassword) && blogoPasswordSkaitliukas < 3) {
            System.out.println("Iveskite slaptazodi, noredami prisijungti prie ADMINISTRATORIAUS MENIU:");
            ivestasPassword = scanner.nextLine();
            if (!adminPassword.equals(ivestasPassword)) {
                blogoPasswordSkaitliukas = blogoPasswordSkaitliukas + 1;
                System.out.println("Slaptazodis nekorektiskas!");
                System.out.println();
            }
        }

        if (blogoPasswordSkaitliukas == 3) {
            System.out.println(blogoPasswordSkaitliukas + " kartus ivedete nekorektiska slaptazodi!");
            System.exit(0);
        }

        if (adminPassword.equals(ivestasPassword)) {
            System.out.println();
            adminMeniu2();
        }
    }

    public static void adminMeniu2() {
        System.out.println("Naviguojate ADMINISTRATORIAUS MENIU. Pasirinkite norima veiksma:");
        System.out.println();
        System.out.println("0 - Grizti i pradzia");
        System.out.println("1 - Prekiu atsargu sandelyje perziura ir/ar ju keitimas");
        System.out.println("2 - Pinigu likutis ir jo keitimas");
        System.out.println("3 - Uzsakymu isklotines perziura");
        System.out.println("4 - Baigti darba ir iseiti is programos");

        switch (nuskaitymas(4)) {
            case 0:
                pradzia();
                break;
            case 1:
                adminMeniu21();
                break;
            case 2:
                adminMeniu22();
                break;
            case 3:
                adminMeniu23();
                break;
            case 4:
                System.out.println("Dekojame, kad apsilankete! Laukiame sugriztant!");
                System.exit(0);
            default:
                System.out.println("Nekorektiska ivestis!");
                adminMeniu2();
        }
    }

    public static void adminMeniu21() {
        Sandelis.sandelioSpausdinimasAdminui();
        System.out.println("0 - Grizti atgal");
        System.out.println("1 - Keisti sandelyje turimos prekes atsargas");
        System.out.println("2 - Papildyti sandeli nauja preke");

        switch (nuskaitymas(2)) {
            case 0:
                adminMeniu2();
                break;
            case 1:
                adminMeniu211();
                break;
            case 2:
                adminMeniu212();
                break;
            default:
                System.out.println("Nekorektiska ivestis!");
                adminMeniu21();
        }
    }

    public static void adminMeniu211() {
        Sandelis.sandelioSpausdinimasAdminui();
        System.out.println("Iveskite prekes ID");
        int prekesID = teigIntSkaiciausNuskaitymas();
        Preke rastaPreke = Sandelis.ieskotiSandelyjePrekesPagalID(prekesID);

        if (rastaPreke.ID != 0) {
            adminMeniu2111(rastaPreke);
        } else {
            System.out.println("Toks ID nerastas!");
            System.out.println();
            adminMeniu21();
        }
    }

    public static void adminMeniu2111(Preke keiciamaPreke) {
        Sandelis.rastosPrekesSpausdinimasAdminui(keiciamaPreke);
        PiniguLikutis.spausdinkPiniguLikuti();
        Preke.keiskPrekesKiekiSandely(keiciamaPreke);
        adminMeniu21();
    }

    // Papildyti sandeli
    public static void adminMeniu212() {

        PiniguLikutis.spausdinkPiniguLikuti();
        Preke.nuskaitykNaujaPrekeToSandelis();
        adminMeniu21();
    }

    public static void adminMeniu22() {
        PiniguLikutis.spausdinkPiniguLikuti();
        System.out.println("0 - Grizti atgal");
        System.out.println("1 - Didinti/mazinti pinigu likuti");

        switch (nuskaitymas(1)) {
            case 0:
                adminMeniu2();
                break;
            case 1:
                adminMeniu221();
                break;
            default:
                System.out.println("Nekorektiska ivestis!");
                adminMeniu22();
        }
    }

    public static void adminMeniu221() {
        PiniguLikutis.spausdinkPiniguLikuti();
        System.out.println("Iveskite pinigu pokyti (+/-) EUR");
        double ivestasPokytis = doubleSkaiciausNuskaitymas();
        if ((PiniguLikutis.getKiekis() + ivestasPokytis) >= 0) {
            PiniguLikutis.keiskPiniguLikuti(ivestasPokytis);
        } else {
            System.out.println("Veiksmas negalimas, nes ivesta suma virsija esama pinigu likuti!");
            adminMeniu221();
        }
        PiniguLikutis.spausdinkPiniguLikuti();
        adminMeniu2();
    }

    public static void adminMeniu23() {
        rodykUzsakymuAtaskaita();
        System.out.println("0 - Grizti atgal");
        switch (nuskaitymas(0)) {
            case 0:
                adminMeniu2();
                break;
            default:
                System.out.println("Nekorektiska ivestis!");
                adminMeniu23();
        }
    }

    public static void rodykUzsakymuAtaskaita() {
        String url = "jdbc:sqlite:projektinioDuomenuBaze.db";
        String sql = "SELECT pirkejoID, pirkejoPavadinimas, pirkejoEmail, pirkejoAdresas, krepselioKaina, uzsakymoDataLaikas FROM uzsakymai";
        String s = "                          ";


        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("________________________________________________________________________________________________________________________________________________________________");
            System.out.println("Pirkejo ID                  Pavarde arba pavadinimas    Pirkejo e-mail              Pirkejo adresas             Uzsakymo suma EUR           Uzsakymo data/laikas");
            System.out.println("________________________________________________________________________________________________________________________________________________________________");
            while (rs.next()) {

                String pirkejoIDAtaskaitai = rs.getString("pirkejoID");
                pirkejoIDAtaskaitai = pirkejoIDAtaskaitai + s.substring(pirkejoIDAtaskaitai.length() + 1);

                String pirkejoPavadinimasAtaskaitai = rs.getString("pirkejoPavadinimas");
                pirkejoPavadinimasAtaskaitai = pirkejoPavadinimasAtaskaitai + s.substring(pirkejoPavadinimasAtaskaitai.length() + 1);

                String pirkejoEmailAtaskaitai = rs.getString("pirkejoEmail");
                pirkejoEmailAtaskaitai = pirkejoEmailAtaskaitai + s.substring(pirkejoEmailAtaskaitai.length() + 1);

                String pirkejoAdresasAtaskaitai = rs.getString("pirkejoAdresas");
                pirkejoAdresasAtaskaitai = pirkejoAdresasAtaskaitai + s.substring(pirkejoAdresasAtaskaitai.length() + 1);

                String krepselioKainaAtaskaitai = rs.getString("krepselioKaina");
                krepselioKainaAtaskaitai = krepselioKainaAtaskaitai + s.substring(krepselioKainaAtaskaitai.length() + 1);

                String uzsakymoDataLaikasAtaskaitai = rs.getString("uzsakymoDataLaikas");
                uzsakymoDataLaikasAtaskaitai = uzsakymoDataLaikasAtaskaitai + s.substring(uzsakymoDataLaikasAtaskaitai.length() + 1);

                System.out.println(pirkejoIDAtaskaitai + "\t" + pirkejoPavadinimasAtaskaitai + "\t" + pirkejoEmailAtaskaitai + "\t" +
                        pirkejoAdresasAtaskaitai + "\t" + krepselioKainaAtaskaitai + "\t" + uzsakymoDataLaikasAtaskaitai);
            }
            System.out.println("_______________________________________________________________________________________________________________________________________________________________");
            System.out.println();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        pradzia();
    }

}
