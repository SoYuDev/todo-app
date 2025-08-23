package com.luis.todoapp.user.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name="user_entity") //user is often a reserved word so we change the table name to avoid errors.
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    private String username, password, email, fullname;

    private UserRole role;

    // This method is part of the Spring Security UserDetails interface.
    // Tells Spring Security what permissions or roles the user has.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }


}
