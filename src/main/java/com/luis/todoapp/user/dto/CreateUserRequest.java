package com.luis.todoapp.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Command object that will be sent to the register form.
@Data // Generates Getters, Setters, toString, equals, hashCode and canEqual methods.
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {

    @NotBlank(message = "Username cannot be empty")
    private String username;

    // Password with at least 1 Uppercase, 1 Lowercase, 1 number and 8 characters.
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$",
            message = "Password must have at least 8 characters, with uppercase, lowercase and a number"
    )
    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotBlank(message = "Please repeat your password")
    private String verifyPassword;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Full name cannot be empty")
    private String fullname;
}
