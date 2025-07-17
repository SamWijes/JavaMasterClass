package dev.ChTask;
enum Status {IN_QUEUE,ASSIGNED,IN_PROGRESS,NOT_ASSIGNED}
enum Priority{HIGH,MEDIUM,LOW}
public class Task implements Comparable<Task>{
    private String assignee;
    private String projectName;
    private String taskDescription;
    private Status status;
    private Priority priority;

    public Status getStatus() {
        return this.status;
    }

    public Priority getPriority() {
        return this.priority;
    }

    @Override
    public String toString() {
        return "%-10s %-17s %-23s %12s %10s".formatted(assignee,projectName,taskDescription,status,priority);
    }

    @Override
    public int compareTo(Task o) {
        int sort1=this.projectName.compareTo(o.projectName);
        if (sort1 == 0) {
            sort1=this.taskDescription.compareTo(o.taskDescription);
        }
        return sort1;
    }

    public Task(String assignee, String projectName, String taskDescription, Priority priority) {
        this(assignee,projectName,taskDescription,null,priority);
    }

    public Task(String assignee, String projectName, String taskDescription, Status status, Priority priority) {
        this.assignee = assignee;
        this.projectName = projectName;
        this.taskDescription = taskDescription;
        this.status = status;
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (!projectName.equals(task.projectName)) return false;
        return taskDescription.equals(task.taskDescription);
    }

    @Override
    public int hashCode() {
        int result = projectName.hashCode();
        result = 31 * result + taskDescription.hashCode();
        return result;
    }
}
