package com.waneib22.taskmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String title;
    private String description;
    @Column(nullable = false)
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Due date must be ")
    private LocalDate dueDate;
    private boolean completed;

    public Task(Long id,
                String description,
                LocalDate dueDate,
                boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    public void markAsIncomplete() {
        this.completed = false;
    }

    public boolean isOverdue() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.isAfter(dueDate) && !completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", completed=" + completed +
                '}';
    }

}