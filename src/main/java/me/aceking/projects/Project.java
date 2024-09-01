package me.aceking.projects;

import jakarta.persistence.*;
import lombok.*;
import me.aceking.teams.Team;
import me.aceking.users.User;

import java.time.LocalDate;
import java.util.List;

// Project POJO
@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate dueDate;

    @ManyToMany
    @JoinTable(
            name = "project_members",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> members;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}