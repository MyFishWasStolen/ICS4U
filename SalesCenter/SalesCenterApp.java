//Programmer: Matthew Sinoj
//Description: 
//Date Created: March 23, 2026
//Date Revised: March 25, 2026

package SalesCenter;
import java.util.Scanner;

public class SalesCenterApp {

    public static void main(String[] args) {
        
    
        SalesCenter sc = new SalesCenter();
        String choice =null;
        int empNum = 0;
        Scanner input = new Scanner(System.in);

        do
        {
            System.out.println("Employee/Pay/Quit");
            System.out.print("Enter Choice: ");
            choice = input.nextLine();

            switch (choice) {
                case "E","P":
                    System.out.print("Enter employee number(1,2 or 3)");

                    empNum = input.nextInt();
                    


                    break;


                case ("Q"):
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }




        
        }while (choice !=("Q"));

        input.close();
    }
    
}
