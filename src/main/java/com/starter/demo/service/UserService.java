package com.starter.demo.service;

import com.starter.demo.domain.user.User;
import com.starter.demo.exceptions.UserNotFoundException;
import com.starter.demo.repositories.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException( "User not found with id: " + id));
    }
}
