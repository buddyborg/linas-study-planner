package planner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Starts the study planner app.
 * Loads saved tasks and manages navigation between the dashboard,
 * task list and add task window.
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

        showDashboard(stage);
        stage.show();
    }

    public void showDashboard(Stage stage){

        Label dashboardLabel = new Label("Lina's Dashboard");
        Button taskViewBtn = new Button("View Tasks");

        taskViewBtn.setOnAction(e -> showTaskList(stage));

        VBox vbox = new VBox();
        vbox.getChildren().addAll(dashboardLabel, taskViewBtn);

        Scene scene = new Scene(vbox, 500, 400);

        stage.setTitle("Lina's Study Planner");
        stage.setScene(scene);
    }

    public void showTaskList(Stage stage){

        Label titleLabel = new Label("Tasks:");
        ListView<String> taskListView = new ListView<>(); //ListView = horizontal or vertical list of items that user can select/interact with

        Button backBtn = new Button("Back to Dashboard");
        backBtn.setOnAction(event -> showDashboard(stage));

        Button addTaskBtn = new Button("Add New Task");
        addTaskBtn.setOnAction(event -> showAddTaskWindow());

        for(Task task: manager.getTasks()){
            taskListView.getItems().add(task.getTitle()); //Task Object -> title text
        }

        VBox vbox = new VBox(); //vbox = layout pane that arranges its child nodes in vertical column from top to bottom
        vbox.getChildren().addAll(titleLabel, taskListView, backBtn, addTaskBtn);

        Scene scene = new Scene(vbox, 500, 400);

        stage.setTitle("Lina's Study Planner");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> fileHandler.saveTasks(manager.getTasks())); //when close happens, save tasks
    }

    public void showAddTaskWindow(){

        Stage addTaskStage = new Stage();
        Label label = new Label("Add Task Screen");

        VBox vbox = new VBox();
        vbox.getChildren().addAll(label);

        Scene scene = new Scene(vbox, 300, 500);

        addTaskStage.setTitle("Add New Task");
        addTaskStage.setScene(scene);
        addTaskStage.setOnCloseRequest(event -> addTaskStage.close());
        addTaskStage.show();
    }

}