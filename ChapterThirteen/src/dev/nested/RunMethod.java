package dev.nested;

import dev.nested.domain.StoreEmployee;

import java.util.ArrayList;
import java.util.List;

public class RunMethod {

    public static void main(String[] args) {
        List<StoreEmployee> storeEmployees=new ArrayList<>(List.of(
                new StoreEmployee("Walmart",10015,"Meg",2019),
                new StoreEmployee("Cloud9",10025,"Jim",2015),
                new StoreEmployee("Fashion House",10002,"Cory",2011),
                new StoreEmployee("Nolimit",10018,"Donald",2018),
                new StoreEmployee("French Corner",10010,"Parkson",2001)

        ));
    }
}
