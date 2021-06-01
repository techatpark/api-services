package com.techatpark.starter.security.security;


import com.techatpark.starter.security.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final List<User> users;

    public CustomUserDetailsService(final PasswordEncoder passwordEncoder) {
        this.users = new ArrayList<>();

        User user = new User();
        user.setName("tom");
        user.setPassword(passwordEncoder.encode("password"));
        user.setImageUrl("/images/" + user.getName() + ".png");

        users.add(user);

        user = new User();
        user.setName("jerry");
        user.setPassword(passwordEncoder.encode("password"));
        user.setImageUrl("/images/" + user.getName() + ".png");
        users.add(user);

    }

    public Optional<User> findByName(String name) {
        return this.users.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst();
    }

    public Boolean existsByName(String name) {
        return this.users.stream()
                .anyMatch(user -> user.getName().equals(name));
    }

    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = findByName(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with email : " + email)
                );

        return UserPrincipal.create(user);
    }


}