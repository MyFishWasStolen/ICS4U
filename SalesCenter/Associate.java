package SalesCenter;

public class Associate extends Employee
{
    private double hourlySalary;

    public Associate(String name, double wage)
    {

        super(name,"Associate");
        hourlySalary = wage;

    }

    @Override
    public double calculateSalary(double hours)
    {

        return hourlySalary*hours;

    }
}
