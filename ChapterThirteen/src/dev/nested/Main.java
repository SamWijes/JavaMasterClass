package dev.nested;

import dev.nested.domain.Employee;
import dev.nested.domain.StoreEmployee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees=new ArrayList<>(List.of(
                new Employee(1001,"Ralph",2015),
                new Employee(1005,"Carole",2013),
                new Employee(1022,"Mike",2022),
                new Employee(1045,"Sara",2003)
                ));

//        var comparator=new EmployeeComparator<>();
//        employees.sort(comparator);
        //var comparator= new Employee.EmployeeComparator<>("name");
        employees.sort(new Employee.EmployeeComparator<>());
        for (var employee:employees ) {
            System.out.println(employee);
        }

        List<StoreEmployee> storeEemployees=new ArrayList<>(List.of(
                new StoreEmployee("Walmat",1001,"Ralph",2015),
                new StoreEmployee("WallGreen",1005,"Carole",2013),
                new StoreEmployee("JCorner",1022,"Mike",2022),
                new StoreEmployee("Walmat",1045,"Sara",2003)
        ));
        //var genericEmployee=new StoreEmployee();
        //var comparator=genericEmployee.new StoreComparator<>();
       storeEemployees.sort(new StoreEmployee().new StoreComparator<>());

        for (var employee:storeEemployees ) {
            System.out.println(employee);
        }

//        Meal meal =new Meal();
//        System.out.println(meal);
//
//        Meal ml2=new Meal(293);
//        System.out.println(ml2);
        System.out.println("Piglatin names");
        addPigLatinName(storeEemployees);
    }

    public static void addPigLatinName(List<? extends StoreEmployee> list){
        class DecoratedEmployee extends StoreEmployee implements Comparable<DecoratedEmployee> {
            private String pigLatin;
            private Employee originalInstance;

            public DecoratedEmployee(String pigLatin, Employee originalInstance) {
                this.pigLatin = pigLatin;
                this.originalInstance = originalInstance;
            }

            @Override
            public String toString() {
                return originalInstance.toString() +" "+ pigLatin ;
            }

            @Override
            public int compareTo(DecoratedEmployee o) {
                return pigLatin.compareTo(o.pigLatin);
            }
        }
        List<DecoratedEmployee> newList=new ArrayList<>();

        for(var employee:list){
            String pigLatinName=employee.getName().substring(1)+employee.getName().charAt(0)+"ay";
            newList.add(new DecoratedEmployee(pigLatinName,employee));
        }
        newList.sort(null);
        for (var el:newList) {
            System.out.println(el.originalInstance+ "  "+el.pigLatin);
        }
    }
}
