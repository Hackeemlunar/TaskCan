package me.aceking.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link me.aceking.users.User}
 */
public record CreateUserDto(@NotNull String username,
                            @NotNull String password,
                            @NotNull @Email String email,
                            @NotNull String fullName
                            ) {}