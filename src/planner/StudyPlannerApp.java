package planner;

import java.time.LocalDate;

/**
 * TODO
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

        manager.addTask(mathTask);
        manager.addTask(engTask);
        manager.addTask(csTask);

        manager.markComplete(2);

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


    }
}