package com.luis.todoapp.shared.security;

import com.luis.todoapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Spring Security uses it to look up a user when someone tries to log in
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Returns a User object, User already implements UserDetails so it already contains the needed info (username, password, email...).
        return userRepository.findByUsernameOrEmail(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("Bad credentials"));
    }
}
