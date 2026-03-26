//Programmer: Matthew Sinoj
//Description: 
//Date Created: March 23, 2026
//Date Revised: March 25, 2026

package SalesCenter;
import java.util.Scanner;

public class SalesCenterApp {

    public static void main(String[] args) {
        
    
    
        String choice = "";
        int empNum = 0;
        Scanner input = new Scanner(System.in);

        while (choice != "Q") 
        {
            System.out.println("Employee/Pay/Quit");
            System.out.print("Enter Choice: ");
            choice = input.nextLine();
            
            if (choice.equals("E")) 
            {
                System.out.print("Enter employee number(1,2 or 3)");
                empNum = input.nextInt();
            }

            else if (choice.equals("P"))
            {
                System.out.print("Enter employee number(1,2 or 3)");
                empNum = input.nextInt();
            }

            else
            {
                break;
            }
        
        }

    }
    
}
