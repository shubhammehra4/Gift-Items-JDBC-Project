
public class landingClass {
	
	public static void menu{
		
		Scanner sc= new Scanner(System.in);
		bool done=0;
		
		while(!done)
		{
			try {
				System.out.println("*************Welcome to your very own Gift Shop*************\n MAIN MENU");
				System.out.println("1. New to our Shop? Create an account.");
				System.out.println("2. Log in to check out the cool gifts");
				System.out.println("3. Admin Login");
				System.out.println("4. Exit");
				
				System.out.println("Press the number corresponding to your choice");
				
				String getValue;
				
				getValue= sc.nextLine();
				
				int choice;
				try
				{
					
					choice=Integer.parseInt(getValue);
					
					
				}
				catch (Exception e)
				{
					choice=5;
				}
				switch(choice)
				{
					case 1:
						// Verify with signup class.
						SignUp newUser= new SignUp();
						newUser.sign(sc);
						break;
					case 2:
						// Verify with LogIn class
						LogIn currUser= new LogIn();
						currUser.login(sc);
						break;
					case 3:
						// Verify with VerifyAdmin class.
						VerifyAdmin admin= new VerifyAdmin();
						break;
					case 4:
						done=1;
						break;
					default:
						System.out.println("Incorrect Choice.\n Please enter an integer between 1 and 4.");
						break;
							
				}
				
			}
			
			catch (Exception e)
			{
				System.out.println("Err.. Something went wrong, please report the following error to the admin");
				System.out.println(e);
			}
			
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		menu();
		System.out.println("Thank you for visiting. Hope to see you soon. Have a good day");

	}

}
