package com.luis.todoapp.user.repository;

import com.luis.todoapp.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // If the query finds a user, the Optional contains the User object if it doesn't,
    // the method returns Optional.empty (an empty object) instead of null avoiding NullPointerException.
    Optional<User> findByUsernameOrEmail(String username, String email);
}
