package com.luis.todoapp.shared.init;

import com.luis.todoapp.user.dto.CreateUserRequest;
import com.luis.todoapp.user.model.User;
import com.luis.todoapp.user.model.UserRole;
import com.luis.todoapp.user.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// Class to preload some data into the application.
@Component
@RequiredArgsConstructor
public class DataSeed {

    private final UserService userService;

    @PostConstruct // After the bean is created run this method.
    public void init() {
        List<User> users = insertUsers();
    }

    // Creation of "dummy" users.
     private List<User> insertUsers() {

        List<User> result = new ArrayList<>();

        CreateUserRequest req = CreateUserRequest.builder()
                .username("user")
                .email("user@user.com")
                .password("1234")
                .verifyPassword("1234")
                .fullname("The user")
                .build();
        User user = userService.registerUser(req);
        result.add(user);

        CreateUserRequest req2 = CreateUserRequest.builder()
                .username("admin")
                .email("admin@ow.net")
                .password("1234")
                .verifyPassword("1234")
                .fullname("Administrador")
                .build();
        User user2 = userService.registerUser(req2);

        userService.changeRole(user2, UserRole.ADMIN);

        return result;
    }
}
