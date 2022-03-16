package com.bugtracker.user.controller;

import com.bugtracker.user.vo.ResponseTemplateVO;
import com.bugtracker.user.dto.UpdateUserDto;
import com.bugtracker.user.dto.UserDto;
import com.bugtracker.user.entity.AuthRequest;
import com.bugtracker.user.entity.User;
import com.bugtracker.user.exception.IncorrectCredentials;
import com.bugtracker.user.service.UserService;
import com.bugtracker.user.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @GetMapping(value= "/auth")
    public String userLogin(@RequestParam String userName, @RequestParam String password, @RequestParam Long roleId) {

        return userService.userLogin(userName, password, roleId);
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        }
        catch (Exception ex) {
            throw new IncorrectCredentials();
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }

    @PostMapping(value= "/registerUserAccount", consumes = "application/json", produces = "application/json")
    public User registerUser(@RequestBody UserDto userDto){

        return userService.registerUser(userDto);
    }

    @GetMapping(value= "/getUser/{id}")
    public ResponseTemplateVO getUserById(@PathVariable("id") Long userId) {

        return userService.getUser(userId);
    }

    @PutMapping(value= "/updateUserAccount")
    public User updateUser(@RequestBody UpdateUserDto updateUserDto) {

        return userService.updateUser(updateUserDto);
    }

    @PutMapping(value= "/changePassword")
    public User changePassword(@RequestParam Long userId, @RequestParam String currentPassword, @RequestParam String newPassword) {

        return userService.changePassword(userId, currentPassword, newPassword);
    }

    @PutMapping(value= "/changeEmail")
    public User changeEmail(@RequestParam Long userId, @RequestParam String password, @RequestParam String email) {

        return userService.changeEmail(userId, password, email);
    }

    @GetMapping(value= "/getStaffById/{id}")
    public User getStaffById(@PathVariable("id") Long userId) {

        return userService.viewStaffById(userId);
    }

    @GetMapping(value= "/getClientById/{id}")
    public User getClientById(@PathVariable("id") Long userId) {

        return userService.viewClientById(userId);
    }

    @GetMapping(value= "/getAllClient")
    @PreAuthorize("hasRole('ROLE_1')")
    public List<User> getALlClient() {

        return userService.viewAllClient();
    }

    @GetMapping(value= "/getAllStaff")
    @PreAuthorize("hasRole('ROLE_1')")
    public List<User> getAllStaff() {

        return userService.viewAllStaff();
    }

    @DeleteMapping("/deleteClient/{id}")
    @PreAuthorize("hasRole('ROLE_1') or hasRole('ROLE_3')")

    public Boolean deleteClientById(@PathVariable("id") Long clientId) {
        return userService.deleteClientById(clientId);
    }

    @DeleteMapping("/deleteStaff/{id}")
    @PreAuthorize("hasRole('ROLE_1') or hasRole('ROLE_2')")
    public Boolean deleteStaffById(@PathVariable("id") Long staffId) {
        return userService.deleteStaffById(staffId);
    }
}
