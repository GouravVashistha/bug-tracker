package com.bugtracker.user.helper;

import com.bugtracker.user.dto.UserDto;
import com.bugtracker.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoToModel {

    public User convertUserDtoToModel(UserDto userDto) {

        User user= new User();

        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setContactNo(userDto.getContactNo());
        user.setEmail(userDto.getEmail());
        user.setAddress(userDto.getAddress());
        user.setBirthDate(userDto.getBirthDate());
        user.setRoleId(userDto.getRoleId());

        return user;
    }
}
