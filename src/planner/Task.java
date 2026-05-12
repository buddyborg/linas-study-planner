/**
 * TODO
 * 
 * @author  Lina
 */
public class Task {

    private String title;
    private String dueDate;
    private String priority;
    private boolean completed;

    public Task(String title, String dueDate, String priority, boolean completed) {
        this.title = title;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markComplete() {
        completed = true;
    }

    @Override
    public String toString() {
        return "Task [title=" + title + ", dueDate=" + dueDate + ", priority=" + priority + ", completed=" + completed
                + "]";
    }
}