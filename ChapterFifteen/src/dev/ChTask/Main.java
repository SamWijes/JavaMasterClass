package dev.ChTask;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Set<Task> tasksAll=TaskData.getTask("all");
        Set<Task> tasksAnn=TaskData.getTask("ann");
        Set<Task> tasksBob=TaskData.getTask("bob");
        Set<Task> tasksCarol=TaskData.getTask("carol");

        Set<Task> unionTask=getUnion(List.of(tasksAnn,tasksBob,tasksCarol));
        Comparator<Task> sortPriority=Comparator.comparing(Task::getPriority);
        //Additional tasks done by employee
        Set<Task> additional=getDifference(unionTask,tasksAll);
        sortAndPrint(additional,"Additional Tasks Done By Employee");

        //Assigned Tasks
        Set<Task> assigned=getIntersect(unionTask,tasksAll);
        sortAndPrint(assigned,"Tasks Assigned to  Employees");
        //Pending Assignment
        Set<Task> tasksAvail=getDifference(tasksAll,unionTask);
        sortAndPrint(tasksAvail,"Pending Assignemnt");

        //Overlapping Task
        Set<Task> AnnBob=getIntersect(tasksAnn,tasksBob);
        Set<Task> bobCarol=getIntersect(tasksBob,tasksCarol);
        Set<Task> AnnCarol=getIntersect(tasksAnn,tasksCarol);

        Set<Task> overlap=getUnion(List.of(AnnBob,bobCarol,AnnCarol));

        sortAndPrint(overlap,"Overalpp");


        List<Set<Task>> sets=List.of(tasksAnn,tasksBob,tasksCarol);
        List<Task> overlapping=new ArrayList<>();
        for (var set:sets) {
            Set<Task> dupes=getIntersect(set,overlap);
            overlapping.addAll(dupes);
        }

        Comparator<Task> priorityNat=sortPriority.thenComparing(Comparator.naturalOrder());

        overlapping.sort(priorityNat);
        sortAndPrint(overlapping,"Overlapping on each Emp",priorityNat);



        sortAndPrint(tasksAll,"Without Sort");
        sortAndPrint(tasksAll,"Sort",sortPriority);
        sortAndPrint(tasksAnn,"Without Sort");



    }
    private static void sortAndPrint(Collection<Task> collection, String header){
        sortAndPrint(collection,header,null);
    }


    private static Set<Task> getUnion(List<Set<Task>> setABC){
        Set<Task> s=new HashSet<>();
        for (var curSet:setABC) {
            s.addAll(curSet);
        }
        return s;
    }

    private static Set<Task> getIntersect(Set<Task> setA,Set<Task> setB){
        Set<Task> s=new HashSet<>(setA);
        s.retainAll(setB);
        return s;
    }

    private static Set<Task> getDifference(Set<Task> setA,Set<Task> setB) {
        Set<Task> s = new HashSet<>(setA);
        s.removeAll(setB);
        return s;
    }



    private static void sortAndPrint(Collection<Task> collection, String header, Comparator<Task> sorter){
        System.out.println("_".repeat(30));
        System.out.println(header);
        System.out.println("_".repeat(30));

        List<Task> taskList=new ArrayList<>(collection);
        taskList.sort(sorter);
        taskList.forEach(System.out::println);


    }
}
