package projektinis;

import java.sql.*;

public class UzsakymuAtaskaita {

    public static void ikelkUzsakymaToAtaskaita(int pirkejoID, String pirkejoPavadinimas, String pirkejoEmail,
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

                System.out.println(
                        apdorotiReiksme(s, rs.getString("pirkejoID")) + "\t"
                                + apdorotiReiksme(s, rs.getString("pirkejoPavadinimas")) + "\t"
                                + apdorotiReiksme(s, rs.getString("pirkejoEmail")) + "\t"
                                + apdorotiReiksme(s, rs.getString("pirkejoAdresas")) + "\t"
                                + apdorotiReiksme(s, rs.getString("krepselioKaina")) + "\t"
                                + apdorotiReiksme(s, rs.getString("uzsakymoDataLaikas"))
                );
            }
            System.out.println("________________________________________________________________________________________________________________________________________________________________");
            System.out.println();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    private static String apdorotiReiksme(String s, String tekstas) {
        return tekstas + s.substring(tekstas.length() + 1);
    }
}
