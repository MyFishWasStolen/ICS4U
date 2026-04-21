package Old.SalesCenter;
import java.text.NumberFormat;

// Manages a collection of employees and provides methods to retrieve their info and pay
public class SalesCenter
    {

        private Employee[] employees;  // Array holding all employees in the sales center
        private NumberFormat currency; // Formatter for displaying dollar amounts

        // Constructor: sets up currency formatter and initializes the employee roster
        public SalesCenter()
        {

            currency = NumberFormat.getCurrencyInstance();

            employees = new Employee[3];
            employees[0] = new Manager("Diego Martin",55000.00);   // Manager with annual salary
            employees[1] = new Associate("Kylie Walter", 18.50);   // Associate with hourly wage
            employees[2] = new Associate("Michael Rose", 16.75);   // Associate with hourly wage



        }

        // Returns name and title of the employee at the given 1-based employee number
        public String getEmployeeInfo(int employeeNumber)
        {

            return employees[employeeNumber-1].getInfo();

        }

        // Returns name, title, and formatted pay for the employee for the given pay period
        // payPeriod is hours for associates and weeks for managers
        public String getEmployeePay(int employeeNumber, double payPeriod)
        {

            Employee emp = employees[employeeNumber-1];
            double pay = emp.calculateSalary(payPeriod);
            return emp.getInfo()+"\n"+ currency.format(pay);

        }


    }
