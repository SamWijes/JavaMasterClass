package dev.genChallenge.Util;

import dev.genChallenge.Model.Student;


import java.util.ArrayList;
import java.util.List;

public class QueryList<T extends Student & QueryItem> extends ArrayList<T>{
        // private List<T> items;
    public QueryList(){

    }
    public QueryList(List<T> items) {
        super(items);
//        this.items=items;
    }

    public static<S extends QueryItem> List<S> getMatMaches(List<S> item, String field, String value){
        List<S> matches=new ArrayList<>();
        for (var el:item) {
            if(el.matchFieldValue(field, value)){
                matches.add(el);
            }
        }
        return matches;
    }

//
    public QueryList<T> getMatMaches(String field,String value){

        QueryList<T> matches=new QueryList<>();
        for (T el:this) {
           if(el.matchFieldValue(field, value)){
               matches.add(el);
           }
        }
        return matches;
    }
}
