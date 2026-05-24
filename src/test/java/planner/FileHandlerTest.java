package planner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {

    @TempDir
    Path tempDir;

    @Test
    void saveTasksWritesTasksToFile() throws Exception {
        Path filePath = tempDir.resolve("tasks.txt");
        FileHandler fileHandler = new FileHandler(filePath.toString());
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Math Quiz", LocalDate.of(2026, 5, 13), Priority.HIGH, false));
        tasks.add(new Task("English Essay", LocalDate.of(2026, 5, 15), Priority.LOW, true));

        fileHandler.saveTasks(tasks);

        List<String> lines = Files.readAllLines(filePath);
        assertEquals(2, lines.size());
        assertEquals("Math Quiz|2026-05-13|HIGH|false", lines.get(0));
        assertEquals("English Essay|2026-05-15|LOW|true", lines.get(1));
    }

    @Test
    void loadTasksReadsTasksFromFile() throws Exception {
        Path filePath = tempDir.resolve("tasks.txt");
        Files.write(filePath, List.of(
                "Math Quiz|2026-05-13|HIGH|false",
                "English Essay|2026-05-15|LOW|true"
        ));
        FileHandler fileHandler = new FileHandler(filePath.toString());

        ArrayList<Task> loadedTasks = fileHandler.loadTasks();

        assertEquals(2, loadedTasks.size());
        assertEquals("Math Quiz", loadedTasks.get(0).getTitle());
        assertEquals(LocalDate.of(2026, 5, 13), loadedTasks.get(0).getDueDate());
        assertEquals(Priority.HIGH, loadedTasks.get(0).getPriority());
        assertFalse(loadedTasks.get(0).isCompleted());
        assertEquals("English Essay", loadedTasks.get(1).getTitle());
        assertEquals(LocalDate.of(2026, 5, 15), loadedTasks.get(1).getDueDate());
        assertEquals(Priority.LOW, loadedTasks.get(1).getPriority());
        assertTrue(loadedTasks.get(1).isCompleted());
    }

    @Test
    void loadTasksSkipsLinesThatDoNotHaveFourParts() throws Exception {
        Path filePath = tempDir.resolve("tasks.txt");
        Files.write(filePath, List.of(
                "Bad line",
                "CS1410 Project|2026-05-30|MEDIUM|false"
        ));
        FileHandler fileHandler = new FileHandler(filePath.toString());

        ArrayList<Task> loadedTasks = fileHandler.loadTasks();

        assertEquals(1, loadedTasks.size());
        assertEquals("CS1410 Project", loadedTasks.get(0).getTitle());
    }

    @Test
    void loadTasksReturnsEmptyListWhenFileDoesNotExist() {
        Path filePath = tempDir.resolve("missing-tasks.txt");
        FileHandler fileHandler = new FileHandler(filePath.toString());

        ArrayList<Task> loadedTasks = fileHandler.loadTasks();

        assertTrue(loadedTasks.isEmpty());
    }
}