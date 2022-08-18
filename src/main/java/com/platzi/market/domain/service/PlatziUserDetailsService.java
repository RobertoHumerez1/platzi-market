package com.platzi.market.domain.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlatziUserDetailsService implements UserDetailsService {
    //@Override
    //public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //    return new User("root", "{noop}toor", new ArrayList<>());
    //}

    private static List<User> users = new ArrayList();

    public PlatziUserDetailsService(){
        users.add(new User("root", "{noop}toor", new ArrayList<>()));
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = users.stream().filter(u -> u.getUsername().equals(username)).findAny();

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found by name: " + username);
        }

        return new User(user.get().getUsername(), user.get().getPassword(), user.get().getAuthorities());
    }
}
