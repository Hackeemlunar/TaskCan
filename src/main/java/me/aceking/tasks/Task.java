package me.aceking.tasks;


import jakarta.persistence.*;
import lombok.*;
import me.aceking.projects.Project;
import me.aceking.users.User;
import java.time.LocalDateTime;
import java.util.List;

// Task POJO
@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // Optional: For convenient object creation
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;

    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "assignee_id") // Foreign key column name
    private User assignee;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ElementCollection
    @CollectionTable(name = "task_tags", joinColumns = @JoinColumn(name = "task_id"))
    @Column(name = "tag")
    private List<String> tags;

    @ManyToOne
    @JoinColumn(name = "parent_task_id")
    private Task parentTask;

    @OneToMany(mappedBy = "parentTask")
    private List<Task> subtasks;

    @OneToMany(mappedBy = "task")
    private List<Comment> comments;
}
