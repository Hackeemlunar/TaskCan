package me.aceking.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for {@link me.aceking.users.User}
 */
public record UpdateUserDto(String username,
                            String password,
                            @Email String email,
                            String fullName
                            ) {}