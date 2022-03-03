package com.bugtracker.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String userName;
    @Min(8)
    private String password;
    private String firstName;
    private String lastName;
    private Long contactNo;
    @Email
    private String email;
    private Date birthDate;
    private String address;
    private Long roleId;
}
