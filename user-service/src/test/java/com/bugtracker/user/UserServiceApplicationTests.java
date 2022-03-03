package com.bugtracker.user;

import com.bugtracker.user.entity.User;
import com.bugtracker.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserServiceApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @Order(1)
    void userLoads(){
        assertNotNull(userRepository.findAll());
    }

    @Test
    @Order(2)
    void saveUserTest(){
        User user= new User();
        user.setUserId(1L);
        user.setUserName("shabbir20");
        user.setPassword(passwordEncoder.encode("Shabbir@123"));
        user.setFirstName("Shabbir");
        user.setLastName("Kaderi");
        user.setAddress("Dhule, Maharashtra");
        user.setBirthDate(new Date(1999-11-28));
        user.setEmail("shabbirkaderi9@gmail.com");
        user.setContactNo(9082649421L);
        user.setRoleId(1L);

        userRepository.save(user);
        Assertions.assertThat(user.getUserId()).isPositive();
    }

    @Test
    @Order(3)
    void getListUserTest(){
        List<User> userList=userRepository.findAll();
        Assertions.assertThat(userList).isNotEmpty();
    }

    @Test
    @Order(4)
    void editUser(){
        User user= userRepository.findByUserId(1L);
        user.setAddress("Dhule, Maharashtra");
        Assertions.assertThat(userRepository.save(user).getContactNo()).isEqualTo(9082649421L);
    }

    @Test
    @Order(5)
    void getUserTest(){
        User user=userRepository.findByUserId(1L);
        Assertions.assertThat(user.getRoleId()).isEqualTo(1L);

    }
}
