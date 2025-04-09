package me.aceking.users;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(@NotNull String username);

    boolean existsByEmail(@NotNull String email);

    User findByUsername(String username);

    void deleteByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u = :user WHERE u.id = :id")
    int updateUser(@Param("id") Long id, @Param("user") User user);

    User findByEmail(String email);
}