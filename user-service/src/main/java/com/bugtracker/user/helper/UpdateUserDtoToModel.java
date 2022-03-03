package com.bugtracker.user.helper;

import com.bugtracker.user.dto.UpdateUserDto;
import com.bugtracker.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserDtoToModel {

    public User convertUpdateUserDtoToModel(UpdateUserDto updateUserDto) {

        User user= new User();

        user.setUserId(updateUserDto.getUserId());
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setPassword(updateUserDto.getPassword());
        user.setContactNo(updateUserDto.getContactNo());
        user.setAddress(updateUserDto.getAddress());
        user.setBirthDate(updateUserDto.getBirthDate());

        return user;
    }
}

