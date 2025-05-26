package com.ganttchart.controller;

import com.ganttchart.model.Task;
import com.ganttchart.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Combination of @Controller and @ResponseBody. Returns data directly in response body.
@RequestMapping("/api/tasks") // Base path for all task-related endpoints
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Endpoint to create a new task
    // POST /api/tasks
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        // Add validation for the task object if necessary
        Task createdTask = taskService.saveTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    // Endpoint to get all tasks
    // GET /api/tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // Endpoint to get a single task by ID
    // GET /api/tasks/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> taskOptional = taskService.getTaskById(id);
        return taskOptional.map(task -> new ResponseEntity<>(task, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to update an existing task
    // PUT /api/tasks/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Optional<Task> taskOptional = taskService.getTaskById(id);
        if (taskOptional.isPresent()) {
            Task existingTask = taskOptional.get();
            existingTask.setName(taskDetails.getName());
            existingTask.setStartDate(taskDetails.getStartDate());
            existingTask.setEndDate(taskDetails.getEndDate());
            // Update other fields as necessary
            Task updatedTask = taskService.saveTask(existingTask);
            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to delete a task
    // DELETE /api/tasks/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) { // Catch potential exceptions e.g. if task to delete not found
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
