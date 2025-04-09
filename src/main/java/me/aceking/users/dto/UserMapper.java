package me.aceking.users.dto;

import me.aceking.users.User;

public class UserMapper {
    public static UserResponseDto toUserResponseDto(User user) {
        return new UserResponseDto(user.getId(), user.getUsername(), user.getFullName());
    }

    public static CreateUserDto toCreateUserDto(User user) {
        return new CreateUserDto(user.getUsername(), user.getPassword(), user.getEmail(), user.getFullName());
    }

    public static User toUser(CreateUserDto user) {
        return new User(user.username(), user.password(), user.email(), user.fullName());
    }

    public static UserIdDto toUserIdDto(User user) {
        return new UserIdDto(user.getId());
    }

    public static User userIdDtoToUser(UserIdDto user) {
        return User.builder().id(user.id()).build();
    }


}
