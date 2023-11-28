package com.tatsiana.grocery.security;

import com.tatsiana.grocery.model.User;
import com.tatsiana.grocery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username " + username + " does not exists");
        }

        return new CustomUserDetails(username, user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")), user);
    }
}
