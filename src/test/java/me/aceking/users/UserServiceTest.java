package me.aceking.users;

import me.aceking.exceptions.*;
import me.aceking.exceptions.ReadException;
import me.aceking.users.dto.CreateUserDto;
import me.aceking.users.dto.UpdateUserDto;
import me.aceking.users.dto.UserMapper;
import me.aceking.users.dto.UserResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private IUserService userService = new UserService(userRepository);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser_Success() {
        // Given
        CreateUserDto createUserDto = new CreateUserDto("testuser", "password", "test@example.com", "Test User");
        User newUser = new User("testuser", "password", "test@example.com", "Test User");
        UserResponseDto userResponseDto = new UserResponseDto(1L, "testuser", "user");

        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(newUser);
        when(UserMapper.toUserResponseDto(any(User.class))).thenReturn(userResponseDto);

        // When
        UserResponseDto createdUser = userService.createUser(createUserDto);

        // Then
        assertNotNull(createdUser);
        assertEquals(userResponseDto, createdUser);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testCreateUser_UsernameTaken() {
        // Given
        CreateUserDto createUserDto = new CreateUserDto("existinguser", "password", "test@example.com", "Test User");

        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        // When & Then
        assertThrows(CreateException.class, () -> userService.createUser(createUserDto));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testCreateUser_EmailTaken() {
        // Given
        CreateUserDto createUserDto = new CreateUserDto("testuser", "password",
                "existing@example.com", "Test User");

        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        // When & Then
        assertThrows(CreateException.class, () -> userService.createUser(createUserDto));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testGetUserByUsername_Success() {
        // Given
        String username = "testuser";
        User existingUser = new User(username, "password", "test@example.com", "Test User");
        UserResponseDto userResponseDto = new UserResponseDto(1L, username, "Test User");

        when(userRepository.existsByUsername(username)).thenReturn(true);
        when(userRepository.findByUsername(username)).thenReturn(existingUser);
        when(UserMapper.toUserResponseDto(any(User.class))).thenReturn(userResponseDto);

        // When
        UserResponseDto retrievedUser = userService.getUserByUsername(username);

        // Then
        assertNotNull(retrievedUser);
        assertEquals(userResponseDto, retrievedUser);
    }

    @Test
    void testGetUserByUsername_NotFound() {
        // Given
        String username = "nonexistentuser";

        when(userRepository.existsByUsername(username)).thenReturn(false);

        // When & Then
        assertThrows(ReadException.class, () -> userService.getUserByUsername(username));
    }

    @Test
    void testGetUserById_Success() {
        // Given
        Long userId = 1L;
        User existingUser = new User("testuser", "password", "test@example.com", "Test User");
        UserResponseDto userResponseDto = new UserResponseDto(userId, "testuser", "Test");
        existingUser.setId(userId);

        when(userRepository.existsById(userId)).thenReturn(true);
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(UserMapper.toUserResponseDto(any(User.class))).thenReturn(userResponseDto);

        // When
        UserResponseDto retrievedUser = userService.getUserById(userId);

        // Then
        assertNotNull(retrievedUser);
        assertEquals(userResponseDto, retrievedUser);
    }

    @Test
    void testGetUserById_NotFound() {
        // Given
        Long userId = 1L;

        when(userRepository.existsById(userId)).thenReturn(false);

        // When & Then
        assertThrows(ReadException.class, () -> userService.getUserById(userId));
    }

    @Test
    void testGetUsers() {
        // Given
        List<User> users = Collections.singletonList(new User("testuser", "password", "test@example.com", "Test User"));
        Page<User> userPage = new PageImpl<>(users);
        Pageable pageable = Pageable.ofSize(10);

        when(userRepository.findAll(pageable)).thenReturn(userPage);

        // When
        Page<UserResponseDto> retrievedUsers = userService.getUsers(pageable);

        // Then
        assertNotNull(retrievedUsers);
        assertEquals(1, retrievedUsers.getTotalElements());
    }

    @Test
    void testUpdateUser_Success() {
        // Given
        Long userId = 1L;
        User existingUser = new User("testuser", "password", "test@example.com", "Test User");
        existingUser.setId(userId);
        UpdateUserDto updateUserDto = new UpdateUserDto("updateduser", "newpassword",
                "updated@example.com", "Updated User");

        UserResponseDto userResponseDto = new UserResponseDto(userId, "updateduser", "test");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.updateUser(eq(userId), any(User.class))).thenReturn(1);
        when(UserMapper.toUserResponseDto(any(User.class))).thenReturn(userResponseDto);

        // When
        UserResponseDto updatedUser = userService.updateUser(userId, updateUserDto);

        // Then
        assertNotNull(updatedUser);
        assertEquals(userId, updatedUser.id());
        assertEquals("updateduser", updatedUser.username());
    }

    @Test
    void testUpdateUser_NotFound() {
        // Given
        Long userId = 1L;
        UpdateUserDto updateUserDto = new UpdateUserDto("updateduser",
                "newpassword", "updated@example.com", "Updated User");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(CreateException.class, () -> userService.updateUser(userId, updateUserDto));
    }

    @Test
    void testDeleteUser_Success() {
        // Given
        Long userId = 1L;

        when(userRepository.existsById(userId)).thenReturn(true);

        // When
        userService.deleteUser(userId);

        // Then
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void testDeleteUser_NotFound() {
        // Given
        Long userId = 1L;

        when(userRepository.existsById(userId)).thenReturn(false);

        // When & Then
        assertThrows(DeleteException.class, () -> userService.deleteUser(userId));
        verify(userRepository, never()).deleteById(anyLong());
    }
}