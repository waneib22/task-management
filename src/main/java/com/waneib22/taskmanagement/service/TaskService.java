package com.waneib22.taskmanagement.service;

import com.waneib22.taskmanagement.exception.TaskNotFounded;
import com.waneib22.taskmanagement.model.Task;
import com.waneib22.taskmanagement.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }


    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFounded("Task not founded"));
    }


    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public void updateTask(Long id, Task updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFounded("Task not found"));

        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setDueDate(updatedTask.getDueDate());

        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFounded("Task not founded"));

        taskRepository.delete(task);
    }
}
