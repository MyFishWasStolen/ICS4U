package SalesCenter;
import java.text.NumberFormat;

public class SalesCenter
    {

        private Employee[] employees;
        private NumberFormat currency;

        public SalesCenter()
        {

            currency = NumberFormat.getCurrencyInstance();

            employees = new Employee[3];
            employees[0] = new Manager("Diego Martin",55000.00);
            employees[1] = new Associate("Kylie Walter", 18.50);
            employees[2] = new Associate("Michael Rose", 16.75);



        }

        public String getEmployeeInfo(int employeeNumber)
        {

            return employees[employeeNumber-1].getInfo();

        }

        public String getEmployeePay(int employeeNumber, double payPeriod)
        {

            Employee emp = employees[employeeNumber-1];
            double pay = emp.calculateSalary(payPeriod);
            return emp.getInfo()+"\n"+ currency.format(pay);

        }


    }
