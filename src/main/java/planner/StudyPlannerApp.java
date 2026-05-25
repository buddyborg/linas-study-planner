package planner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Starts the study planner app.
 * Loads saved tasks, displays the current task list, and saves tasks before closing.
 *
 * @author Lina
 */
public class StudyPlannerApp extends Application {

    private TaskManager manager;
    private FileHandler fileHandler;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        manager = new TaskManager();
        fileHandler = new FileHandler("tasks.txt");

        ArrayList<Task> loadedTasks = fileHandler.loadTasks(); //load tasks
        manager.addTasks(loadedTasks); //add loadedTasks to manager

        Label titleLabel = new Label("Tasks:");
        ListView<String> taskListView = new ListView<>(); //ListView = horizontal or vertical list of items that user can select/interact with

        for(Task task: manager.getTasks()){
            taskListView.getItems().add(task.getTitle()); //Task Object -> title text
        }

        VBox vbox = new VBox(); //vbox = layout pane that arranges its child nodes in vertical column from top to bottom
        vbox.getChildren().addAll(titleLabel, taskListView);

        Scene scene = new Scene(vbox, 500, 400);

        stage.setTitle("Lina's Study Planner");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> fileHandler.saveTasks(manager.getTasks())); //when close happens, save tasks
        stage.show();

    }
}