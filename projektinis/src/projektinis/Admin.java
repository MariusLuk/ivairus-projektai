package projektinis;

import java.sql.*;

public class Admin extends Vartotojas {

    public static String password;
    public static String url = "jdbc:sqlite:projektinioDuomenuBaze.db";

    public Admin(String nickName){
        this.nickName = nickName;
    }

    public String getPassword(){

        String sql = "SELECT * FROM Vartotojas WHERE Vardas = ?";

        try (Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nickName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                password  = rs.getString("Slaptazodis");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return password;
    }

    public void setPassword(String slaptazodis){
        String sql = "UPDATE Vartotojas SET Slaptazodis = ?" + "WHERE Vardas = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, slaptazodis);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
