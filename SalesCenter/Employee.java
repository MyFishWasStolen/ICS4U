package SalesCenter;

public abstract class Employee
{

 private String name;
 private String title;

 public Employee(String n, String t)
 {
    name = n;
    title =t;


 }

 public String getInfo()
 {

     return name + ", " + title;

 }

 public abstract double calculateSalary(double payPeriod);



}
