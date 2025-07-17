package dev.nested.domain;

import java.util.Comparator;

public class EmployeeComparator<T extends Employee> implements Comparator<Employee> {

    @Override
    public int compare(Employee o1,Employee o2){
        return o1.getYearStarted()- o2.getYearStarted();
    }
}
