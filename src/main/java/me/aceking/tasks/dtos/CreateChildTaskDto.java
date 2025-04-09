package me.aceking.tasks.dtos;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import me.aceking.tasks.TaskPriority;
import me.aceking.tasks.TaskStatus;
import me.aceking.users.dto.UserIdDto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link me.aceking.tasks.Task}
 */
public record CreateChildTaskDto(String title, @NotNull UserIdDto creator, TaskStatus status, TaskPriority priority,
                                 @NotNull @FutureOrPresent LocalDateTime dueDate, ParentTaskDto parentTask) {}