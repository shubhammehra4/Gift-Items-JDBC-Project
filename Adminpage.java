import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Adminpage 
{
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    static final String USER = "Nishit";
    static final String PASS = "hello123";
    
    String username = "";
    String password = "";
    
	public static void adpage(Scanner scan) 
	{
		Connection conn = null;
        Statement stmt = null;
        try
        {
        	Class.forName (JDBC_DRIVER);
        	
            // Connecting to a Database...
            conn = DriverManager.getConnection (DB_URL, USER, PASS);
            // Creating statement...
            stmt = conn.createStatement();
            
		    boolean done = true;
		    System.out.println("\t\tAdmin Page");
		    
		    try
		    {
		    	while(done)
		    	{
		    		System.out.println();
		    		System.out.println("********************************");
		    		System.out.println("Please select a query below!");
		    		System.out.println("1. Enter New Model");
		    		System.out.println("2. Update Price ");
		    		System.out.println("3. Update Availability");
		    		System.out.println("4. Delete Model");
		    		System.out.println("5. Logout");
		    		System.out.println("********************************");
		    		System.out.println();
		               
		    		String input = scan.nextLine();
		    		int val;
		               
		    		try
		    		{
		    			val = Integer.parseInt(input);
		    		}
		    		catch(Exception e)
		    		{
		    			val = 6;
		    		}
		    		if(val == -1 || val < 1 || val > 6)
		    		{
		    			System.out.println("Incorrect Input!");
		    			System.out.println("Please enter a number from 1-5");
		    		}
		               
		               switch(val)
		               {
		               		case 1:
		               			String gift_Id = "";
				                String Name = "";
				                Integer Price = 0;
				                Integer Avail = 0;
				 		    	System.out.println("Enter Gift ID");
				 		    	gift_Id = scan.nextLine();
				 		    	System.out.println("Enter Model Name");
				 		    	Name = scan.nextLine();
				 		    	System.out.println("Enter Price");
				 		    	Price = scan.nextInt();
				 		    	System.out.println("Enter Availability");
				 		    	Avail = scan.nextInt();
				 		    	String sql = "INSERT INTO GiftItems VALUES " + "( '"+ gift_Id + "' , " + "'" + Name + "', " + Price + "," + Avail + ")";
						    	stmt.execute(sql);
						    	System.out.println("Successfully data added to database!");
						    	break;
		               		case 2:
		               			String gift_Id1 = "";  
				                Integer Price1 = 0;
				                System.out.println("Enter Gift ID");
				                gift_Id1 = scan.nextLine();
				 		    	System.out.println("Enter New Price");
				 		    	Price1 = scan.nextInt();
				 		    	String sql1 = "UPDATE GiftItems SET PRICE = " + Price1 +  " where ID = '" + gift_Id1 + "'";
			                	PreparedStatement stmt1 = conn.prepareStatement(sql1);
			                	stmt1.executeUpdate();
						    	System.out.println("Successfully Updated Price!");
						    	break;
		               		case 3:
		               			String gift_Id2 = "";  
				                Integer Avail1 = 0;
				                System.out.println("Enter Gift Id");
				                gift_Id2 = scan.nextLine();
				 		    	System.out.println("Enter New Availablity");
				 		    	Avail1 = scan.nextInt();
				 		    	String sql2 = "UPDATE GiftItems SET AVAILIBILTY = ? where Id = ?";
			                	PreparedStatement stmt2 = conn.prepareStatement(sql2);
			                	stmt2.setInt(1, Avail1);
			                	stmt2.setString(2, gift_Id2);
			                	stmt2.executeUpdate();
						    	System.out.println("Successfully Updated availibilty!");
						    	break;
		               		case 4:
		               			String gift_Id3 = "";  
				                System.out.println("Enter id of gift you want to delete");
				                gift_Id3 = scan.nextLine();
				 		    	String sqlDel = "DELETE FROM GiftItems WHERE ID = '" + gift_Id3 + "'";
			                    stmt.execute(sqlDel);
			                    System.out.println("Gift successfully deleted!");
			                    break;
		               		case 5:
		               			done= false;
		               			break;
		               }
		               
		    	}
		    } 
		    catch (Exception e)
		    {
		    	System.out.println("Error");
		    }
        }
		catch(SQLException | ClassNotFoundException e)
		{
			System.out.println("Error");
			e.printStackTrace();
		}
		finally
		{
		      try
		      {
		         if(stmt!=null)
		            conn.close();
		      }
		      catch(SQLException se)
		      {
		    	  ;
		      }
		      try
		      {
		         if(conn!=null) conn.close();
		      }
		      catch(SQLException se)
		      {
		         se.printStackTrace();		         
		         System.out.println("Error closing connection to the database!");
		      }
		   }
		return;
	}

};