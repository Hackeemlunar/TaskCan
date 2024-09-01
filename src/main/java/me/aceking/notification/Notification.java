package me.aceking.notification;

import jakarta.persistence.*;
import lombok.*;
import me.aceking.tasks.Task;
import me.aceking.users.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String message;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    private boolean read;
}
