package com.example.workflow.services;

import com.example.workflow.entities.Role;
import com.example.workflow.entities.User;
import com.example.workflow.exceptions.BadRequestException;
import com.example.workflow.repositories.RoleRepository;
import com.example.workflow.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        Role employeeRole = roleRepository.findByName("EMPLOYEE")
                .orElseThrow(() -> new BadRequestException("Role not found"));

        user.getRoles().add(employeeRole);

        // 🔥 Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
}
