package me.aceking.users;

import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, Long> {
//    User updateById(Long id, User user);
}