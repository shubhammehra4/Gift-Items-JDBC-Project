import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Random;


// Java-->Driver-->Mysql
public class Cart {
	static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
	static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
	static final String USER = "Nishit";
	static final String PASS = "hello123";


	public static void fun(Scanner scan, Integer Id1) {
		Connection conn = null;
		Statement stmt = null;

		ResultSet r = null, res = null, tp = null;
		try {
			//STEP 1: Register JDBC driver
			Class.forName(JDBC_DRIVER);

			//STEP 2: Open a connection
			//System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Connected database successfully...");

			//STEP 3: Execute a query
			// System.out.println("Creating statement...");
			stmt = conn.createStatement();

			//Welcome user!
			System.out.println("Welcome to the gift Purchasing Program!");

			//Initialize our querying variable
			boolean querying = true;

			try  {
				while (querying) {
					//Print the menu
					System.out.println();
					System.out.println("********************************");
					System.out.println("Please select a query below!");
					//2 available options
					System.out.println("1. Check Your Orders");
					System.out.println("2. Check Out Cart");
					System.out.println("3. Return to dashboard");
					System.out.println("********************************");
					System.out.println();

					System.out.println("Enter Your Choice");
					String input = scan.nextLine();
					int answer;
					try {
						answer = Integer.valueOf(input);
					} catch (Exception e) {
						answer = -1;
					}

					switch (answer) {
					case 1:
						try {
							String resq = "Select * from usercart where CUSTOMERID=" + Id1;
							System.out.println("sfsdgsdg" + resq);
							r = stmt.executeQuery(resq);
							int cnt = 0;
							System.out.println("GIFTID QUANTITY PRICE");
							while (r.next()) 
							{
								Integer giftID = r.getInt("GIFTID");
								Integer quant = r.getInt("QUANTITY");
								//String giftq = "Select * from giftitems where ID=" + giftID;
								//System.out.println("gifftttttttttttt" + giftq);

								Integer price = -1;
//								try {
//									tp = stmt.executeQuery(giftq);
////			               					try
////			               					{
//									if (tp.next()) {
//										//price = quant * tp.getInt("PRICE");
//									}
//									//}
////			               					catch(Exception e)
////			               					{
////			               						System.out.println("GIFT ID not retreived");
////			               						System.out.println(e);
////			               					}
//								}
//
//								catch (Exception e) {
//									System.out.println("Databse error");
//									System.out.println(e);
//								}
								System.out.println(giftID + " " + quant + " " + price);

								cnt++;
							}

							if (cnt == 0) {
								System.out.println("YOUR CART IS EMPTY");
							}
						} catch (Exception e) {
							System.out.println("Databse error1");
							e.printStackTrace();

						}
						
						r.close();

						break;
					case 2:
						String add;
						System.out.println("Enter your address for delivery");
						add = scan.nextLine();
						System.out.println("Are you sure you want to check out your cart\n Enter 1 to confirm\n Enter 0 to cancel");
						String getconfirm;
						getconfirm = scan.nextLine();
						Integer confirm = -1;
						try {
							confirm = Integer.parseInt(getconfirm);
						} catch (Exception e) {
							System.out.println("Invalid Choice");
						}
						if (confirm == 1) {
							try {
								String query = "Select * from usercart where CUSTOMERID=" + Id1;
								res = stmt.executeQuery(query);
								while (res.next()) {
									Random rand = new Random();
									Integer giftID = res.getInt("GIFTID");
									Integer bookingid = rand.nextInt (1000000000) + 1;
									String ins = "Insert into bookings values(" + bookingid + "," + Id1 + "," + giftID + "," + "'" + add + "'" + ")";
									System.out.println("insert:" + ins);
									stmt.executeUpdate(ins);


								}

								//String delf = "Delete from usercart where CUSTOMERID=" + Id1;
								//stmt.executeUpdate(delf);

							} catch (Exception e) {

								System.out.println("Unable to access cart");
								System.out.println(e);
								e.printStackTrace();
							}
						}
						break;
					default:
						querying = false;
						break;


					}


				}

			}

			catch (Exception e) {
				System.out.println(e);
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("sdgsH");
			e.printStackTrace();
		} finally {
			//finally block used to close resources
			try {
				if (res != null)
					res.close();
				if (r != null)
					r.close();
				if (tp != null)
					tp.close();
			} catch (Exception e) {
				System.out.println("error closing" + e);
			}
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}// do nothing
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();

				//Inform user of error
				System.out.println("Error closing connection to the database!");
			}//end finally try
		}//end try
		System.out.println("Goodbye!");

	}

