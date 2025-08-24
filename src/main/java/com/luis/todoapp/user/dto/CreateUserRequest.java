package com.luis.todoapp.user.dto;

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

    private String username, password, verifyPassword, email, fullname;
}
