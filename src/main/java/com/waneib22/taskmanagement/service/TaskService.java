package com.waneib22.taskmanagement.service;

import com.waneib22.taskmanagement.exception.TaskNotFounded;
import com.waneib22.taskmanagement.model.Task;
import com.waneib22.taskmanagement.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFounded("task doesn't exist"));
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Integer id, Task updatedTask) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFounded("Task not found"));
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setDueDate(updatedTask.getDueDate());
        return taskRepository.save(task);
    }

    public void deleteTaskById(Integer id) {
        taskRepository.deleteById(id);
    }
}
