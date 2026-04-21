package Old.SalesCenter;

// Represents a salaried manager; extends Employee with annual salary-based pay
public class Manager extends Employee
{

    private double salary;  // Annual salary in dollars

    // Constructor: sets name to "Manager" title and stores annual salary
    public Manager(String name, double wage)
    {

        super(name,"Manager");
        salary = wage;

    }

    // Calculates pay for a given number of weeks worked
    // Divides annual salary by 52 weeks, then multiplies by weeks worked
    @Override
    public double calculateSalary(double weeks)
    {

        return (salary / 52.0) * weeks;

    }
}
