package me.aceking.users.dto;

import lombok.Builder;

/**
 * DTO for {@link me.aceking.users.User}
 */
@Builder
public record UserResponseDto(Long id, String username, String fullName) {
}