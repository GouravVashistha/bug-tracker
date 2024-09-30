package com.bugtracker.user.entity;

import lombok.Data;

@Data
public class JwtResponse {
    private User user;
    private String accessToken;
}