//	public void fun2(Scanner scan, Integer Id1)
//	{
//		Connection conn = null;
//		Statement stmt = null;
//
//		try{
//		      //STEP 1: Register JDBC driver
//		      Class.forName(JDBC_DRIVER);
//
//		      //STEP 2: Open a connection
//		      System.out.println("Connecting to a selected database...");
//		      conn = DriverManager.getConnection(DB_URL, USER, PASS);
//		      System.out.println("Connected database successfully...");
//
//		      //STEP 3: Execute a query
//		      System.out.println("Creating statement...");
//		      stmt = conn.createStatement();
//
//
//		      //Welcome user!
//		      System.out.println("Welcome to the gift Purchasing Program!");
//
//		      //Initialize our querying variable
//		      boolean querying = true;
//
//		      try  {
//		           while(querying)
//		           {
//		               //Print the menu
//		        	   System.out.println();
//		        	   System.out.println("********************************");
//		               System.out.println("Please select a query below!");
//		               //1 available option
//		               System.out.println("1. Confirm Order");
//		               System.out.println("********************************");
//		               System.out.println();
//
//		               System.out.println("Enter Your Choice");
//		               String input = scan.nextLine();
//		               int answer;
//		               try{
//		            	   answer=Integer.valueOf(input);
//		               }
//		               catch(Exception e)
//		               {
//		            	   answer=-1;
//		               }
//		               if(answer == -1 || answer < 1 || answer > 1)
//		               {
//		                   //User entered incorrect input
//		                   System.out.println("Incorrect Input!");
//		                   System.out.println("Please enter a number 1-1");
//		               }
//
//		                Random rand = new Random();
//                		bookingid = rand.nextInt (100000) + 1; // [1-100000]
//
//
//		               // cart db-> search(will return array) -> bookings(with id and address)
//		               // user id, booking id, address,
//
//		               else if (answer==1)
//		               {
//		            	   System.out.println("Please enter the gift");
//		                   input = scan.nextLine();
//		                   String sql = "SELECT Availablity FROM wm WHERE LOWER(Title) LIKE LOWER('%" + input + "%')";
//		                   ResultSet rs = stmt.executeQuery(sql);
//
//		                   if(rs.next())
//		                   {
//			                	   String sql1 = "INSERT INTO wm SET wm.Availablity = ? where wm.Title=?";
//			                	   PreparedStatement stmt1 = conn.prepareStatement(sql1);
//			                	   stmt1.setInt(1, val);
//			                	   stmt1.setString(2, input);
//			                	   stmt1.executeUpdate();
//			                	   System.out.println("Enter your Address");
//			                	   String addr= scan.nextLine();
//			                	   String sql2 = "INSERT INTO book Values" + "("+ Id1 + ", " + "'" + input + "', " + "'" + addr + "')";
//			                	   stmt.execute(sql2);
//
//			                	   System.out.println("Order Confirmed");
//
//		                   }
//
//		                   else
//		                   {
//		                	   System.out.println("This gift item does not exist...");
//		                   }
//
//		                   rs.close();
//		               }
//
//
//
//
//		                   catch(Exception e)
//		                   {
//		                        e.printStackTrace();
//		                        System.out.println("Error! Could Not get result from the Database");
//		                   }
//
//		               }
//
//
//
//
//		           }
//
//		      }
//		      catch (Exception e)
//		      {
//		    	  System.out.println(e);
//		      }
//		}
//		catch(SQLException | ClassNotFoundException e)
//		{
//			e.printStackTrace();
//		}
//		finally{
//		      //finally block used to close resources
//		      try{
//		         if(stmt!=null)
//		            conn.close();
//		      }catch(SQLException se){
//		      }// do nothing
//		      try{
//		         if(conn!=null)
//		            conn.close();
//		      }catch(SQLException se){
//		         se.printStackTrace();
//
//		         //Inform user of error
//		         System.out.println("Error closing connection to the database!");
//		      }//end finally try
//		   }//end try
//		   System.out.println("Goodbye!");
//	}

};