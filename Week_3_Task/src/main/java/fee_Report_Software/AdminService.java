package fee_Report_Software;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
//import com.feeapp.db.DBConnection;

public class AdminService {
    
    // Add new accountant
    public static void addAccountant(String name, String email, String phone, String password) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO accountant (name, email, phone, password) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, password);
            pstmt.executeUpdate();
            System.out.println("Accountant added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View existing accountants
    public static void viewAccountants() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM accountant";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n List of Accountants:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Name: " + rs.getString("name") +
                                   ", Email: " + rs.getString("email") +
                                   ", Phone: " + rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Edit accountant details
    public static void editAccountant(int id, String name, String email, String phone, String password) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "UPDATE accountant SET name=?, email=?, phone=?, password=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            pstmt.setString(4, password);
            pstmt.setInt(5, id);
            int updated = pstmt.executeUpdate();
            if (updated > 0) {
                System.out.println("Accountant details updated successfully!");
            } else {
                System.out.println("Accountant ID not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete accountant
    public static void deleteAccountant(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "DELETE FROM accountant WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            int deleted = pstmt.executeUpdate();
            if (deleted > 0) {
                System.out.println("Accountant deleted successfully!");
            } else {
                System.out.println("Accountant ID not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}