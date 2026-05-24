package planner;

import java.util.ArrayList;

/**
 * Starts the study planner app.
 * Loads saved tasks, displays the current task list, and saves tasks before closing.
 *
 * @author Lina
 */
public class StudyPlannerApp {

    private TaskManager manager;
    private FileHandler fileHandler;
    public static void main(String[] args) {

        TaskManager manager = new TaskManager();
        FileHandler fileHandler = new FileHandler("tasks.txt");


        //fileHandler.saveTasks(manager.getTasks()); //action: writes tasks into tasks.txt
        ArrayList<Task> loadedTasks = fileHandler.loadTasks(); //question: gives back tasks from tasks.txt
        manager.addTasks(loadedTasks);

        System.out.println("Current tasks:");
        System.out.println(manager);

        fileHandler.saveTasks(manager.getTasks());

    }
}