import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class user {
	
	public static void purchase(Scanner sc,Integer Id1)
	try
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connect=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","Nishit","hello123");
		Statement stmt=connect.createStatement();
		
		bool done=false;
		while(!done)
			
		{
			System.out.println("1. List of all Gifts");
            System.out.println("2. List of Details of a particular Gift ID ");
            System.out.println("3. Purchase Gift");
            System.out.println("4. Check Your Orders");
            System.out.println("5. Dashboard");
            System.out.println("6. Logout");
            
            String getValue;
			
			getValue= sc.nextLine();
			
			int choice;
			try
			{
				
				choice=Integer.parseInt(getValue);
				
				
			}
			catch (Exception e)
			{
				choice=7;
			}
			
			switch(choice)
			{
				case 1:
					String query="SELECT * from gifts";
					try
					{
						ResultSet values=stmt.executeQuery(query);
						System.out.println("ID NAME PRICE")
						while(values.next())
						{
							Integer id=values.getInt("ID");
							Integer cost=values.getInt("PRICE");
							String name=values.getString("NAME");
							System.out.println(id+" "+name+" "+cost);
							
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
						System.out.println("Data could not be retrieved");
					}
					break;
				case 2:
					String val ;
					System.out.println("Enter the product ID");
					val= sc.nextLine();
					int ID;
					try
					{
						ID=Integer.parseInt(val);
						
					}
					catch (Exception e)
					{
						System.out.println(e);
						System.out.println("Invalid choice. Please enter a valid product ID");
						break;
					}
					String query = "SELECT * FROM t1 WHERE ID IS"+ID;
					
					ResultSet values= stmt.executeQuery(query);
					if(values.next())
					{
						Integer id=values.getInt("ID");
						Integer cost=values.getInt("PRICE");
						String name=values.getString("NAME");
						System.out.println(id+" "+name+" "+cost);
						
					}
					else
					{
						System.out.println("There is no product with "+ID+" ID");
						
					}
					break;
				case 3:
					System.out.println("Enter the ID of the product you want to purchase");
					String val ;
					val= sc.nextLine();
					Integer ID;
					try
					{
						ID=Integer.parseInt(val);
						
					}
					catch (Exception e)
					{
						System.out.println(e);
						System.out.println("Invalid choice. Please enter a valid product ID");
						break;
					}
					String quant;
					System.out.println("Enter the quantity");
					quant=sc.nextLine();
					Integer quantity;
					try 
					{
						quantity= Integer.parseInt(quant);
						
					}
					catch(Exception e)
					{
						System.out.println("Invalid quantity. Please Enter a valid quantity.");
						break;
					}
					
					String query="SELECT * from t1 WHERE ID="+ID;
					ResultSet values=stmt.executeQuery(query);
					
					if(values.next())
					{

						Integer cost=values.getInt("PRICE");
						System.out.println("Your total cost is:"+cost*quantity);
						System.out.println("1. Confirm Order\n2. Cancel Order");
						String go= sc.nextLine();
						Integer order;
						try
						{
							order=Integer.parseInt(go);
							
						}
						catch(Exception e)
						{
							System.out.println("Invalid choice");
							break;
						}
						if(order==1)
						{
							System.out.println("Your order has been placed successfully");
							// INSERT ORDER INTO THE BOOKING TABLE
						}
						else
						{
							System.out.println("Order has been cancelled");
						}
						
					}
					else
					{
						System.out.println("No such product available");
					}
					
				case 4:
					// SAKSHAM MAHAJAN KA KAAM HAI YEH.
					
					
					
					
			
			}
		}
		
		connect.close();
	}
	catch(SQLException | ClassNotFoundException e)
	{
		System.out.println("Database connectivity error");
		System.out.println(e);
	}
	

};
