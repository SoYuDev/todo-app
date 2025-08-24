package com.luis.todoapp.shared.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

@EnableWebSecurity // Turns on Spring Security's web support.
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Defines who can access which URLs
        http.authorizeHttpRequests(requests ->
                requests.requestMatchers("/admin/**").hasRole("ADMIN") // Any URL under /admin/ requires ADMIN role.
                        .requestMatchers("/login", "/logout", "/auth/register", "/auth/register/submit", "/h2-console/**", "/img/**", "/css/**").permitAll()
                        .anyRequest().authenticated()); // Any other request requires a logged-in user.

        /*
        * formLogin enables form-based authentication (username/password)
        * loginPage tells spring to use my page instead of the default generated one.
        * defaultSuccessUrl redirects to /
        * If someone is not authenticated redirect them to /login.
        */
        http.formLogin(login -> {
            login.loginPage("/login")
                    .defaultSuccessUrl("/")
                    .permitAll();
        });

        // When an unauthenticated user requests a protected URL, Spring saves it; after login, it redirects back to the URL.
        http.requestCache(cache -> {
            HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
            requestCache.setMatchingRequestParameterName(null);
            cache.requestCache(requestCache);
        });

        // Invalidates the session, clears authentication, redirects you to a default page (often /login?logout)
        http.logout(Customizer.withDefaults());

        //CSRF protection stays enabled except on "h-2console/**"
        http.csrf((csrf) -> {
            csrf.ignoringRequestMatchers("/h2-console/**");
        });

        // Allow H2 console to render in frames.
        http.headers((headers) ->
                headers.frameOptions((opts) -> opts.disable()));
        return http.build();
    }
}
