import java.util.Scanner;

class Main {

    public static void driver() {
        boolean running = true;

        try (Scanner scan = new Scanner (System.in) ) {
            while (running) {
                System.out.println();
                System.out.println ("Choose one of these options:");
                System.out.println();
                System.out.println ("\t1. Login & Shop");
                System.out.println ("\t2. User Register");
                System.out.println ("\t3. Admin Login");
                System.out.println ("\t4. Exit");
                String input = scan.nextLine();
                int answer;

                try {
                    answer = Integer.valueOf (input);
                } catch (Exception e) {
                    answer = -1;
                }

                switch (answer) {
                    case 1:
                        System.out.println ("Login Page");
                        // Login Page
                        Login L = new Login();
                        L.execute (scan);
                        break;

                    case 2:
                        System.out.println ("Register Page");
                        // Register Page
                        Register R = new Register();
                        R.execute (scan);
                        break;

                    case 3:
                        System.out.println ("Admin Login");
                        // Admin Login
                        break;

                    case 4:
                        running = false;
                        break;

                    default:
                        System.out.println ("Incorrect Input!");
                        System.out.println ("Please enter a Number between 1-4");
                }

                // Clear Console
                // System.out.print("\033[H\033[2J");
                // System.out.flush();
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println ("Something went wrong");
        } finally {
            System.out.println ("Thanks for visiting us! ");
            System.out.println ("Hope to see you again soon");
            System.out.println ("Bye!");
        }
    }

    public static void main (String[] args) {
        System.out.println ("###############################");
        System.out.println ("\t\t\tWelcome");
        driver();
    }
}