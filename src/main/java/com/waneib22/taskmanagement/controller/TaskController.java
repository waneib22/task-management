package com.waneib22.taskmanagement.controller;

import com.waneib22.taskmanagement.model.Task;
import com.waneib22.taskmanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/welcome")
    public String welcome() {
        return " *** Welcome to our Task web application ***";
    }

    @GetMapping("/get/all")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/get/{id}")
    public Task getTaskById(@PathVariable Integer id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/update/{id}")
    public Task updateTask(@PathVariable Integer id,
                           @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTask(@PathVariable Integer id) {
        taskService.deleteTaskById(id);
    }

}
