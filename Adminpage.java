import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Adminpage 
{
	public static void adpage(Scanner scan)
	try
	{
		      Class.forName("oracle.jdbc.driver.OracleDriver");
		      Connection connect=DriverManager.getConnection("jdbc:oracle:thin::testuser/testpass@localhost");
		      Statement stmt=connect.createStatement();
		      
		      boolean done = true;
		      System.out.println("Admin Page");
		      
		      
		    	  while(done)
		          {
		    		   System.out.println();
		        	   System.out.println("********************************");
		               System.out.println("Please select a query below!");
		               System.out.println("1. Enter New Model");
		               System.out.println("2. Update Price ");
		               System.out.println("3. Delete Model");
		               System.out.println("4. Logout");
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
		            	   val = 5;
		               }
		               if(val == -1 || val < 1 || val > 5)
		               {
		                   System.out.println("Incorrect Input!");
		                   System.out.println("Please enter a number from 1-4");
		               }
		               
		               switch(val)
		               {
		               		case 1:
		               			String gift_Id = "";
				                String Name = "";
				                Integer Price = 0;
				 		    	System.out.println("Enter Gift ID");
				 		    	gift_Id = scan.nextLine();
				 		    	System.out.println("Enter Model Name");
				 		    	Name = scan.nextLine();
				 		    	System.out.println("Enter Price");
				 		    	Price = scan.nextInt();
				 		    	String sql = "INSERT INTO gifts VALUES " + "( '"+ gift_Id + "' , " + "'" + Name + "', " + "" + Price + ")";
						    	stmt.execute(sql);
						    	System.out.println("Successfully added to database!");
		               		case 2:
		               			String gift_Id1 = "";  
				                Integer Price1 = 0;
				                System.out.println("Enter Gift ID");
				                gift_Id1 = scan.nextLine();
				 		    	System.out.println("Enter New Price");
				 		    	Price1 = scan.nextInt();
				 		    	String sql1 = "UPDATE gifts SET PRICE = " + Price1 +  " where ID = '" + gift_Id1 + "'";
			                	PreparedStatement stmt1 = connect.prepareStatement(sql1);
			                	stmt1.executeUpdate();
						    	System.out.println("Successfully Updated Price!");
//		               		case 3:
//		               			String Model = "";  
//				                Integer Avail = 0;
//				                System.out.println("Enter Model No");
//				 		    	Model = scan.nextLine();
//				 		    	System.out.println("Enter New Availablity");
//				 		    	Avail = scan.nextInt();
//				 		    	String sql1 = "UPDATE wm SET wm.Availablity = ? where wm.Title=?";
//			                	PreparedStatement stmt1 = conn.prepareStatement(sql1);
//			                	stmt1.setInt(1, Avail);
//			                	stmt1.setString(2, Model);
//			                	stmt1.executeUpdate();
//						    	System.out.println("Success!");
		               		case 3:
		               			String gift_Id2 = "";  
				                System.out.println("Enter id of gift you want to delete");
				                gift_Id2 = scan.nextLine();
				 		    	String sqlDel = "DELETE FROM gifts WHERE ID = '" + gift_Id2 + "'";
			                    stmt.execute(sqlDel);
			                    System.out.println("Gift successfully deleted!");
		               		case 4:
		               			done= false;
		   }
		} 
	connect.close();
	}
	catch(SQLException | ClassNotFoundException e)
	{
		System.out.println("Database connectivity error");
		System.out.println(e);
	}
}
