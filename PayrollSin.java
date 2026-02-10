//Programer: Mathew Sinoj
//Description: Program will calculate empolyee payroll
//Date Created: January 29 2026
//Date Revised: Febuary 9 2026

//import packages
import java.text.NumberFormat;
import java.util.Scanner;
import java.lang.Double;
import java.lang.String;  

public class PayrollSin
{

    public static void main(String[]args)
    {
        //create neccesary objects
        Scanner input =new Scanner(System.in);
        NumberFormat currency = NumberFormat.getCurrencyInstance();

        //ask the user for the number of empolyees they have
        System.out.println("Enter the number of employees");
        int n=input.nextInt();//initalize a variable with the num of empolyeees

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
            employee[i][0]=input.next(); //store employees name

            System.out.println("Enter the employees hourly wage");
            employee[i][1]=input.next(); //store hourly wage

            System.out.println("Enter the employees hours worked");
            employee[i][2]=input.next(); //store hours worked

            //calulate and store gross income
            employee[i][3]= String.valueOf(Double.parseDouble(employee[i][1])*Double.parseDouble(employee[i][2]));

            //calulate and store deductions
            employee[i][4]=String.valueOf(tax(Double.parseDouble(employee[i][3])));

            //calculate and store netpay
            employee[i][5]=String.valueOf(Double.parseDouble(employee[i][3])-Double.parseDouble(employee[i][4]));


        }

        //for loop the calculates total gross pay, total deductions, total net pay, average hours and count each person in each tax baraket
        for(int i=0;i<n;i++)
        {   
            //add up and store each empolyee income, deductions, netpay, and hours
            totalGrossIncome += Double.parseDouble(employee[i][3]);
            totalDeductions += Double.parseDouble(employee[i][4]);
            totalNet += Double.parseDouble(employee[i][5]);
            averageHours += Double.parseDouble(employee[i][2]);

            //check which bracket each employee is in and add one to the counter
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
        //calulate and store average gross income and average hours
        averageGross = totalGrossIncome/n;
        averageHours = averageHours/n;


        //for loop that prints each empolyee's stats
        for(int i=0;i<n;i++)
        {

            System.out.println("Employee Name: " +employee[i][0] );
            System.out.println("Employee Hourly Wage: " +currency.format(Double.parseDouble(employee[i][1])));
            System.out.println("Employee Hours Worked: " +(employee[i][2]));
            System.out.println("Employee Gross Income: " +currency.format(Double.parseDouble(employee[i][3] )));
            System.out.println("Employee Deductions: " +currency.format(Double.parseDouble(employee[i][4] )));
            System.out.println("Employee Net Income: " +currency.format(Double.parseDouble(employee[i][5] )));
            System.out.println("---------------------------------------------");

        }

        //print totals and averages for payroll and which employee has the highest horus and what braket empolyees are in

        System.out.println("Total Gross Earnings: " + currency.format(totalGrossIncome));
        System.out.println("Total Deductions: " + currency.format(totalDeductions));
        System.out.println("Total Net: " + currency.format(totalNet));
        System.out.println("Average gross: " + currency.format(averageGross));
        System.out.println("Average hours: " + averageHours);
        System.out.println("Employee with the most hours:" + employee[mostHours][0]);
        System.out.println("Empolyees in upper bracket: " + upper);
        System.out.println("Empolyees in middle bracket: " + middle);
        System.out.println("Empolyees in lower bracket: " + lower);

        input.close();//close input

    }

    //method that calulates tax based on tax bracket

    /*pre: none
     * post: returns deductions based of tax brackets
     */
    private static double tax(double grossIncome)
    {
        //method that calulates tax based on tax bracket


        double tax = 0;//initalize variable

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
