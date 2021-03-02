import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Register {
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    static final String USER = "Nishit";
    static final String PASS = "hello123";

    String username = "";
    String password = "";
    String email = "";
    String name = "";

    public void execute (Scanner scan) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName (JDBC_DRIVER);
            // Connecting to a Database...
            conn = DriverManager.getConnection (DB_URL, USER, PASS);
            // Creating statement...
            stmt = conn.createStatement();
            System.out.println();
            System.out.println ("################################");
            System.out.println ("\t\t\tRegister");
            System.out.println();

            try {
                System.out.print ("Name: ");
                name = scan.nextLine();
                System.out.print ("Username: ");
                username = scan.nextLine();
                System.out.print ("Email: ");
                email = scan.nextLine();
                System.out.print ("Password: ");
                password = scan.nextLine();
                String sql = "INSERT INTO user VALUES " + "(" + username + ", " + "'" + password + "', " + "'" + name
                             + "', " + "'" + email + "')";
                stmt.execute (sql);
                // System.out.print(name);
                // System.out.print(password);
                System.out.println ("Successfully Registered!");
                System.out.println ("Redirecting");
            } catch (Exception e) {
                System.out.println ("Error!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {
            }

            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
                System.out.println ("Error closing connection to the database!");
            }
        }
    }
}