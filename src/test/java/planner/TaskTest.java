package planner;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void constructorStoresTaskDetails() {
        Task task = new Task("Math Quiz", LocalDate.of(2026, 5, 13), Priority.HIGH, false);

        assertEquals("Math Quiz", task.getTitle());
        assertEquals(LocalDate.of(2026, 5, 13), task.getDueDate());
        assertEquals(Priority.HIGH, task.getPriority());
        assertFalse(task.isCompleted());
    }

    @Test
    void markCompleteAndIncompleteChangeCompletedStatus() {
        Task task = new Task("Essay", LocalDate.of(2026, 5, 15), Priority.LOW, false);

        task.markComplete();
        assertTrue(task.isCompleted());

        task.markIncomplete();
        assertFalse(task.isCompleted());
    }

    @Test
    void settersEditTaskDetails() {
        Task task = new Task("Old Title", LocalDate.of(2026, 5, 1), Priority.LOW, false);

        task.setTitle("New Title");
        task.setDueDate(LocalDate.of(2026, 6, 2));
        task.setPriority(Priority.MEDIUM);

        assertEquals("New Title", task.getTitle());
        assertEquals(LocalDate.of(2026, 6, 2), task.getDueDate());
        assertEquals(Priority.MEDIUM, task.getPriority());
    }

    @Test
    void toStringIncludesTaskDetails() {
        Task task = new Task("Math Quiz", LocalDate.of(2026, 5, 13), Priority.HIGH, false);

        assertEquals("Math Quiz - Due Date: 2026-05-13 Priority: HIGH Completed?: false", task.toString());
    }
}