package com.waneib22.taskmanagement;

import com.waneib22.taskmanagement.controller.TaskController;
import com.waneib22.taskmanagement.model.Task;
import com.waneib22.taskmanagement.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

public class TaskControllerTest {

    @Mock
    private TaskService taskService;
    private TaskController taskController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        taskController = new TaskController(taskService);
    }

    @Test
    public void testGetAllTasks_ReturnsListOfTasks() {
        // Arrange
        Task task1 = new Task(1L,
                "Task 1",
                "Descrition 1",
                null, false);
        Task task2 = new Task(2L,
                "Task 2",
                "Descrition 2",
                null, true);
        List<Task> tasks = Arrays.asList(task1, task2);
        Mockito.when(taskService.getAllTasks()).thenReturn(tasks);

        // Act
        List<Task> result = taskController.getAllTasks();

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(tasks, result);

        Mockito.verify(taskService, Mockito.times(1))
                .getAllTasks();
    }

    @Test
    public void testCreate_ValidTask_ReturnsCreatedStatus() {
        // Arrange
        Task task = new Task(1L,
                "Task 1",
                "Description 1",
                null, false);

        // Act
        ResponseEntity<Task> responseEntity = taskController.createTask(task);

        // Assert
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        Mockito.verify(taskService, Mockito.times(1))
                .createTask(task);
    }
}
