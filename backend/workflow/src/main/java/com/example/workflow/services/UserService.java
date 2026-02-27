package com.example.workflow.services;

import com.example.workflow.entities.Role;
import com.example.workflow.entities.User;
import com.example.workflow.exceptions.BadRequestException;
import com.example.workflow.repositories.RoleRepository;
import com.example.workflow.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User registerUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new BadRequestException("Email already exists");
        }

        Role employeeRole = roleRepository.findByName("EMPLOYEE")
                .orElseThrow(() -> new BadRequestException("Role not found"));

        user.getRoles().add(employeeRole);

        return userRepository.save(user);
    }
}
