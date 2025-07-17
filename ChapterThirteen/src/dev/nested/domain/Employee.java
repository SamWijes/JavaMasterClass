package dev.nested.domain;

import java.util.Comparator;

public class Employee {
    public static class EmployeeComparator<T extends Employee>
            implements Comparator<Employee> {
        private String sortType;

        public EmployeeComparator(String sortType){
            this.sortType=sortType;
        }

        public EmployeeComparator(){
            this.sortType="name";
        }

        @Override
        public int compare(Employee o1,Employee o2){
            if(sortType.equalsIgnoreCase("yearstarted")){
                return o1.yearStarted- o2.yearStarted;
            } else if (sortType.equalsIgnoreCase("id")) {
                return o1.employeeId- o2.employeeId;
            }else return o1.name.compareTo(o2.name);

        }
    }
    private String name;
    private int employeeId;
    private int yearStarted;

    public Employee(int employeeId,String name,  int yearStarted) {
        this.employeeId = employeeId;
        this.name = name;
        this.yearStarted = yearStarted;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public int getYearStarted() {
        return yearStarted;
    }

    @Override
    public String toString() {
        return "%5d %-8s %5d ".formatted(employeeId,name,yearStarted);
    }
}
