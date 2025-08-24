package com.luis.todoapp.shared.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// This class allows us to configure simple route-to-view mappings.
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    // When a user visits /login go to the login view
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    // This saves us from creating a trivial controller like this:
//    @Controller
//    public class LoginController {
//        @GetMapping("/login")
//        public String login() {
//            return "login";
//        }
//    }
}
