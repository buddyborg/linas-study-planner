import java.util.ArrayList;

/**
 * TODO
 * 
 * @author  Lina
 */
public class FileHandler {

    private String filePath;

    public FileHandler(String filePath){
        this.filePath = filePath;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        
    }

    public ArrayList<Task> loadTasks() {
        return new ArrayList<Task>(); //FIX 
    }
}