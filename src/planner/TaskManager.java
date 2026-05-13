package planner;

import java.util.ArrayList;
import java.time.LocalDate;
/**
 * TODO
 * 
 * @author  Lina
 */
public class TaskManager {

    private ArrayList<Task> tasks;

    public TaskManager(){ //TaskManager creates its own empty list 
        tasks = new ArrayList<>();
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public void removeTask(int index){
        tasks.remove(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void markComplete(int index) {
        tasks.get(index).markComplete();
    }

    public void markIncomplete(int index){
        tasks.get(index).markIncomplete();
    }

    public void editTaskTitle(int index, String newTitle) {
        tasks.get(index).setTitle(newTitle);
    }

    public void editTaskDueDate(int index, LocalDate newDueDate) {
        tasks.get(index).setDueDate(newDueDate);
    }

    public void editTaskPriority(int index, Priority newPriority) {
        tasks.get(index).setPriority(newPriority);
    }

    @Override
    public String toString() { //needed to print whole task list easily 
        return "Tasks: " + tasks;
    }

    
}