import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Login {
    // static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    // static final String DB_URL = "dburl";
    // static final String USER = "user";
    // static final String PASS = "pass";

    String username = "";
    String password = "";

    public void execute (Scanner scan) {
        // Connection conn = null;
        // Statement stmt = null;
        try {
            // Class.forName(JDBC_DRIVER);
            System.out.println ("Connecting to a Database...");
            // conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println ("Successfully Connected to DB");
            // System.out.println("Creating statement...");
            // stmt = conn.createStatement();
            System.out.println();
            System.out.println ("#############################");
            System.out.println ("\t\t\tLogin");
            System.out.println();

            try {
                System.out.print ("Username: ");
                username = scan.nextLine();
                System.out.print ("Password: ");
                password = scan.nextLine();
                System.out.print ("Welcome ");
                System.out.println (username);
                // String sql = "SELECT Password FROM USER WHERE Username=" + username + "";
                // try (ResultSet res = stmt.executeQuery(sql)) {
                // while (res.next()) {
                // String originalPassword = res.getString("Password");
                // if (password.equals(originalPassword)) {
                // System.out.println("Successful Login and redirecting to details page");
                // // User U =new User();
                // // U.fun(scan, idv);
                // } else {
                // System.out.println("Incorrect Password");
                // }
                // }
                // res.close();
                // } catch (Exception e) {
                // e.printStackTrace();
                // System.out.println("Username is not registered");
                // }
            } catch (Exception e) {
                System.out.println ("Error");
            }

            // } catch (SQLException | ClassNotFoundException e) {
        } catch (Exception e) {
            System.out.println ("Error");
            // e.printStackTrace();
        } finally {
            System.out.println ("Login Complete");
            // try {
            // if (stmt != null)
            // conn.close();
            // } catch (SQLException se) {
            // }
            // try {
            // if (conn != null)
            // conn.close();
            // } catch (SQLException se) {
            // se.printStackTrace();
            // System.out.println("Error closing connection to the database!");
            // }
        }
    }
}