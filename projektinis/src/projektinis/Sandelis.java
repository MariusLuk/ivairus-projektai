package projektinis;

import java.sql.*;
import java.util.ArrayList;

public class Sandelis {
    public static ArrayList<Preke> prekes = new ArrayList<>();

    public static void sandelioSpausdinimasAdminui() {
        System.out.println("Prekiu likuciai sandelyje siuo metu:");

/*		for (Preke p : Sandelis.prekes) {
			System.out.println(
					"ID " + p.ID + " pavadinimas " + p.pavadinimas + " --- " + "kiekis " + p.kiekis + " vnt., svoris " + p.svoris
							+ " kg, didmenine kaina " + p.didmKaina + " EUR, mazmenine kaina " + p.mazKaina + " EUR");
		}*/

        String url = "jdbc:sqlite:C:\\git\\source\\projects-to-git\\projektinis\\projektinioDuomenuBaze.db";
        String sql = "SELECT * FROM sandelis";

        String s = "          ";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("______________________________________________________________________________________");
            System.out.println("PrekesId     Pavadinimas     Kiekis     Svoris     Didmenine kaina     Mazmenine kaina");
            System.out.println("______________________________________________________________________________________");
            while (rs.next()) {

                String pavadinimas = rs.getString("Pavadinimas");

                pavadinimas = pavadinimas + s.substring(pavadinimas.length()+1);


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

    public static void sandelioSpausdinimasPirkejui() {
        System.out.println("Prekiu likuciai sandelyje siuo metu:");

        for (Preke p : Sandelis.prekes) {
            System.out.println("ID " + p.ID + " pavadinimas " + p.pavadinimas + " --- " + "kiekis " + p.kiekis + " vnt., svoris "
                    + p.svoris + " kg, kaina " + p.mazKaina + " EUR");
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

    public static Preke ieskotiPrekesPagalID(int prekesID) {
        String url = "jdbc:sqlite:C:\\git\\source\\projects-to-git\\projektinis\\projektinioDuomenuBaze.db";
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
        String url = "jdbc:sqlite:C:\\git\\source\\projects-to-git\\projektinis\\projektinioDuomenuBaze.db";
        String sql = "UPDATE sandelis SET kiekis = ? " + " WHERE PrekesId = ?";

        try (Connection conn = DriverManager.getConnection(url); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, kiekis);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();

            System.out.println("Kiekis atnaujintas");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}
