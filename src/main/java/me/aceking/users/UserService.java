package me.aceking.users;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(Long id){
        return userRepository.findById(id).get();
    }

    public User updateUser(Long id, User user){
        if (!userRepository.existsById(id)) {
            throw new UserExceptions("User not found");
        } else if (user == null) {
            throw new UserExceptions("User cannot be null");
        }
        User existingUser = userRepository.findById(id).get();
        existingUser.setFullName(user.getFullName());
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
