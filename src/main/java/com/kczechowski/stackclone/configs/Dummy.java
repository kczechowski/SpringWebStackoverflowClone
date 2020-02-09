package com.kczechowski.stackclone.configs;

import com.kczechowski.stackclone.entities.User;
import com.kczechowski.stackclone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class Dummy implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        User user = new User();
//        user.setEmail("user");
//        user.setNickname("user");
//        user.setPassword("123");
//        userRepository.save(user);
    }
}
