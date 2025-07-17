package dev.chall;

import java.time.LocalDate;
import java.util.*;

record Employee(String FirstName, String LastName, String hireDate){}

public class Main {

    public static void main(String[] args) {

        List<Employee> employeeList=new ArrayList<>(List.of(
                new Employee("Jhon","Doe","2002-05-12"),
                new Employee("Jim","Carter","2004-06-14"),
                new Employee("Rodney","Stewart","2002-07-18"),
                new Employee("Jake","Sully","2008-04-19")
        ));

        //System.out.println(LocalDate.parse("2002-05-12").getYear());


        EmployeeSort(employeeList);
    }

    public static void EmployeeSort(List<Employee> empList){

        class EmployeeDetails {
            private String fullName;
            private int yearsWorked;
            private Employee employeeInstance;

            public EmployeeDetails(Employee employeeInstance ) {
                this.employeeInstance=employeeInstance;
                this.fullName=employeeFullName(employeeInstance);
                this.yearsWorked=years(employeeInstance);
            }

            private String employeeFullName(Employee employee){
                return employee.FirstName()+" "+employee.LastName();

            }
            private int years(Employee employee){
                return LocalDate.now().getYear()- LocalDate.parse(employee.hireDate()).getYear();

            }

            @Override
            public String toString() {
                return "Employee " +fullName+" has worked for "+yearsWorked+"years";
            }




        }
        List<EmployeeDetails> newList=new ArrayList<>();
        for (var emp:empList) {
            newList.add(new EmployeeDetails(emp));
        }
        var c=new Comparator<EmployeeDetails>(){
            @Override
            public int compare(EmployeeDetails o1, EmployeeDetails o2) {
                return o1.fullName.compareTo(o2.fullName);
            }
        };
        newList.sort(c);
        for(var emp:newList){
            System.out.println(emp);
        }
    }
}
