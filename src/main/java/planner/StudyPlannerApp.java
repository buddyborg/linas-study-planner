package planner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
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
    private ListView<String> taskListView; //ListView = horizontal or vertical list of items that user can select/interact with

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
        taskListView = new ListView<>();

        Button backBtn = new Button("Back to Dashboard");
        backBtn.setOnAction(event -> showDashboard(stage));

        Button addTaskBtn = new Button("Add New Task");
        addTaskBtn.setOnAction(event -> showAddTaskWindow());

        Button deleteTaskBtn = new Button("Remove Selected Task");
        deleteTaskBtn.setOnAction(event -> {
            int selectedIndex = taskListView.getSelectionModel().getSelectedIndex(); //asks list which row is currently selected

            if (selectedIndex == -1){ //if no row selected, JavaFX returns -1
                Alert removeTaskAlert = new Alert(Alert.AlertType.ERROR);

                removeTaskAlert.setTitle("Missing Task Selection");
                removeTaskAlert.setHeaderText("Could not remove task");
                removeTaskAlert.setContentText("Please select a task to remove.");
                removeTaskAlert.showAndWait();

                return; //stops the button action because no task was selected
            }

            manager.removeTask(selectedIndex);
            refreshTaskList(); //visually shows the change
            fileHandler.saveTasks(manager.getTasks()); //save so if user closes and then reopens deleted task stays deleted
        });

        refreshTaskList();

        VBox vbox = new VBox(); //vbox = layout pane that arranges its child nodes in vertical column from top to bottom
        vbox.getChildren().addAll(titleLabel, taskListView, backBtn, addTaskBtn, deleteTaskBtn);

        Scene scene = new Scene(vbox, 500, 400);

        stage.setTitle("Lina's Study Planner");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> fileHandler.saveTasks(manager.getTasks())); //when close happens, save tasks
    }

    public void refreshTaskList(){
        taskListView.getItems().clear(); //clears taskListView

        for(Task task: manager.getTasks()){
            taskListView.getItems().add(task.toString()); //Task Object -> text
        }

    }

    public void showAddTaskWindow(){

        Stage addTaskStage = new Stage();
        Label topLbl = new Label("Add New Task");
        Label titleLbl = new Label("Task Name:");
        TextField addTaskTitle = new TextField();
        Label dateLbl = new Label("Due Date:");
        DatePicker addDueDate = new DatePicker();
        Label priorityLbl = new Label("Priority:");
        ComboBox<Priority> addPriority = new ComboBox<>();
        addPriority.getItems().addAll(Priority.LOW, Priority.MEDIUM, Priority.HIGH); //adds into combo box's list
        addPriority.setValue(Priority.MEDIUM);
        Label statusLbl = new Label("Completed?:");
        CheckBox addCompletedStatus = new CheckBox("Complete");
        Button addTaskBtn = new Button("Add Task");
        addTaskBtn.setOnAction(event -> {
            String title = addTaskTitle.getText().trim(); //using trim so that spaces don't get stored
            LocalDate dueDate = addDueDate.getValue();
            Priority priority = addPriority.getValue();
            boolean completed = addCompletedStatus.isSelected();

            String errorMessage = "";

            if(title.isBlank()){
                errorMessage += "Please enter a task name.\n";
            }

            if(dueDate == null){
                errorMessage += "Please choose a due date.";
            }

            if (!errorMessage.isEmpty())  {
                Alert taskAlert = new Alert(Alert.AlertType.ERROR);

                taskAlert.setTitle("Missing Task Information");
                taskAlert.setHeaderText("Could not add task");
                taskAlert.setContentText(errorMessage);
                taskAlert.showAndWait();

                return; // Stops the button action so the bad task does not get created or saved
            }

            Task newTask = new Task(title, dueDate, priority, completed);
            manager.addTask(newTask);

            refreshTaskList();

            fileHandler.saveTasks(manager.getTasks());

            addTaskStage.close();

        });
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(event -> addTaskStage.close());

        VBox vbox = new VBox();
        vbox.getChildren().addAll(topLbl, titleLbl, addTaskTitle, dateLbl, addDueDate, priorityLbl, addPriority,
                statusLbl, addCompletedStatus, addTaskBtn, cancelBtn);

        Scene scene = new Scene(vbox, 300, 500);

        addTaskStage.setTitle("Add New Task");
        addTaskStage.setScene(scene);
        addTaskStage.setOnCloseRequest(event -> addTaskStage.close());
        addTaskStage.show();
    }

}