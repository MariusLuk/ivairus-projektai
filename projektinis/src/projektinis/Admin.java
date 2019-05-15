package projektinis;

import java.sql.*;

public class Admin extends Vartotojas {

    private String password;
    private String url = "jdbc:sqlite:projektinioDuomenuBaze.db";

    public Admin(String adminVardas) {
        this.nickName = adminVardas;
    }

    public String getPassword() {
        String sql = "SELECT * FROM vartotojas WHERE Vardas = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nickName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                password = rs.getString("Slaptazodis");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return password;
    }

    public void setPassword(String slaptazodis, String adminVardas) {
        String sql = "UPDATE vartotojas SET Slaptazodis = ?" + "WHERE Vardas = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, slaptazodis);
            pstmt.setString(2, adminVardas);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
