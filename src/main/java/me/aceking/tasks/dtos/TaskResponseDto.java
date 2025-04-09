package me.aceking.tasks.dtos;

import lombok.Builder;
import me.aceking.tasks.TaskPriority;
import me.aceking.tasks.TaskStatus;
import me.aceking.users.dto.UserResponseDto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link me.aceking.tasks.Task}
 */
@Builder
public record TaskResponseDto(Long id, String title, UserResponseDto creator, String description, TaskStatus status,
                              TaskPriority priority, LocalDateTime dueDate) {
}