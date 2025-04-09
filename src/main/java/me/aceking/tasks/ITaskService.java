package me.aceking.tasks;

import me.aceking.tasks.dtos.CreateTaskDto;
import me.aceking.tasks.dtos.TaskResponseDto;
import me.aceking.tasks.dtos.UpdateTaskDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface ITaskService {
    TaskResponseDto createTask(CreateTaskDto task);
    TaskResponseDto createTaskByAdmin(CreateTaskDto task);
    TaskResponseDto getTaskById(Long id);
    Page<TaskResponseDto> getTasksByTeamId(Pageable pageable, Long teamId);
    Page<TaskResponseDto> getTasksByUserId(Pageable pageable, Long userId);
    Page<TaskResponseDto> getTasks(Pageable pageable);
    TaskResponseDto updateTask(Long id, UpdateTaskDto task);
    void deleteTask(Long id);
    TaskResponseDto updateTaskStatus(Long id, TaskStatus status);
    TaskResponseDto updateTaskPriority(Long id, TaskPriority priority);
    TaskResponseDto updateTaskDueDate(Long id, String dueDate);

    TaskResponseDto updateTaskDueDate(Long id, LocalDateTime dueDate);

    TaskResponseDto updateTaskAssignee(Long id, Long assigneeId);
    TaskResponseDto updateTaskTeam(Long id, Long teamId);
    TaskResponseDto updateTaskTitle(Long id, String title);
}