package fee_Report_Software;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


	
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/FeeReportDB";
    private static final String USER = "root"; // Change as per your MySQL username
    private static final String PASSWORD = "Varshini2004#"; // Change as per your MySQL password

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}

