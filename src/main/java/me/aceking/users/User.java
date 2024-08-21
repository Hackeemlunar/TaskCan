package me.aceking.users;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Builder
@Getter
@Setter
@Entity
@Table(name = "user")
@AllArgsConstructor @RequiredArgsConstructor @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NonNull
    @Column(name = "full_name", nullable = false)
    String fullName;

    @NonNull
    @Column(name = "username", nullable = false)
    String username;

    @Column(name = "password", nullable = false)
    String password;

    @NonNull
    @Column(name = "email", nullable = false)
    String email;

    String role;

    String profilePicture;

    ZonedDateTime createdAt;

    ZonedDateTime updatedAt;

    ZonedDateTime lastLogin;

}