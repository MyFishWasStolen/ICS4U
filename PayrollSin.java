//import packages
import java.text.NumberFormat;
import java.util.Scanner;

public class PayrollSin
{

    public static void main(String[]args)
    {
        //create neccesary objects
        Scanner sc=new Scanner(System.in);
        NumberFormat currency = NumberFormat.getCurrencyInstance();

        //ask the user for the number of empolyees they have
        System.out.println("Enter the number of employees");
        int n=sc.nextInt();//initalize a variable with the num of empolyeees

        //2d array to store mutiple values assgined to one empolyee
        String[][] employee=new String[n][6];

        //initalize varabiles
        double totalGrossIncome=0,totalDeductions=0,totalNet=0,averageGross=0,averageHours=0;
        int lower=0, middle=0, upper=0, mostHours=0;

        //for loop that asks user for empolyee name, hourly wage, hours worked,
        //then calculate the gross income, tax deductions, and net pay
        for(int i=0;i<n;i++)
        {
            System.out.println("Enter the employees name");
            employee[i][0]=sc.next();

            System.out.println("Enter the employees hourly wage");
            employee[i][1]=sc.next();

            System.out.println("Enter the employees hours worked");
            employee[i][2]=sc.next();

            employee[i][3]= String.valueOf(Double.parseDouble(employee[i][1])*Double.parseDouble(employee[i][2]));

            employee[i][4]=String.valueOf(tax(Double.parseDouble(employee[i][3])));

            employee[i][5]=String.valueOf(Double.parseDouble(employee[i][3])-Double.parseDouble(employee[i][4]));


        }

        //for loop the calculates total gross pay, total deductions, total net pay, average hours and count each person in each tax baraket
        for(int i=0;i<n;i++)
        {
            totalGrossIncome += Double.parseDouble(employee[i][3]);
            totalDeductions += Double.parseDouble(employee[i][4]);
            totalNet += Double.parseDouble(employee[i][5]);
            averageHours += Double.parseDouble(employee[i][2]);

            if(Double.parseDouble(employee[i][3])<=575)
            {

                lower++;

            }
            else if(Double.parseDouble(employee[i][3])<=950)
            {

                middle++;

            }
            else if (Double.parseDouble(employee[i][3])>950)
            {

                upper++;

            }

            if(Double.parseDouble(employee[mostHours][2])<Double.parseDouble(employee[i][2]))
            {

                mostHours=i;

            }

        }
        averageGross = totalGrossIncome/n;
        averageHours = averageHours/n;


        //for loop that prints each empolyee's stats
        for(int i=0;i<n;i++)
        {

            System.out.println("Employee Name: " +employee[i][0] );
            System.out.println("Employee Hourly Wage: $" +currency.format(employee[i][1]));
            System.out.println("Employee Hours Worked: " +(employee[i][2]));
            System.out.println("Employee Gross Income: $" +currency.format(employee[i][3] ));
            System.out.println("Employee Deductions: $" +employee[i][4] );
            System.out.println("Employee Net Income: $" +employee[i][5] );
            System.out.println("---------------------------------------------");

        }


        System.out.println("Total Gross Earnings: $" + totalGrossIncome);
        System.out.println("Total Deductions: $" + totalDeductions);
        System.out.println("Total Net: $" + totalNet);
        System.out.println("Average gross: $" + averageGross);
        System.out.println("Average hours: " + averageHours);
        System.out.println("Employee with the most hours:" + employee[mostHours][0]);
        System.out.println("Empolyees in upper bracket: " + upper);
        System.out.println("Empolyees in middle bracket: " + middle);
        System.out.println("Empolyees in lower bracket: " + lower);


    }

    //method that calulate tax based on tax bracket
    private static double tax(double grossIncome)
    {
        double tax = 0;//initalize varaible

        //lower tax braket if income less than 575
        if(grossIncome<=575)
        {

            tax = grossIncome*0.17;

        }
        //middle tax braket if income is more than 575 but less than 960
        else if (grossIncome<=960)
        {
            double lower = 575*0.17; // calculate tax deductions for amount that is 575 and less
            tax = ((grossIncome-575)*0.25)+lower;//calculate tax deductions for income over 575


        }
        else if (grossIncome>960)
        {

            double lower = 575*0.17;
            double middle = 960*0.25;
            tax = ((grossIncome-575-960)*0.35)+lower+middle;

        }

        return tax;//return total tax deductions
    }



}
