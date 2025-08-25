package com.luis.todoapp.user.service;

import com.luis.todoapp.user.dto.CreateUserRequest;
import com.luis.todoapp.user.model.User;
import com.luis.todoapp.user.model.UserRole;
import com.luis.todoapp.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    // Takes the DTO which will be the command object of the register form
    public User registerUser(CreateUserRequest request) {

        if (!request.getPassword().equals(request.getVerifyPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        return userRepository.save(
                User.builder()
                        .username(request.getUsername())
                        .password(encoder.encode(request.getPassword())) // Gets the password and encodes it.
                        .email(request.getEmail())
                        .fullname(request.getFullname())
                        .role(UserRole.USER) // Sets the default role to 'USER'
                        .build()
        );

    }

    public User changeRole(User user, UserRole userRole) {
        user.setRole(userRole);
        return userRepository.save(user); // Updates the current user with the new role.
    }

    public User changeRole(Long userId, UserRole userRole) {
        return userRepository.findById(userId) // returns an Optional<User>
                .map(u -> { // .map takes the value inside Optional (u references the User)
                    u.setRole(userRole);
                    return userRepository.save(u);
                }).orElse(null);
    }


}
