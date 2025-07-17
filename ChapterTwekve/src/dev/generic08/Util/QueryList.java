package dev.generic08.Util;

import dev.generic08.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QueryList <T extends Student & QueryItem>{
    private List<T> items;

    public QueryList(List<T> items) {
        this.items = items;
    }


    public static<T extends QueryItem> List<T> getMatMaches(List<T> item,String field,String value){
        List<T> matches=new ArrayList<>();
        for (var el:item) {
            if(el.matchFieldValue(field, value)){
                matches.add(el);
            }
        }
        return matches;
    }

//
//    public List<T> getMatMaches(String field,String value){
//        List<T> matches=new ArrayList<>();
//        for (T el:items) {
//           if(el.matchFieldValue(field, value)){
//               matches.add(el);
//           }
//        }
//        return matches;
//    }
}
