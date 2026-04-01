package SalesCenter;

// Abstract base class representing a generic employee in the sales center
public abstract class Employee
{

 private String name;   // Employee's full name
 private String title;  // Employee's job title (e.g., "Manager", "Associate")

 // Constructor: initializes name and title for any employee
 public Employee(String n, String t)
 {
    name = n;
    title =t;


 }

 // Returns a formatted string with the employee's name and title
 public String getInfo()
 {

     return name + ", " + title;

 }

 // Abstract method: each subclass must define how salary is calculated
 // payPeriod meaning varies by subclass (hours for associates, weeks for managers)
 public abstract double calculateSalary(double payPeriod);



}
