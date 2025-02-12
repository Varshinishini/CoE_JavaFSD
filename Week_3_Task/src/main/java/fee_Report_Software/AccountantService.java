package fee_Report_Software;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class AccountantService {

    
    public static void addStudent(String name, String email, String course, double fee, double paid, String address, String phone) {
        try (Connection conn = DBConnection.getConnection()) {
            double due = fee - paid;
            String query = "INSERT INTO student (name, email, course, fee, paid, due, address, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, course);
            pstmt.setDouble(4, fee);
            pstmt.setDouble(5, paid);
            pstmt.setDouble(6, due);
            pstmt.setString(7, address);
            pstmt.setString(8, phone);
            pstmt.executeUpdate();
            System.out.println("Student added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static void viewStudents() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM student";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n List of Students:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Name: " + rs.getString("name") +
                                   ", Email: " + rs.getString("email") +
                                   ", Course: " + rs.getString("course") +
                                   ", Fee: " + rs.getDouble("fee") +
                                   ", Paid: " + rs.getDouble("paid") +
                                   ", Due: " + rs.getDouble("due") +
                                   ", Address: " + rs.getString("address") +
                                   ", Phone: " + rs.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static void editStudent(int id, String name, String email, String course, double fee, double paid, String address, String phone) {
        try (Connection conn = DBConnection.getConnection()) {
            double due = fee - paid;
            String query = "UPDATE student SET name=?, email=?, course=?, fee=?, paid=?, due=?, address=?, phone=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, course);
            pstmt.setDouble(4, fee);
            pstmt.setDouble(5, paid);
            pstmt.setDouble(6, due);
            pstmt.setString(7, address);
            pstmt.setString(8, phone);
            pstmt.setInt(9, id);
            int updated = pstmt.executeUpdate();
            if (updated > 0) {
                System.out.println("Student details updated successfully!");
            } else {
                System.out.println("Student ID not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete student
    public static void deleteStudent(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "DELETE FROM student WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            int deleted = pstmt.executeUpdate();
            if (deleted > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student ID not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static void checkDueFee() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM student WHERE due > 0";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n Students with Due Fees:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Name: " + rs.getString("name") +
                                   ", Course: " + rs.getString("course") +
                                   ", Due Fee: " + rs.getDouble("due"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

