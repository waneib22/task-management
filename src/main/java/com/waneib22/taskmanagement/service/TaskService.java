package com.waneib22.taskmanagement.service;

import com.waneib22.taskmanagement.exception.TaskNotFounded;
import com.waneib22.taskmanagement.model.Task;
import com.waneib22.taskmanagement.model.UserInfo;
import com.waneib22.taskmanagement.repository.TaskRepository;
import com.waneib22.taskmanagement.repository.UserInfoRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    public TaskService(TaskRepository taskRepository,
                       UserInfoRepository userInfoRepository,
                       PasswordEncoder passwordEncoder) {
        this.taskRepository = taskRepository;
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
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

    public UserInfo addUser(UserInfo userInfo) {
        userInfo.setUsername(userInfo.getUsername());
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userInfoRepository.save(userInfo);
        return userInfoRepository.save(userInfo);
    }
}
