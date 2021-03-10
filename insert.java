import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class insert {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		try
//		{
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Connection connect=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","Nishit","hello123");
//			Statement stmt=connect.createStatement();
//			String s="insert into c values ('Nishit','shubham','helloworld')";
//			int x=stmt.executeUpdate(s);
//
//			if(x>0)
//			{
//				System.out.println("SUCCESS");
//			}
//			else
//			{
//				System.out.println("FAILED");
//			}
//			connect.close();
//		}
//		catch(Exception e)
//		{
//			System.out.println(e);
//		}
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "Nishit", "hello123");
			Statement stmt = connect.createStatement();
			String query = "Select * from Customers";
			ResultSet res = stmt.executeQuery(query);

			while (res.next()) {
				String name = res.getString("Name");
				String cost = res.getString("Username");
				String email = res.getString("Email");
				Integer id = res.getInt("ID");
				String pass = res.getString("Password");
				System.out.println("Curr:" + name + cost + email + id + " " + pass);

			}
		} catch (Exception e) {

		}

	}

};

