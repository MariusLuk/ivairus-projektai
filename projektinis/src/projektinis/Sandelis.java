package projektinis;

import java.sql.*;
import java.util.ArrayList;

public class Sandelis {

    public static String url = "jdbc:sqlite:projektinioDuomenuBaze.db";

    public static void sandelioSpausdinimasAdminui() {
        System.out.println("Prekiu likuciai sandelyje siuo metu:");

        String sql = "SELECT * FROM sandelis";
        String s = "               ";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("_______________________________________________________________________________________________");
            System.out.println("Prekes ID       Pavadinimas     Kiekis          Svoris        Didmenine kaina   Mazmenine kaina");
            System.out.println("_______________________________________________________________________________________________");
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
            System.out.println("_______________________________________________________________________________________________");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }


    public static void sandelioSpausdinimasPirkejui() {
        System.out.println("Prekiu likuciai sandelyje siuo metu:");
        String sql = "SELECT * FROM sandelis";
        String s = "               ";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("_______________________________________________________________________________");
            System.out.println("PrekesId       Pavadinimas     Kiekis          Svoris           Mazmenine kaina");
            System.out.println("_______________________________________________________________________________");
            while (rs.next()) {

                String prekesID = rs.getString("PrekesID");
                prekesID = prekesID + s.substring(prekesID.length() + 1);

                String pavadinimas = rs.getString("Pavadinimas");
                pavadinimas = pavadinimas + s.substring(pavadinimas.length() + 1);

                String kiekis = rs.getString("Kiekis");
                kiekis = kiekis + s.substring(kiekis.length() + 1);

                String svoris = rs.getString("Svoris");
                svoris = svoris + s.substring(svoris.length() + 1);

                String mazmenineKaina = rs.getString("MazmenineKaina");
                mazmenineKaina = mazmenineKaina + s.substring(mazmenineKaina.length() + 1);

                System.out.println(prekesID + "\t" + pavadinimas + "\t" + kiekis + "\t" + svoris + "\t" + mazmenineKaina);
            }
            System.out.println("_______________________________________________________________________________");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    public static void sandelioPrekesSpausdinimasPirkejui(int prekesID) {
        System.out.println();

        Preke p =  Sandelis.ieskotiSandelyjePrekesPagalID(prekesID);

        System.out.println(
                "Prekes ID " + p.ID + ", pavadinimas " + p.pavadinimas + ", likutis sandelyje siuo metu: " + p.kiekis);
        System.out.println("Mazmenine kaina " + p.mazKaina + " EUR");

        System.out.println();
    }

    public static void krepselioPrekiuIsemimasIsSandelio() {
        System.out.println();

        for (Preke krepselioPreke : Krepselis.prekes) {
            keistiPrekesKiekiSandelioDB(krepselioPreke.ID, -krepselioPreke.kiekis);
        }

        System.out.println();
    }

    public static Preke ieskotiSandelyjePrekesPagalID(int prekesID) {
        String sql = "SELECT * FROM sandelis WHERE PrekesID = ?";

        Preke rastaPreke = new Preke();

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, prekesID);
            ResultSet rs = stmt.executeQuery();

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

    public static void keistiPrekesKiekiSandelioDB(int id, int kiekis) {
        String sql = "UPDATE sandelis SET kiekis = kiekis + ?" + "WHERE PrekesId = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, kiekis);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void irasytiPrekeToSandelioDB(Preke naujaPreke) {

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


    public static void rastosSandelioPrekesSpausdinimasAdminui(Preke keiciamaPreke) {

        System.out.println("_______________________________________________________________________________________________");
        System.out.println("Prekes ID       Pavadinimas     Kiekis          Svoris        Didmenine kaina   Mazmenine kaina");
        System.out.println("_______________________________________________________________________________________________");

        String s = "               ";

        String prekesID = Integer.toString(keiciamaPreke.ID);
        prekesID = prekesID + s.substring(prekesID.length() + 1);

        String pavadinimas = keiciamaPreke.pavadinimas;
        pavadinimas = pavadinimas + s.substring(pavadinimas.length() + 1);

        String kiekis = Double.toString(keiciamaPreke.kiekis);
        kiekis = kiekis + s.substring(kiekis.length() + 1);

        String svoris = Double.toString(keiciamaPreke.svoris);
        svoris = svoris + s.substring(svoris.length() + 1);

        String didmenineKaina = Double.toString(keiciamaPreke.didmKaina);
        didmenineKaina = didmenineKaina + s.substring(didmenineKaina.length() + 1);

        String mazmenineKaina = Double.toString(keiciamaPreke.mazKaina);
        mazmenineKaina = mazmenineKaina + s.substring(mazmenineKaina.length() + 1);

        System.out.println(prekesID + "\t" + pavadinimas
                + "\t" + kiekis + "\t" + svoris
                + "\t" + didmenineKaina + "\t" + mazmenineKaina);

        System.out.println("_______________________________________________________________________________________________");

    }

    public static void rastosSandelioPrekesSpausdinimasPirkejui(Preke keiciamaPreke) {

        System.out.println("_____________________________________________________________________________");
        System.out.println("Prekes ID       Pavadinimas     Kiekis          Svoris        Mazmenine kaina");
        System.out.println("_____________________________________________________________________________");

        String s = "               ";

        String prekesID = Integer.toString(keiciamaPreke.ID);
        prekesID = prekesID + s.substring(prekesID.length() + 1);

        String pavadinimas = keiciamaPreke.pavadinimas;
        pavadinimas = pavadinimas + s.substring(pavadinimas.length() + 1);

        String kiekis = Double.toString(keiciamaPreke.kiekis);
        kiekis = kiekis + s.substring(kiekis.length() + 1);

        String svoris = Double.toString(keiciamaPreke.svoris);
        svoris = svoris + s.substring(svoris.length() + 1);

        String mazmenineKaina = Double.toString(keiciamaPreke.mazKaina);
        mazmenineKaina = mazmenineKaina + s.substring(mazmenineKaina.length() + 1);

        System.out.println(prekesID + "\t" + pavadinimas
                + "\t" + kiekis + "\t" + svoris
                + "\t" + mazmenineKaina);

        System.out.println("_____________________________________________________________________________");

    }

}
