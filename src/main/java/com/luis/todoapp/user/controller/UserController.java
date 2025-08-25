package com.luis.todoapp.user.controller;

import com.luis.todoapp.user.dto.CreateUserRequest;
import com.luis.todoapp.user.model.User;
import com.luis.todoapp.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/auth/register")
    public String showRegisterForm(Model model) {
        // We send an empty object (Command object) to be filled in the form.
        model.addAttribute("user", new CreateUserRequest());
        return "register";
    }


    @PostMapping("/auth/register/submit")
    public String processRegisterForm(
            @Valid // Valid annotation is necessary to activate the validation features in Spring Boot
            @ModelAttribute("user") CreateUserRequest request, BindingResult bindingResult) {
        if (!request.getPassword().equals(request.getVerifyPassword())) {
            bindingResult.rejectValue("password", "error.password",  "Passwords do not match");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        User saved = userService.registerUser(request);

        return "redirect:/login";
    }
}
