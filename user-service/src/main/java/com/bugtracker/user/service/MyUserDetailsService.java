package com.bugtracker.user.service;

import com.bugtracker.user.entity.User;
import com.bugtracker.user.exception.UserNotFoundException;
import com.bugtracker.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findByUserName(userName);

        if(optional.isEmpty()) {
            throw new UserNotFoundException();
        }

        User user= optional.get();

        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(),getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        List<Long> roleList=new ArrayList<>();
        roleList.add(1L);
        roleList.add(2L);
        roleList.add(3L);
        roleList.forEach(role ->
                authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRoleId()))
        );
        return authorities;


    }
}

