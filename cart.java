
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Random;


// Java-->Driver-->Mysql
public class Cart 
{
	
	public void fun(Scanner scan, Integer Id1)
	{
		Connection conn = null;
		Statement stmt = null;
		try{
		      //STEP 1: Register JDBC driver
		      Class.forName(JDBC_DRIVER);

		      //STEP 2: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      
		      //STEP 3: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      
		      
		      //Welcome user!
		      System.out.println("Welcome to the gift Purchasing Program!");
		      
		      //Initialize our querying variable
		      boolean querying = true;
		      
		      try  {
		           while(querying)
		           {
		               //Print the menu
		        	   System.out.println();
		        	   System.out.println("********************************");
		               System.out.println("Please select a query below!");
		               //2 available options
		               System.out.println("1. Check Your Orders");
		               System.out.println("2. Dashboard");	
		               System.out.println("********************************");
		               System.out.println();
		               
		               System.out.println("Enter Your Choice");
		               String input = scan.nextLine();
		               int answer;
		               try{
		            	   answer=Integer.valueOf(input);
		               }
		               catch(Exception e)
		               {
		            	   answer=-1;
		               }
		               if(answer == -1 || answer < 1 || answer > 2)
		               {
		                   //User entered incorrect input
		                   System.out.println("Incorrect Input!");
		                   System.out.println("Please enter a number 1-2");
		               }
		                            
		               
		               else if (answer == 1)
		               {
		            	   String sql = "SELECT Model FROM book where Id = "+Id1+"";
		            	   
		                   try (ResultSet rs = stmt.executeQuery(sql)) {
		                	   
		                	   System.out.println("Model No");
		                       while(rs.next()){
		                           //Retrieve by column name
		                           String title = rs.getString("Model");
		                           
		                           //Display values
		                           System.out.println(title);
		                           
		                       }
		                       
		                       //Close the result set
		                       rs.close();
		                   }
		                   
		                   catch(Exception e) 
		                   {
		                        e.printStackTrace();
		                        System.out.println("Error! Could Not get result from the Database");
		                   }
		                   
		               }
		               
		               else if (answer == 2)
		               {
		            	   System.out.println("Redirecting to Dashboard");
		            	   	            
		               }
		       
		               
		           }
	
		      }

		      catch (Exception e)
		      {
		    	  System.out.println(e);
		      }
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		         
		         //Inform user of error
		         System.out.println("Error closing connection to the database!");
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");

	}

	public void fun2(Scanner scan, Integer Id1)
	{
		Connection conn = null;
		Statement stmt = null;

		try{
		      //STEP 1: Register JDBC driver
		      Class.forName(JDBC_DRIVER);

		      //STEP 2: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
		      System.out.println("Connected database successfully...");
		      
		      //STEP 3: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      
		      
		      //Welcome user!
		      System.out.println("Welcome to the gift Purchasing Program!");
		      
		      //Initialize our querying variable
		      boolean querying = true;
		      
		      try  {
		           while(querying)
		           {
		               //Print the menu
		        	   System.out.println();
		        	   System.out.println("********************************");
		               System.out.println("Please select a query below!");
		               //1 available option
		               System.out.println("1. Confirm Order");
		               System.out.println("********************************");
		               System.out.println();
		               
		               System.out.println("Enter Your Choice");
		               String input = scan.nextLine();
		               int answer;
		               try{
		            	   answer=Integer.valueOf(input);
		               }
		               catch(Exception e)
		               {
		            	   answer=-1;
		               }
		               if(answer == -1 || answer < 1 || answer > 1)
		               {
		                   //User entered incorrect input
		                   System.out.println("Incorrect Input!");
		                   System.out.println("Please enter a number 1-1");
		               }

		                Random rand = new Random();
                		bookingid = rand.nextInt (100000) + 1; // [1-100000]

                		
		               // cart db-> search(will return array) -> bookings(with id and address)
		               // user id, booking id, address, 
		                 
		               else if (answer==1)
		               {
		            	   System.out.println("Please enter the gift");
		                   input = scan.nextLine();
		                   String sql = "SELECT Availablity FROM wm WHERE LOWER(Title) LIKE LOWER('%" + input + "%')";
		                   ResultSet rs = stmt.executeQuery(sql);
		                 
		                   if(rs.next())
		                   {              	   
			                	   String sql1 = "INSERT INTO wm SET wm.Availablity = ? where wm.Title=?";
			                	   PreparedStatement stmt1 = conn.prepareStatement(sql1);
			                	   stmt1.setInt(1, val);
			                	   stmt1.setString(2, input);
			                	   stmt1.executeUpdate();
			                	   System.out.println("Enter your Address");
			                	   String addr= scan.nextLine();
			                	   String sql2 = "INSERT INTO book Values" + "("+ Id1 + ", " + "'" + input + "', " + "'" + addr + "')";
			                	   stmt.execute(sql2);
			     	
			                	   System.out.println("Order Confirmed");
			                   
		                   }
		                   
		                   else
		                   {
		                	   System.out.println("This gift item does not exist..."); 
		                   }

		                   rs.close();
		               }
		               
		               
		  
		                   
		                   catch(Exception e) 
		                   {
		                        e.printStackTrace();
		                        System.out.println("Error! Could Not get result from the Database");
		                   }
		                   
		               }
		               
		  
		               
		               
		           }
	
		      }
		      catch (Exception e)
		      {
		    	  System.out.println(e);
		      }
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		         
		         //Inform user of error
		         System.out.println("Error closing connection to the database!");
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
	}

}
