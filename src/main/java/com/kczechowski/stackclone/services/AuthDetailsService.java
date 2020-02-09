package com.kczechowski.stackclone.services;

import com.kczechowski.stackclone.entities.User;
import com.kczechowski.stackclone.repositories.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class AuthDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByNickname(username);
        if (user == null)
            throw new UsernameNotFoundException(username + " not found");

        List<String> roles = user.getRoles().stream().map(r -> r.getType()).collect(Collectors.toList());
        String[] rolesArray = roles.toArray(new String[roles.size()]);

        return new org.springframework.security.core.userdetails.User(user.getNickname(), user.getPassword(), AuthorityUtils.createAuthorityList(rolesArray));
    }

}
