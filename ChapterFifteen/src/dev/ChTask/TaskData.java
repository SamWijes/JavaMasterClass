package dev.ChTask;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaskData {

    public static String annsTasks= """
            Infrastructure,Security,High,In Progress
            Infrastructure,Password Policy,Medium,In Progress
            Research,Cloud solutions,Medium,In Progress
            Data Design,Encryption Policy,High
            Data Design,Project Table,Medium
            Data Access,Write Views,Low,In Progress
            
            """;

    public static String bobsTasks= """
            Infrastructure,Security,High,In Progress
            Infrastructure,Password Policy,Medium
            Data Design	,Encryption Policy,High
            Data Access	,Write Views,Low,In Progress
            
            """;

    public static String carolsTasks= """
            Infrastructure,Logging,High,In Progress
            Infrastructure,DB Access,Medium
            Infrastructure,Password Policy,Medium
            Data Design,Task Table,High
            Data Access,Write Views,Low
            
            """;
    public static String alltasks= """
            Infrastructure,Logging,High
            Infrastructure,DB Access,Medium
            Infrastructure,Security,High
            Infrastructure,Password Policy,Medium
            Data Design,Task Table,Medium
            Data Design,Employee Table,Medium
            Data Design,Cross Reference Tables,High
            Data Design,Encryption Policy,High
            Data Access,Write Views,Low
            Data Access,Set Up Users,Low
            Data Access,Set Up Access Policy,Low
            """;

    public static Set<Task> getTask(String owner){
        Set<Task> task=new HashSet<>();
        String taskOwner= ("ann,bob,carol".contains(owner.toLowerCase()))? owner:null;
        String selectedTask=switch (owner.toLowerCase()){
            case "ann"->annsTasks;
            case "bob"->bobsTasks;
            case "carol"->carolsTasks;
            default -> alltasks;
        };
        for(var taskLine:selectedTask.split("\n")){
            String [] data=taskLine.split(",");
            Arrays.asList(data).replaceAll(String::trim);
            Status status=(data.length<=3)? Status.IN_QUEUE:Status.valueOf(data[3].
                    toUpperCase().replaceAll(" ","_"));
            if (data.length>3) {
                task.add(new Task(taskOwner,data[0],data[1],status,Priority.valueOf(data[2].toUpperCase()) ));

            }else  task.add(new Task(taskOwner,data[0],data[1],Priority.valueOf(data[2].toUpperCase())));

        }
        return  task;
    }
}
