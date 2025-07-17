package dev.genChallenge;

import dev.genChallenge.Model.SamStudent;
import dev.genChallenge.Model.Student;
import dev.genChallenge.Util.QueryItem;
import dev.genChallenge.Util.QueryList;
import dev.genChallenge.Util.SamComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        QueryList<SamStudent> queryList=new QueryList<>();
        for (int i = 0; i < 5; i++) {
            queryList.add(new SamStudent());
        }
        System.out.println("Ordered");
        queryList.sort(Comparator.naturalOrder());
        var matches=queryList
                .getMatMaches("percentagecomplete","40");
        printList(queryList);

//        matches.sort(new SamComparator());
//
//        printList(matches);
//
//        matches.sort(Comparator.naturalOrder());
//        printList(matches);


    }


    public static void printList(List<?> students ){
       for (var student:students){
           System.out.println(student);
       }
        System.out.println();
    }


}

