package SalesCenter;

public class Manager extends Employee
{

    private double salary;

    public Manager(String name, double wage)
    {

        super(name,"Manager");
        salary = wage;

    }

    @Override
    public double calculateSalary(double weeks)
    {

        return (salary / 52.0) * weeks;

    }
}
