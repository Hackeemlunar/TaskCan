package me.aceking.tasks;

import me.aceking.exceptions.DeleteException;
import me.aceking.exceptions.ReadException;
import me.aceking.tasks.dtos.CreateTaskDto;
import me.aceking.tasks.dtos.TaskMapper;
import me.aceking.tasks.dtos.TaskResponseDto;
import me.aceking.tasks.dtos.UpdateTaskDto;
import me.aceking.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class TaskService implements ITaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResponseDto createTask(CreateTaskDto task) {
        Task newTask = TaskMapper.toTask(task);
        Task savedTask = taskRepository.save(newTask);
        return TaskMapper.toTaskResponseDto(savedTask);
    }

    @Override
    public TaskResponseDto createTaskByAdmin(CreateTaskDto task) {
        Task newTask = TaskMapper.toTask(task);
        Task savedTask = taskRepository.save(newTask);
        return TaskMapper.toTaskResponseDto(savedTask);
    }

    @Override
    public TaskResponseDto getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            return TaskMapper.toTaskResponseDto(task.get());
        }
        throw new ReadException("Task with id: " + id + " not found");
    }

    @Override
    public Page<TaskResponseDto> getTasksByTeamId(Pageable pageable, Long teamId) {
        return null;
    }

    @Override
    public Page<TaskResponseDto> getTasksByUserId(Pageable pageable, Long userId) {
        Page<Task> tasks = taskRepository.findTasksByCreatorIdOrAssigneeId(pageable, userId, userId);
        if (tasks.isEmpty()) {
            throw new ReadException("Tasks not found");
        }
        return tasks.map(TaskMapper::toTaskResponseDto);
    }

    @Override
    public Page<TaskResponseDto> getTasks(Pageable pageable) {
        Page<Task> tasks = taskRepository.findAll(pageable);
        if (tasks.isEmpty()) {
            throw new ReadException("Tasks not found");
        }
        return tasks.map(TaskMapper::toTaskResponseDto);
    }

    @Override
    public TaskResponseDto updateTask(Long id, UpdateTaskDto taskDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ReadException("Task with id: " + id + " not found"));

        if (taskDto.title() != null || !taskDto.title().isBlank()) {
            task.setTitle(taskDto.title());
        }
        if (taskDto.description() != null) {
            task.setDescription(taskDto.description());
        }
        if (taskDto.status() != null) {
            task.setStatus(taskDto.status());
        }
        if (taskDto.priority() != null) {
            task.setPriority(taskDto.priority());
        }
        if (taskDto.dueDate() != null) {
            task.setDueDate(taskDto.dueDate());
        }

        Task updatedTask = taskRepository.save(task);
        return TaskMapper.toTaskResponseDto(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            taskRepository.delete(task.get());
            return;
        }
        throw new DeleteException("Task with id: " + id + " not found");
    }

    @Override
    public TaskResponseDto updateTaskStatus(Long id, TaskStatus status) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setStatus(status);
            return TaskMapper.toTaskResponseDto(taskRepository.save(task));
        }
        throw new ReadException("Task with id: " + id + " not found");
    }

    @Override
    public TaskResponseDto updateTaskPriority(Long id, TaskPriority priority) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setPriority(priority);
            return TaskMapper.toTaskResponseDto(taskRepository.save(task));
        }
        throw new ReadException("Task with id: " + id + " not found");
    }

    @Override
    public TaskResponseDto updateTaskDueDate(Long id, String dueDate) {
        return null;
    }

    @Override
    public TaskResponseDto updateTaskDueDate(Long id, LocalDateTime dueDate) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setDueDate(dueDate);
            return TaskMapper.toTaskResponseDto(taskRepository.save(task));
        }
        throw new ReadException("Task with id: " + id + " not found");
    }

    @Override
    public TaskResponseDto updateTaskAssignee(Long id, Long assigneeId) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            User user = User.builder().id(assigneeId).build();
            task.setAssignee(user);
            return TaskMapper.toTaskResponseDto(taskRepository.save(task));
        }
        throw new ReadException("Task with id: " + id + " not found");
    }

    @Override
    public TaskResponseDto updateTaskTeam(Long id, Long teamId) {
        return null;
    }

    @Override
    public TaskResponseDto updateTaskTitle(Long id, String title) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setTitle(title);
            return TaskMapper.toTaskResponseDto(taskRepository.save(task));
        }
        throw new ReadException("Task with id: " + id + " not found");
    }
}