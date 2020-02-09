package com.kczechowski.stackclone.controllers;

import com.kczechowski.stackclone.entities.User;
import com.kczechowski.stackclone.entities.UserRole;
import com.kczechowski.stackclone.repositories.UserRepository;
import com.kczechowski.stackclone.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/users")
    List<User> all() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        System.out.println(securityContext);
        return userRepository.findAll();
    }


    @GetMapping("/users/{id}")
    User one(@PathVariable int id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());
    }


    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User user, @PathVariable int id) {

        return userRepository.findById(id)
                .map(u -> {
                    u.setNickname(user.getNickname());
                    u.setEmail(user.getEmail());
                    return userRepository.save(u);
                }).orElseThrow(() -> new RuntimeException());
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }
}
