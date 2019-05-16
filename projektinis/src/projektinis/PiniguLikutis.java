package projektinis;

import java.sql.*;

public class PiniguLikutis {

    private static Double kiekis;
    private static String url = "jdbc:sqlite:projektinioDuomenuBaze.db";

    public static double nuskaitykPiniguLikutiDB() {
        String sql = "SELECT * FROM piniguLikutis";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                kiekis = rs.getDouble("Likutis");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return kiekis;
    }

    public static void keiskPiniguLikuti(double piniguPokytis) {
        nuskaitykPiniguLikutiDB();
        kiekis += piniguPokytis;
        PiniguLikutis.atnaujintiPiniguLikutiDB(kiekis);

    }

    public static Double getKiekis() {
        return kiekis;
    }

    public static void setKiekis(Double kiekis) {
        atnaujintiPiniguLikutiDB(kiekis);
    }

    private static void atnaujintiPiniguLikutiDB(Double kiekis) {
        String sql = "UPDATE piniguLikutis SET Likutis = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, kiekis);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void spausdinkPiniguLikuti() {
        System.out.println("Pinigu likutis parduotuveje siuo metu: " + PiniguLikutis.nuskaitykPiniguLikutiDB() + " EUR");
        System.out.println();
    }
}
