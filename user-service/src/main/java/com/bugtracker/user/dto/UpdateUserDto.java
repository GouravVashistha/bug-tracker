package com.bugtracker.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDto {

        private Long userId;
        private String firstName;
        private String lastName;
        private String password;
        private Long contactNo;
        private Date birthDate;
        private String address;
}
