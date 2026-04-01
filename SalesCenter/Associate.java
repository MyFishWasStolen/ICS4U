package SalesCenter;

// Represents an hourly associate; extends Employee with hourly wage-based pay
public class Associate extends Employee
{
    private double hourlySalary;  // Hourly wage in dollars

    // Constructor: sets title to "Associate" and stores hourly wage
    public Associate(String name, double wage)
    {

        super(name,"Associate");
        hourlySalary = wage;

    }

    // Calculates pay for a given number of hours worked
    // Multiplies hourly wage by total hours worked
    @Override
    public double calculateSalary(double hours)
    {

        return hourlySalary*hours;

    }
}
