package com.bug.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long userId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Long contactNo;
    private String email;
    private Date birthDate;
    private String address;
    private Long roleId;
}
