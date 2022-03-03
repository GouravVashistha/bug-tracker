package com.bugtracker.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name="user_details",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"user_name", "contact_num", "email_id"})
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userId;

    @Column(name = "user_name")
    private String userName;
    private String password;
    private String firstName;
    private String lastName;

    @Column(name = "contact_num")
    private Long contactNo;

    @Column(name = "email_id")
    private String email;
    private Date birthDate;
    private String address;
    private Long roleId;
}
