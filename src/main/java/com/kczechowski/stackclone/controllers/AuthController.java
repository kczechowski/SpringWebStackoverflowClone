package com.kczechowski.stackclone.controllers;

import com.kczechowski.stackclone.entities.User;
import com.kczechowski.stackclone.entities.UserRole;
import com.kczechowski.stackclone.entities.requests.RegisterUserRequest;
import com.kczechowski.stackclone.entities.responses.DetailedUserResponse;
import com.kczechowski.stackclone.repositories.UserRepository;
import com.kczechowski.stackclone.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @PostMapping("/login")
    DetailedUserResponse login() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        User user = userRepository.findByNickname(securityContext.getAuthentication().getName());

        List<String> roles = user.getRoles().stream().map(r -> r.getType()).collect(Collectors.toList());

        DetailedUserResponse response = new DetailedUserResponse();
        response.setEmail(user.getEmail());
        response.setId(user.getId());
        response.setNickname(user.getNickname());
        response.setRoles(roles);
        return response;
    }

    @PostMapping("/register")
    User newUser(@RequestBody RegisterUserRequest user) {

        User newUser = new User();
        newUser.setNickname(user.getNickname());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser = userRepository.save(newUser);

        UserRole role = new UserRole();
        role.setType("ROLE_USER");
        role.setUserId(newUser.getId());
        userRoleRepository.save(role);

        return newUser;
    }

}
