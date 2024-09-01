package me.aceking.tasks;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Add a new task
    @PostMapping("/tasks")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }
}
