package projektinis;

import java.sql.*;
import java.util.ArrayList;

public class Sandelis {
    public static ArrayList<Preke> prekes = new ArrayList<>();

    public static String url = "jdbc:sqlite:C:\\git\\source\\projects-to-git\\projektinis\\projektinioDuomenuBaze.db";

    public static void sandelioSpausdinimasAdminui() {
        System.out.println("Prekiu likuciai sandelyje siuo metu:");

/*		for (Preke p : Sandelis.prekes) {
			System.out.println(
					"ID " + p.ID + " pavadinimas " + p.pavadinimas + " --- " + "kiekis " + p.kiekis + " vnt., svoris " + p.svoris
							+ " kg, didmenine kaina " + p.didmKaina + " EUR, mazmenine kaina " + p.mazKaina + " EUR");
		}*/


        String sql = "SELECT * FROM sandelis";

        String s = "               ";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("___________________________________________________________________________________________________");
            System.out.println("PrekesId       Pavadinimas         Kiekis        Svoris       Didmenine kaina       Mazmenine kaina");
            System.out.println("___________________________________________________________________________________________________");
            while (rs.next()) {

                String prekesID = rs.getString("PrekesID");
                prekesID = prekesID + s.substring(prekesID.length() + 1);

                String pavadinimas = rs.getString("Pavadinimas");
                pavadinimas = pavadinimas + s.substring(pavadinimas.length() + 1);

                String kiekis = rs.getString("Kiekis");
                kiekis = kiekis + s.substring(kiekis.length() + 1);

                String svoris = rs.getString("Svoris");
                svoris = svoris + s.substring(svoris.length() + 1);

                String didmenineKaina = rs.getString("DidmenineKaina");
                didmenineKaina = didmenineKaina + s.substring(didmenineKaina.length() + 1);

                String mazmenineKaina = rs.getString("MazmenineKaina");
                mazmenineKaina = mazmenineKaina + s.substring(mazmenineKaina.length() + 1);

                System.out.println(prekesID + "\t" + pavadinimas + "\t" + kiekis + "\t" + svoris + "\t" + didmenineKaina + "\t" + mazmenineKaina);
            }
            System.out.println("___________________________________________________________________________________________________");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }



    public static void sandelioSpausdinimasPirkejui() {
        System.out.println("Prekiu likuciai sandelyje siuo metu:");

//        for (Preke p : Sandelis.prekes) {
//            System.out.println("ID " + p.ID + " pavadinimas " + p.pavadinimas + " --- " + "kiekis " + p.kiekis + " vnt., svoris "
//                    + p.svoris + " kg, kaina " + p.mazKaina + " EUR");
//        }

        String sql = "SELECT * FROM sandelis";

        String s = "               ";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("_______________________________________________________________________________");
            System.out.println("PrekesId       Pavadinimas         Kiekis        Svoris         Mazmenine kaina");
            System.out.println("_______________________________________________________________________________");
            while (rs.next()) {

                String prekesID = rs.getString("PrekesID");
                prekesID = prekesID + s.substring(prekesID.length() + 1);

                String pavadinimas = rs.getString("Pavadinimas");
                pavadinimas = pavadinimas + s.substring(pavadinimas.length() + 1);

                String kiekis = rs.getString("Kiekis");
                kiekis = kiekis + s.substring(kiekis.length() + 1);

                String svoris = rs.getString("Svoris");
                svoris = svoris + s.substring(kiekis.length() + 1);

                String mazmenineKaina = rs.getString("MazmenineKaina");
                mazmenineKaina = mazmenineKaina + s.substring(kiekis.length() + 1);

                System.out.println(prekesID + "\t" + pavadinimas + "\t\t" + kiekis + "\t" + svoris + "\t\t\t\t" + mazmenineKaina);
            }
            System.out.println("_______________________________________________________________________________");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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

    public static void sandelioPrekesSpausdinimasAdminui(int prekesID) {
        System.out.println();

        String sql = "SELECT * FROM sandelis WHERE PrekesID = ?";

        String s = "          ";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prekesID);

            ResultSet rs = stmt.executeQuery();

            System.out.println("______________________________________________________________________________________");
            System.out.println("PrekesId     Pavadinimas     Kiekis     Svoris     Didmenine kaina     Mazmenine kaina");
            System.out.println("______________________________________________________________________________________");
            while (rs.next()) {

                String pavadinimas = rs.getString("Pavadinimas");
                pavadinimas = pavadinimas + s.substring(pavadinimas.length() + 1);

                System.out.println(rs.getInt("PrekesID") + "\t     " + pavadinimas
                        + "\t      " + rs.getInt("Kiekis") + "\t     " + rs.getDouble("Svoris")
                        + "\t        " + rs.getDouble("DidmenineKaina") + "\t              " + rs.getDouble("MazmenineKaina"));
            }
            System.out.println("______________________________________________________________________________________");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();

    }

    public static Preke ieskotiPrekesPagalID(int prekesID) {
        String sql = "SELECT * FROM sandelis WHERE PrekesID = ?";

        Preke rastaPreke = new Preke();

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prekesID);
            ResultSet rs = stmt.executeQuery();

            System.out.println("nuskaitymas ivyko");

            if (rs.next()) {
                rastaPreke.ID = rs.getInt("PrekesID");
                rastaPreke.pavadinimas = rs.getString("Pavadinimas");
                rastaPreke.kiekis = rs.getInt("Kiekis");
                rastaPreke.svoris = rs.getDouble("Svoris");
                rastaPreke.didmKaina = rs.getDouble("DidmenineKaina");
                rastaPreke.mazKaina = rs.getDouble("MazmenineKaina");
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rastaPreke;
    }

    public static void keistiPrekesKiekiDB(int id, int kiekis) {
        String sql = "UPDATE sandelis SET kiekis = ?" + "WHERE PrekesId = ?";

        try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, kiekis);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();

            System.out.println("Kiekis atnaujintas");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void irasytiPrekeDB(Preke naujaPreke) {

        String sql = "INSERT INTO sandelis(PrekesID, Pavadinimas, Kiekis, Svoris, DidmenineKaina, MazmenineKaina) VALUES(?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, naujaPreke.ID);
            pstmt.setString(2, naujaPreke.pavadinimas);
            pstmt.setInt(3, naujaPreke.kiekis);
            pstmt.setDouble(4, naujaPreke.svoris);
            pstmt.setDouble(5, naujaPreke.didmKaina);
            pstmt.setDouble(6, naujaPreke.mazKaina);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }


    public static void rastosPrekesSpausdinimasAdminui(Preke keiciamaPreke) {

        // TODO: sutvarkyti header
        System.out.println("_______________________________________________________________________________");
        System.out.println("PrekesId       Pavadinimas         Kiekis        Svoris         Mazmenine kaina");
        System.out.println("_______________________________________________________________________________");

        System.out.println(keiciamaPreke.ID + "\t     " + keiciamaPreke.pavadinimas
                + "\t      " + keiciamaPreke.kiekis + "\t     " + keiciamaPreke.svoris
                + "\t        " + keiciamaPreke.didmKaina + "\t              " + keiciamaPreke.mazKaina);

        System.out.println("_______________________________________________________________________________");

    }


}
