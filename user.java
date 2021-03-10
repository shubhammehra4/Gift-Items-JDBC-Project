import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class user {

	public static void purchase(Scanner sc, Integer Id1) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Nishit", "hello123");
			Statement stmt = connect.createStatement();

			boolean done = false;
			while (!done)

			{
				System.out.println("1. List of all Gifts");
				System.out.println("2. List of Details of a particular Gift ID ");
				System.out.println("3. Add Gifts to cart");
				System.out.println("4. Check Your Cart or View your Cart.");
				System.out.println("5. Logout");

				String getValue;

				getValue = sc.nextLine();

				int choice;
				try {

					choice = Integer.parseInt(getValue);


				} catch (Exception e) {
					choice = 7;
				}

				switch (choice) {
				case 1:
					String query = "SELECT * from GiftItems";
					try {
						ResultSet values = stmt.executeQuery(query);
						System.out.println("ID NAME PRICE");
						while (values.next()) {
							Integer id = values.getInt("ID");
							Integer cost = values.getInt("PRICE");
							String name = values.getString("NAME");
							System.out.println(id + " " + name + " " + cost);

						}
						values.close();
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("Data could not be retrieved");
					}
					break;
				case 2:
					String val ;
					System.out.println("Enter the product ID");
					val = sc.nextLine();
					int ID;
					try {
						ID = Integer.parseInt(val);

					} catch (Exception e) {
						System.out.println(e);
						System.out.println("Invalid choice. Please enter a valid product ID");
						break;
					}
					String query1 = "SELECT * FROM GiftItems WHERE ID=" + ID;


					ResultSet values = stmt.executeQuery(query1);
					if (values.next()) {
						System.out.println("ID NAME PRICE AVAILABILITY");
						Integer id = values.getInt("ID");
						Integer cost = values.getInt("PRICE");
						String name = values.getString("NAME");
						Integer avail = values.getInt("AVAILABILITY");
						System.out.println(id + " " + name + " " + cost + " " + avail);

					} else {
						System.out.println("There is no product with " + ID + " ID");

					}
					values.close();
					break;
				case 3:
					// CART.JAVA call karlena

					System.out.println("Enter the ID of the product you want to add to cart");
					String val1 ;
					val1 = sc.nextLine();
					Integer ID1;
					try {
						ID1 = Integer.parseInt(val1);

					} catch (Exception e) {
						System.out.println(e);
						System.out.println("Invalid choice. Please enter a valid product ID");
						break;
					}
					String quant;
					System.out.println("Enter the quantity");
					quant = sc.nextLine();
					Integer quantity;
					try {
						quantity = Integer.parseInt(quant);

					} catch (Exception e) {
						System.out.println("Invalid quantity. Please Enter a valid quantity.");
						break;
					}

					String query2 = "SELECT * from GiftItems WHERE ID=" + ID1;
					ResultSet values1 = stmt.executeQuery(query2);

					if (values1.next()) {

						Integer cost = values1.getInt("PRICE");
						Integer avail = values1.getInt("AVAILABILITY");
						if (avail < quantity) {
							System.out.println("There are only" + avail + "items available for gift ID:" + ID1);
							continue;
						}

						System.out.println("Your total cost is:" + cost * quantity);
						System.out.println("1. Confirm Add Cart\n2. Cancel Order");
						String go = sc.nextLine();
						Integer order;
						try {
							order = Integer.parseInt(go);

						} catch (Exception e) {
							System.out.println("Invalid choice");
							break;
						}
						if (order == 1) {
							// INSERT ORDER INTO THE BOOKING TABLE
							String cart = "Insert into usercart values(" + Id1 + "," + ID1 + "," + quantity + ")";
							try {
								stmt.executeUpdate(cart);

								System.out.println("Item added to cart");
							} catch (Exception e) {
								System.out.println("Could not add to cart");
								System.out.println(e);
							}

						} else {
							System.out.println("Cancelled");
						}

					} else {
						System.out.println("No such product available");
					}
					values1.close();
					break;

				case 4:
					// SAKSHAM MAHAJAN KA KAAM HAI YEH.
//					String temp="Select * from usercart";
//					ResultSet r=stmt.executeQuery(temp);
//					while(r.next())
//					{
//						Integer usID=r.getInt("CUSTOMERID");
//						Integer giID=r.getInt("GIFTID");
//						Integer qn=r.getInt("QUANTITY");
//						System.out.println("ORDER:"+usID+" "+giID+" "+qn);
//					}
					Cart cr = new Cart();
					cr.fun(sc, Id1);
					break;
				case 5:
					done = true;
					break;
				default:
					System.out.println("Abe input dekh le");
					break;





				}
			}

			connect.close();
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("Database connectivity error");
			System.out.println(e);
		}
	}

};
