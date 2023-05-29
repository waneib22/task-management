package com.waneib22.taskmanagement.service;

import com.waneib22.taskmanagement.exception.UserNotFounded;
import com.waneib22.taskmanagement.model.User;
import com.waneib22.taskmanagement.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFounded("User ID not found"));
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFounded("Username not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void updateUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFounded("User ID not founded"));

        userRepository.save(user);
    }

    public void updateUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFounded(("Username not founded")));

        userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFounded("User ID not founded"));

        userRepository.delete(user);
    }

    public void deleteUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFounded("Username not founded"));

        userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRoles().toArray(new String[0]))
                .build();
    }

}
