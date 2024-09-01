package me.aceking.users;

import jakarta.persistence.*;
import lombok.*;

// User POJO
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String fullName;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}