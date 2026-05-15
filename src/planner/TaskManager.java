package planner;

import java.util.ArrayList;
import java.util.Comparator;
import java.time.LocalDate;
/**
 * Manages the list of tasks in the study planner.
 * Provides methods to add, remove, edit, complete, filter, search and sort tasks. 
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

    public ArrayList<Task> getCompletedTasks(){
        ArrayList<Task> completedTasks = new ArrayList<>();

        for (Task task : tasks){
            if(task.isCompleted()){
                completedTasks.add(task);
            }
        }
        return completedTasks;
    }

    public ArrayList<Task> getIncompleteTasks(){
        ArrayList<Task> incompleteTasks = new ArrayList<>();

        for (Task task : tasks ){
            if(!task.isCompleted()){
                incompleteTasks.add(task);
            }
        }
        return incompleteTasks;
    }

    public ArrayList<Task> getTasksByPriority(Priority priority){
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks){
            if(task.getPriority() == priority){
                matchingTasks.add(task);
            } 
        }
        return matchingTasks;
    }

    public ArrayList<Task> searchTasksByTitle(String searchTitle){
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String searchTitleLower = searchTitle.toLowerCase(); 
        
        for (Task task : tasks){
            String taskTitleLower = task.getTitle().toLowerCase(); //changes both title and searched title to lower case - makes the search case insensitive 
            if(taskTitleLower.contains(searchTitleLower)){
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    public void sortTasksByDueDate() { //Comparator comparing -> Compare objects using one specific value from each object
        tasks.sort(Comparator.comparing(Task::getDueDate)); //For each Task, use its getDueDate() method as the thing to compare. (:: method reference)
    }

    public void sortTasksByPriority() {
        tasks.sort(Comparator.comparing(Task::getPriority).reversed()); //reverses enum order so HIGH priority tasks appear first
    }

    public void sortTasksByCompletionStatus() {
        tasks.sort(Comparator.comparing(Task::isCompleted)); //booleans sort (false -> true) so incomplete(false) tasks go first automatically
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