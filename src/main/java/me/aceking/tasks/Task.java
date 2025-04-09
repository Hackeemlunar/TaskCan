package me.aceking.tasks;


import jakarta.persistence.*;
import lombok.*;
import me.aceking.projects.Project;
import me.aceking.users.User;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String title;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    private String description;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name = "status", columnDefinition = "varchar(20) default 'TO_DO'")
    private TaskStatus status = TaskStatus.TO_DO;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name = "priority", columnDefinition = "varchar(20) default 'LOW'")
    private TaskPriority priority = TaskPriority.LOW;

    private LocalDateTime dueDate;

    @Column(updatable = false, name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "assignee_id") // Foreign key column name
    private User assignee;


    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "parent_task_id")
    private Task parentTask;

    @OneToMany(mappedBy = "parentTask")
    @ToString.Exclude
    private List<Task> subtasks;

}
