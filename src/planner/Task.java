package planner;

import java.time.LocalDate;

/**
 * Represents one task or assignment in the planner.
 * Stores the task title, due date, priority, and completion status. 
 * 
 * @author  Lina
 */
public class Task {

    private String title;
    private LocalDate dueDate;
    private Priority priority;
    private boolean completed;

    public Task(String title, LocalDate dueDate, Priority priority, boolean completed) {
        this.title = title;
        this.dueDate = dueDate;
        this.priority = priority;
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markComplete() {
        completed = true;
    }

    public void markIncomplete() {
        completed = false;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return title + " - Due Date: " + dueDate + " Priority: " + priority + " Completed?: " + completed;
    }
}