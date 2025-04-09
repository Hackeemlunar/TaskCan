package me.aceking.tasks.dtos;

import jakarta.validation.constraints.NotNull;
import me.aceking.tasks.Task;
import me.aceking.users.User;
import me.aceking.users.dto.UserIdDto;
import me.aceking.users.dto.UserMapper;

public class TaskMapper {
    public static TaskResponseDto toTaskResponseDto(Task task) {
        return TaskResponseDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .creator(UserMapper.toUserResponseDto(task.getCreator()))
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .build();
    }

    public static Task toTask(CreateTaskDto task) {
        return Task.builder()
                .title(task.title())
                .creator(UserMapper.userIdDtoToUser(task.creator()))
                .status(task.status())
                .priority(task.priority())
                .dueDate(task.dueDate())
                .build();
    }

    public static Task childTaskToTask(CreateChildTaskDto task) {
        return Task.builder()
                .title(task.title())
                .creator(UserMapper.userIdDtoToUser(task.creator()))
                .status(task.status())
                .priority(task.priority())
                .dueDate(task.dueDate())
                .parentTask(parentTaskDtoToTask(task.parentTask()))
                .build();
    }

    public static Task updateTaskDtoToTask(UpdateTaskDto task) {
        return Task.builder()
                .title(task.title())
                .description(task.description())
                .status(task.status())
                .priority(task.priority())
                .dueDate(task.dueDate())
                .build();
    }

    public static Task parentTaskDtoToTask(ParentTaskDto task) {
        return Task.builder()
                .id(task.id())
                .build();
    }
}
