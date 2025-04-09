package me.aceking.users;

import me.aceking.users.dto.CreateUserDto;
import me.aceking.users.dto.UpdateUserDto;
import me.aceking.users.dto.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    UserResponseDto createUserByAdmin(User user);
    UserResponseDto getUserById(Long id);
    UserResponseDto createUser(CreateUserDto user);
    UserResponseDto getUserByUsername(String username);
    UserResponseDto getUserByEmail(String email);
    UserResponseDto updateUser(Long id, UpdateUserDto userDto);

    void deleteUser(Long id);
    void deleteByUsername(String username);

    Page<UserResponseDto> getUsers(Pageable pageable);

}
