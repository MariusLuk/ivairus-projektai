package projektinis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Projektinis {
    static Scanner scanner = new Scanner(System.in);

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
            System.out.println("_______________________________________________________________________________________________________________________________________________________________");
            System.out.println();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String apdorotiReiksme(String s, String tekstas) {
        return tekstas + s.substring(tekstas.length() + 1);
    }

    public static void main(String[] args) {
        Navigacija.pradzia();
    }

}
