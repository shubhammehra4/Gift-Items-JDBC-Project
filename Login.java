import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Login {
    static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    static final String USER = "Nishit";
    static final String PASS = "hello123";

    String username = "";
    String password = "";

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
            System.out.println ("#############################");
            System.out.println ("\t\t\tLogin");
            System.out.println();

            try {
                System.out.print ("Username: ");
                username = scan.nextLine();
                System.out.print ("Password: ");
                password = scan.nextLine();
                String sql = "SELECT Password FROM Customers WHERE Username='" + username + "'";

                try (ResultSet res = stmt.executeQuery (sql) ) {
                    while (res.next() ) {
                        String originalPassword = res.getString ("Password");

                        if (password.equals (originalPassword) ) {
                            System.out.println ("Successful Login and Redirecting");
                            // User U =new User();
                            // U.fun(scan, idv);
                        } else {
                            System.out.println ("Incorrect Password");
                        }
                    }

                    res.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println ("Username is not registered");
                }
            } catch (Exception e) {
                System.out.println ("Error");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println ("Error");
            e.printStackTrace();
        } finally {
            System.out.println ("Login Complete");

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