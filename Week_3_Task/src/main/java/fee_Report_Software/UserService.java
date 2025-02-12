package fee_Report_Software;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import com.feeapp.db.DBConnection;

public class UserService {
	
	public static void registerAdmin(String username, String password) {
	    try (Connection conn = DBConnection.getConnection()) {
	        String query = "INSERT INTO admin (username, password) VALUES (?, ?)";
	        PreparedStatement pstmt = conn.prepareStatement(query);
	        pstmt.setString(1, username);
	        pstmt.setString(2, password);
	        pstmt.executeUpdate();
	        System.out.println("Admin registered successfully!");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	
    public static boolean validateAdmin(String username, String password) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM admin WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if a record is found
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean validateAccountant(String email, String password) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM accountant WHERE email = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Returns true if a record is found
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
