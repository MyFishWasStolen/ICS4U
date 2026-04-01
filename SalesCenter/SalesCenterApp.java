//Programmer: Matthew Sinoj
//Description: Main application for the Sales Center, lets the user look up employee info or calculate pay
//Date Created: March 23, 2026
//Date Revised: March 25, 2026

package SalesCenter;
import java.util.Scanner;

public class SalesCenterApp {

    public static void main(String[] args) {


        SalesCenter sc = new SalesCenter();  // Create the sales center with its employee list
        String choice =null;    // Stores the user's menu selection
        int empNum = 0;         // Stores the chosen employee number (1-3)
        double payPeriod= 0;    // Stores hours (associates) or weeks (managers) for pay calculation
        Scanner input = new Scanner(System.in);

        // Loop until the user chooses to quit
        do
        {
            System.out.println("Employee/Pay/Quit");
            System.out.print("Enter Choice: ");
            // Read first character of input and convert to uppercase for case-insensitive matching
            choice = String.valueOf(input.next().toUpperCase().charAt(0));

            switch (choice) {
                case ("E"):
                    // Display the name and title of the selected employee
                    System.out.print("Enter employee number(1,2 or 3)");
                    empNum = input.nextInt();
                    System.out.println(sc.getEmployeeInfo(empNum));
                    break;

                case ("P"):
                    // Display the name, title, and calculated pay for the selected employee
                    System.out.print("Enter employee number(1,2 or 3)");
                    empNum = input.nextInt();
                    System.out.print("Enter employee pay period(hours for associate and weeks for managers): ");
                    payPeriod = input.nextDouble();
                    System.out.println(sc.
                            getEmployeePay(empNum, payPeriod));
                    break;

                case ("Q"):
                    // User chose to quit; loop condition will exit
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }





        }while (!choice.equals("Q"));

        input.close();  // Close scanner to release system resources
    }

}
