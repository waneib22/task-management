package com.waneib22.taskmanagement;

import com.waneib22.taskmanagement.exception.TaskNotFounded;
import com.waneib22.taskmanagement.model.Task;
import com.waneib22.taskmanagement.repository.TaskRepository;
import com.waneib22.taskmanagement.repository.UserInfoRepository;
import com.waneib22.taskmanagement.service.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class TestServiceTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private UserInfoRepository userInfoRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    private TaskService taskService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        taskService = new TaskService(taskRepository,
                userInfoRepository,
                passwordEncoder);
    }

    @Test
    public void testGetTaskById_ExistingId_ReturnsTask() {
        // Arrange
        Long taskId = 1L;
        Task task = new Task(taskId, "Task 1",
                "Description 1", null,
                false);
        Mockito.when(taskRepository.findById(taskId))
                .thenReturn(Optional.of(task));

        // Act
        Task result = taskService.getTaskById(taskId);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(taskId, result.getId());
        Assertions.assertEquals("Task 1", result.getTitle());
        Assertions.assertEquals("Description 1",
                result.getDescription());
        Assertions.assertFalse(result.isCompleted());

        Mockito.verify(taskRepository, Mockito.times(1))
                .findById(taskId);
    }

    @Test
    public void testGetTaskById_NonExistingId_ThrowsException() {
        // Arrange
        Long taskId = 1L;
        Mockito.when(taskRepository.findById(taskId))
                .thenReturn(Optional.empty());

        // Act and assert
        Assertions.assertThrows(TaskNotFounded.class,
                () -> taskService.getTaskById(taskId));

        Mockito.verify(taskRepository, Mockito.times(1))
                                                    .findById(taskId);
    }

}
