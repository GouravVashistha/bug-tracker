package com.project.vo;

import lombok.Data;

import java.util.Date;

@Data
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
