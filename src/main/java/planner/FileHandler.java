package planner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles saving and loading planner tasks from a text file.
 * Converts Task objects to file lines and rebuilds Task objects when loading.
 *
 * @author Lina
 */
public class FileHandler {

    private String filePath;

    public FileHandler(String filePath){
        this.filePath = filePath;
    }

    public void saveTasks(ArrayList<Task> tasks) {

        try(PrintWriter out = new PrintWriter(new File(filePath))) {
            for (Task task: tasks){
                out.println(task.getTitle() + "|" + task.getDueDate() + "|" + task.getPriority() + "|" + task.isCompleted()); //use println when wanting to write one line
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }

        
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> loadedTasks = new ArrayList<>(); //creates empty list where loaded tasks go

        try (Scanner sc = new Scanner(new File(filePath))) { //opens the saved file

            while (sc.hasNextLine()) { //keep reading until no more saved task lines
                String line = sc.nextLine(); //reads one line
                String[] parts = line.split("\\|"); //breaks line apart using |


                if (parts.length == 4) {
                    String title = parts[0]; //converts all pieces -> text into real Java values
                    LocalDate dueDate = LocalDate.parse(parts[1]);
                    Priority priority = Priority.valueOf(parts[2]);
                    boolean completed = Boolean.parseBoolean(parts[3]);

                    Task task = new Task(title, dueDate, priority, completed); //create task
                    loadedTasks.add(task); //add to loadedTasks

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No saved tasks found. :(");
        }

        return loadedTasks;
    }
}