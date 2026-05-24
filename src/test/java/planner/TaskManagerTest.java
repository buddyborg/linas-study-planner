package planner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {

    private TaskManager manager;
    private Task mathTask;
    private Task essayTask;
    private Task projectTask;

    @BeforeEach
    void setUp() {
        manager = new TaskManager();
        mathTask = new Task("Math Quiz", LocalDate.of(2026, 5, 13), Priority.HIGH, false);
        essayTask = new Task("English Essay", LocalDate.of(2026, 5, 15), Priority.LOW, true);
        projectTask = new Task("CS1410 Project", LocalDate.of(2026, 5, 30), Priority.MEDIUM, false);
    }

    @Test
    void addTaskAddsOneTaskToList() {
        manager.addTask(mathTask);

        assertEquals(1, manager.getTasks().size());
        assertSame(mathTask, manager.getTasks().get(0));
    }

    @Test
    void addTasksAddsAllTasksToList() {
        ArrayList<Task> tasksToAdd = new ArrayList<>();
        tasksToAdd.add(mathTask);
        tasksToAdd.add(essayTask);

        manager.addTasks(tasksToAdd);

        assertEquals(2, manager.getTasks().size());
        assertEquals("Math Quiz", manager.getTasks().get(0).getTitle());
        assertEquals("English Essay", manager.getTasks().get(1).getTitle());
    }

    @Test
    void removeTaskDeletesTaskAtIndex() {
        addSampleTasks();

        manager.removeTask(1);

        assertEquals(2, manager.getTasks().size());
        assertEquals("Math Quiz", manager.getTasks().get(0).getTitle());
        assertEquals("CS1410 Project", manager.getTasks().get(1).getTitle());
    }

    @Test
    void markCompleteAndIncompleteUpdateSelectedTask() {
        addSampleTasks();

        manager.markComplete(0);
        assertTrue(manager.getTasks().get(0).isCompleted());

        manager.markIncomplete(1);
        assertFalse(manager.getTasks().get(1).isCompleted());
    }

    @Test
    void editMethodsUpdateSelectedTaskFields() {
        addSampleTasks();

        manager.editTaskTitle(0, "Final Math Quiz");
        manager.editTaskDueDate(1, LocalDate.of(2026, 6, 1));
        manager.editTaskPriority(2, Priority.HIGH);

        assertEquals("Final Math Quiz", manager.getTasks().get(0).getTitle());
        assertEquals(LocalDate.of(2026, 6, 1), manager.getTasks().get(1).getDueDate());
        assertEquals(Priority.HIGH, manager.getTasks().get(2).getPriority());
    }

    @Test
    void getCompletedTasksReturnsOnlyCompletedTasks() {
        addSampleTasks();

        ArrayList<Task> completedTasks = manager.getCompletedTasks();

        assertEquals(1, completedTasks.size());
        assertEquals("English Essay", completedTasks.get(0).getTitle());
    }

    @Test
    void getIncompleteTasksReturnsOnlyIncompleteTasks() {
        addSampleTasks();

        ArrayList<Task> incompleteTasks = manager.getIncompleteTasks();

        assertEquals(2, incompleteTasks.size());
        assertEquals("Math Quiz", incompleteTasks.get(0).getTitle());
        assertEquals("CS1410 Project", incompleteTasks.get(1).getTitle());
    }

    @Test
    void getTasksByPriorityReturnsOnlyMatchingPriority() {
        addSampleTasks();

        ArrayList<Task> highPriorityTasks = manager.getTasksByPriority(Priority.HIGH);

        assertEquals(1, highPriorityTasks.size());
        assertEquals("Math Quiz", highPriorityTasks.get(0).getTitle());
    }

    @Test
    void searchTasksByTitleFindsMatchesIgnoringCase() {
        addSampleTasks();

        ArrayList<Task> matchingTasks = manager.searchTasksByTitle("essay");

        assertEquals(1, matchingTasks.size());
        assertEquals("English Essay", matchingTasks.get(0).getTitle());
    }

    @Test
    void searchTasksByTitleReturnsEmptyListWhenNoTasksMatch() {
        addSampleTasks();

        assertTrue(manager.searchTasksByTitle("biology").isEmpty());
    }

    @Test
    void sortTasksByDueDateOrdersEarliestDateFirst() {
        manager.addTask(projectTask);
        manager.addTask(mathTask);
        manager.addTask(essayTask);

        manager.sortTasksByDueDate();

        assertEquals("Math Quiz", manager.getTasks().get(0).getTitle());
        assertEquals("English Essay", manager.getTasks().get(1).getTitle());
        assertEquals("CS1410 Project", manager.getTasks().get(2).getTitle());
    }

    @Test
    void sortTasksByPriorityOrdersHighPriorityFirst() {
        manager.addTask(essayTask);
        manager.addTask(projectTask);
        manager.addTask(mathTask);

        manager.sortTasksByPriority();

        assertEquals(Priority.HIGH, manager.getTasks().get(0).getPriority());
        assertEquals(Priority.MEDIUM, manager.getTasks().get(1).getPriority());
        assertEquals(Priority.LOW, manager.getTasks().get(2).getPriority());
    }

    @Test
    void sortTasksByCompletionStatusOrdersIncompleteTasksFirst() {
        manager.addTask(essayTask);
        manager.addTask(mathTask);
        manager.addTask(projectTask);

        manager.sortTasksByCompletionStatus();

        assertFalse(manager.getTasks().get(0).isCompleted());
        assertFalse(manager.getTasks().get(1).isCompleted());
        assertTrue(manager.getTasks().get(2).isCompleted());
    }

    private void addSampleTasks() {
        manager.addTask(mathTask);
        manager.addTask(essayTask);
        manager.addTask(projectTask);
    }
}