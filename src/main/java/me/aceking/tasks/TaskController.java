package me.aceking.tasks;

import lombok.RequiredArgsConstructor;
import me.aceking.tasks.dtos.CreateTaskDto;
import me.aceking.tasks.dtos.TaskResponseDto;
import me.aceking.tasks.dtos.UpdateTaskDto;
import me.aceking.users.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@Controller
public class TaskController {

    private final ITaskService taskService;
    private final IUserService userService;

    // Add a new task
    @GetMapping("/tasks/new")
    public String addNewTask(Model model) {
        model.addAttribute("users",
                userService.getUsers(PageRequest.of(0, 10)).getContent());
        return "tasks/newTask";
    }

    // Add a new task
    @PostMapping("/tasks/new")
    public String addTask(@ModelAttribute CreateTaskDto task, Model model) {
        TaskResponseDto response = taskService.createTask(task);
        model.addAttribute("task", response);
        return "redirect:/"; // Redirect to task list
    }

    // Get all tasks
    @GetMapping("/")
    public String getAllTasks(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id"));
        Page<TaskResponseDto> response = taskService.getTasks(pageable);
        model.addAttribute("tasks", response);
        return "tasks/index"; // Return "index" instead of "tasksView"
    }

    // Get a task by id
    @GetMapping("/tasks/id/{id}")
    public String getTaskById(@PathVariable Long id, Model model) {
        TaskResponseDto response = taskService.getTaskById(id);
        model.addAttribute("task", response);
        return "tasks/taskView";
    }

    // Get a task by username
    @GetMapping("/tasks/userid/{userid}")
    public String getTaskByUserId(@PathVariable Long userid,
                                  @RequestParam(defaultValue = "0") int page,
                                  Model model) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id"));
        Page<TaskResponseDto> response = taskService.getTasksByUserId(pageable, userid);
        model.addAttribute("tasks", response);
        model.addAttribute("userId", userid); // Add userId to the model
        return "tasks/index"; // Return "index" instead of "tasksView"
    }

    // Update a task
    @GetMapping("/tasks/{id}/edit")
    public String showUpdateTaskForm(@PathVariable Long id, Model model) {
        TaskResponseDto response = taskService.getTaskById(id);
        model.addAttribute("task", response);
        return "tasks/editTask"; // Render the edit form
    }

    @PostMapping("/tasks/{id}/edit")
    public String updateTask(@PathVariable Long id,
                             @ModelAttribute UpdateTaskDto task,
                             Model model) {
        TaskResponseDto response = taskService.updateTask(id, task);
        model.addAttribute("task", response);
        return "redirect:/tasks/id/" + id; // Redirect to task list
    }

    // Delete a task
    @PostMapping("/tasks/{id}") // Assuming you are using POST with _method for DELETE
    public String deleteTask(@PathVariable Long id, Model model) {
        taskService.deleteTask(id);
        return "redirect:/"; // Redirect to task list (adjust if needed)
    }
}