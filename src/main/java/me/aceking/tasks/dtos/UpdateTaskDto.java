package me.aceking.tasks.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import me.aceking.tasks.TaskPriority;
import me.aceking.tasks.TaskStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link me.aceking.tasks.Task}
 */
@Builder
public record UpdateTaskDto(String title, String description, @NotNull TaskStatus status, TaskPriority priority,
                            @FutureOrPresent LocalDateTime dueDate) {}