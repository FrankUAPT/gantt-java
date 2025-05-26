package com.ganttchart.service;

import com.ganttchart.model.Task;
import com.ganttchart.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marks this class as a Spring service component
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired // Dependency injection of TaskRepository
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Method to create or update a task
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    // Method to get all tasks
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Method to get a task by its ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Method to delete a task by its ID
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // More complex business logic can be added here later, for example:
    // - Calculating task duration.
    // - Handling dependencies.
    // - Updating progress based on sub-tasks.
}
