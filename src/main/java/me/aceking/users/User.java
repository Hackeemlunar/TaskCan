package me.aceking.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

// User POJO
@Entity
@Table(name = "t_user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(20) default 'USER'")
    private UserRole role = UserRole.USER;

    public User(@NotNull String username, @NotNull String password, @NotNull @Email String email, @NotNull String s) {
    }
}