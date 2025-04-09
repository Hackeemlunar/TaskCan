package me.aceking.users;

import lombok.RequiredArgsConstructor;
import me.aceking.exceptions.DeleteException;
import me.aceking.exceptions.ReadException;
import me.aceking.users.dto.CreateUserDto;
import me.aceking.users.dto.UpdateUserDto;
import me.aceking.users.dto.UserMapper;
import me.aceking.exceptions.CreateException;
import me.aceking.users.dto.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements IUserService {
    private final Logger logger = Logger.getLogger(UserService.class.getName());
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto createUser(CreateUserDto user) {
        if (userRepository.existsByUsername(user.username())) {
            throw new CreateException("Username '" + user.username() + "' is already taken.");
        } else if (userRepository.existsByEmail(user.email())) {
            throw new CreateException("Email '" + user.email() + "' is already registered.");
        }

        User newUser = new User(user.username(),user.password(), user.email(), user.fullName());
        return UserMapper.toUserResponseDto(userRepository.save(newUser));
    }

    @Override
    public UserResponseDto createUserByAdmin(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new CreateException("Username '" + user.getUsername() + "' is already taken.");
        } else if (userRepository.existsByEmail(user.getEmail())) {
            throw new CreateException("Email '" + user.getEmail() + "' is already registered.");
        }
        return UserMapper.toUserResponseDto(userRepository.save(user));
    }

    @Override
    @Transactional(readOnly = true) // Use readOnly for better performance
    public UserResponseDto getUserByUsername(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new ReadException("User with username '" + username + "' not found.");
        }
        return UserMapper.toUserResponseDto(userRepository.findByUsername(username));
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ReadException("User with id '" + id + "' not found.");
        }
        return UserMapper.toUserResponseDto(userRepository.findById(id).get());
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserByEmail(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new ReadException("User with email '" + email + "' not found.");
        }
        return UserMapper.toUserResponseDto(userRepository.findByEmail(email));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponseDto> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(user -> UserMapper.toUserResponseDto(user));
    }

    @Override
    public UserResponseDto updateUser(Long id, UpdateUserDto userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new CreateException("User with id '" + id + "' not found."));
        if (userDto.username() != null) {
            existingUser.setUsername(userDto.username());
        }
        if (userDto.email() != null) {
            existingUser.setEmail(userDto.email());
        }
        if (userDto.fullName() != null) {
            existingUser.setFullName(userDto.fullName());
        }
        if (userDto.password() != null) {
            // TODO: Hash the password before updating
            existingUser.setPassword(userDto.password());
        }
        userRepository.updateUser(id, existingUser);
        return UserMapper.toUserResponseDto(userRepository.findById(id).get());
    }

    @Override
    public void deleteUser(Long id){
        if (!userRepository.existsById(id)) {
            throw new DeleteException("User with id '" + id + "' not found.");
        }
        userRepository.deleteById(id);
    }

    @Override
    public void deleteByUsername(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new DeleteException("User with username '" + username + "' not found.");
        }
        userRepository.deleteByUsername(username);
    }
}
