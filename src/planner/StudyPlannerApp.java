package planner;

import java.time.LocalDate;

/**
 * Temporary test app for testing TaskManager methods. 
 * 
 * @author  Lina
 */
public class StudyPlannerApp {
    
    private TaskManager manager;
    private FileHandler fileHandler;
    public static void main(String[] args) {
        
        TaskManager manager = new TaskManager();

        Task mathTask = new Task("Math Quiz", LocalDate.of(2026, 5, 13), Priority.HIGH, false);
        Task engTask = new Task("English Essay", LocalDate.of(2026, 5, 15), Priority.LOW, false);
        Task csTask = new Task("CS1410 Project", LocalDate.of(2026, 5, 30), Priority.MEDIUM, false);

        manager.addTask(csTask);
        manager.addTask(engTask);
        manager.addTask(mathTask);

        manager.markComplete(2);

        System.out.println();
        System.out.println(manager);
        System.out.println();

        manager.markComplete(1);
        manager.markIncomplete(2);

        System.out.println(manager);
        System.out.println();

        manager.editTaskTitle(1, "English FINAL Essay");
        manager.editTaskDueDate(2, LocalDate.of(2026, 6, 2));
        manager.editTaskPriority(0, Priority.MEDIUM);
        System.out.println(manager);
        System.out.println();

        System.out.println(manager.getCompletedTasks());
        System.out.println(manager.getIncompleteTasks());
        System.out.println();

        manager.editTaskPriority(2, Priority.HIGH);
        System.out.println("HIGH Priority:" + manager.getTasksByPriority(Priority.HIGH));
        System.out.println("MEDIUM Priority:" + manager.getTasksByPriority(Priority.MEDIUM));
        System.out.println("LOW Priority:" + manager.getTasksByPriority(Priority.LOW));
        System.out.println();

        System.out.println("Search math: " + manager.searchTasksByTitle("math"));
        System.out.println("Search ESSay: " + manager.searchTasksByTitle("ESSay"));
        System.out.println("Search ProJecT: " + manager.searchTasksByTitle("ProJecT"));
        System.out.println("Search bio: " + manager.searchTasksByTitle("bio"));
        System.out.println();

        System.out.println("Before sorting by due date:");
        System.out.println(manager);

        manager.sortTasksByDueDate();

        System.out.println();
        System.out.println("After sorting by due date:");
        System.out.println(manager);

        System.out.println();
        System.out.println("Before sorting by priority:");
        System.out.println(manager);

        manager.sortTasksByPriority();

        System.out.println();
        System.out.println("After sorting by priority:");
        System.out.println(manager);

        System.out.println();
        System.out.println("Before sorting by completion:");
        System.out.println(manager);

        manager.sortTasksByCompletionStatus();

        System.out.println();
        System.out.println("After sorting by completion:");
        System.out.println(manager);


    }
}