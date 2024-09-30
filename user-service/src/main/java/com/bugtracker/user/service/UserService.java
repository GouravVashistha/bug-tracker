package com.bugtracker.user.service;

import com.bugtracker.user.vo.ResponseTemplateVO;
import com.bugtracker.user.vo.Role;
import com.bugtracker.user.constant.Constant;
import com.bugtracker.user.dto.UpdateUserDto;
import com.bugtracker.user.dto.UserDto;
import com.bugtracker.user.entity.User;
import com.bugtracker.user.exception.*;
import com.bugtracker.user.helper.UpdateUserDtoToModel;
import com.bugtracker.user.helper.UserDtoToModel;
import com.bugtracker.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDtoToModel userDtoToModel;

    @Autowired
    private UpdateUserDtoToModel updateUserDtoToModel;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MailService mailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(UserDto userDto) {

        User user = userDtoToModel.convertUserDtoToModel(userDto);
        user.setPassword(encodePassword(user.getPassword()));

        Optional<User> userNameValidator = userRepository.findByUserName(user.getUserName());
        Optional<User> emailValidator = userRepository.findByEmail(user.getEmail());
        Optional<User> contactValidator = userRepository.findByContactNo(user.getContactNo());

        if (userNameValidator.isPresent()) {
            throw new UserNameAlreadyExistsException();
        } else if (emailValidator.isPresent()) {
            throw new EmailAlreadyTakenException();
        } else if (contactValidator.isPresent()) {
            throw new ContactAlreadyExistsException();
        } else {
            if (user.getRoleId() == 1) {
                throw new ForbiddenAuthorityException();
            } else {
                User userReturned = userRepository.save(user);
                mailService.sendMail(userReturned.getEmail(),
                    Constant.EMAIL_START_WARNING + Constant.EMAIL_USERID +
                            userReturned.getUserId() + Constant.EMAIL_USERNAME +
                            userReturned.getUserName() + Constant.EMAIL_PASSWORD +
                            userDto.getPassword() + Constant.EMAIL_CONTACT +
                            userReturned.getContactNo() + Constant.EMAIL_END_WARNING,
                    Constant.ACCOUNT_REGISTRATION_SUBJECT);
                return userReturned;
            }
        }
    }

    public String encodePassword(String password) {

        String regex= "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pattern= Pattern.compile(regex);
        Matcher matcher= pattern.matcher(password);

        if (matcher.matches()) return passwordEncoder.encode(password);
        else throw new PasswordSyntaxIncorrectException();

    }


    public ResponseTemplateVO getUser(Long userId) {

        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        Role role =
                new RestTemplate().getForObject("http://localhost:9001/roles/role/" + user.getRoleId()
                        , Role.class);

        responseTemplateVO.setUser(user);
        responseTemplateVO.setRole(role);

        return responseTemplateVO;
    }

    public String userLogin(String userName, String password, Long roleId) {

        Optional<User> userReturned = userRepository.findByUserName(userName);
        Long roleIdReturned = userReturned.map(User::getRoleId).orElse(0L);
        String passwordReturned = userReturned.map(User::getPassword).orElse("");
        if (userReturned.isEmpty()) {
            throw new UserNotFoundException();
        } else if (!passwordReturned.equals(password)) {
            throw new IncorrectCredentials();
        } else if (roleIdReturned.equals(roleId)) {
            throw new DomainIncorrectException();
        } else {
            return "User login successfully";
        }
    }

    public User updateUser(UpdateUserDto updateUserDto) {

        User user = updateUserDtoToModel.convertUpdateUserDtoToModel(updateUserDto);

        User userReturned = userRepository.findByUserId(user.getUserId());
            userReturned.setFirstName(user.getFirstName());
            userReturned.setLastName(user.getLastName());
            userReturned.setContactNo(user.getContactNo());
            userReturned.setBirthDate(user.getBirthDate());
            userReturned.setAddress(user.getAddress());

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String existingPassword=userReturned.getPassword();

        Boolean isMatch = bCryptPasswordEncoder.matches(updateUserDto.getPassword(), existingPassword);


        if (userReturned.getUserId() == 0L) {
            throw new UserNotFoundException();
        } else if (Boolean.FALSE.equals(isMatch)) {
            throw new IncorrectCredentials();
        } else {
            User userUpdated = userRepository.save(userReturned);
            mailService.sendMail(userUpdated.getEmail(),
                    Constant.EMAIL_START_WARNING + Constant.EMAIL_UPDATED_FIRSTNAME +
                            userUpdated.getFirstName() + Constant.EMAIL_UPDATED_LASTNAME +
                            userUpdated.getLastName() + Constant.EMAIL_CONTACT +
                            userUpdated.getContactNo() + Constant.EMAIL_UPDATED_BIRTHDATE +
                            userUpdated.getBirthDate() + Constant.EMAIL_UPDATED_ADDRESS +
                            userUpdated.getAddress() + Constant.EMAIL_END_WARNING,
                    Constant.ACCOUNT_UPDATING_SUBJECT);
            return userUpdated;
        }
    }

    public User changePassword(Long userId, String currentPassword, String newPassword) {

        User userReturned = userRepository.findByUserId(userId);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String existingPassword=userReturned.getPassword();


        if (userReturned.getUserId() == 0L) {
            throw new UserNotFoundException();
        } else if (!bCryptPasswordEncoder.matches(currentPassword, existingPassword)) {
            throw new IncorrectCredentials();
        } else {
            userReturned.setPassword(bCryptPasswordEncoder.encode(newPassword));

            User userWithUpdatedPassword = userRepository.save(userReturned);
            mailService.sendMail(userWithUpdatedPassword.getEmail(),
                    Constant.EMAIL_START_WARNING + Constant.EMAIL_UPDATED_PASSWORD +
                            userWithUpdatedPassword.getPassword() + Constant.EMAIL_END_WARNING
                    , Constant.UPDATED_PASSWORD_SUBJECT);
            return userWithUpdatedPassword;
        }
    }

    public User changeEmail(Long userId, String password, String email) {

        User userReturned = userRepository.findByUserId(userId);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        String existingPassword=userReturned.getPassword();

        Boolean isMatch = bCryptPasswordEncoder.matches(password, existingPassword);

        if (userReturned.getUserId() == null) {
            throw new UserNotFoundException();
        } else if (Boolean.FALSE.equals(isMatch)) {
            throw new IncorrectCredentials();
        } else {
            userReturned.setEmail(email);
            User userWithUpdatedEmail = userRepository.save(userReturned);
            mailService.sendMail(userWithUpdatedEmail.getEmail(),
                    Constant.EMAIL_START_WARNING + Constant.EMAIL_UPDATED + Constant.EMAIL_END_WARNING
                    , Constant.EMAIL_UPDATING_SUBJECT);
            return userWithUpdatedEmail;
        }
    }

    public User viewStaffById(Long userId) {

        User userReturned = userRepository.findByUserId(userId);

        if (userReturned.getUserId() == 0L || userReturned.getRoleId() != 2L) {
            throw new UserNotFoundException();
        } else {
            return userReturned;
        }
    }

    public User viewClientById(Long userId) {

        User userReturned = userRepository.findByUserId(userId);

        if (userReturned.getUserId() == 0L || userReturned.getRoleId() != 3L) {
            throw new UserNotFoundException();
        } else {
            return userReturned;
        }
    }

    public List<User> viewAllClient() {

        return userRepository.findByRoleId(3L);
    }

    public List<User> viewAllStaff() {

        return userRepository.findByRoleId(2L);
    }

    public Boolean deleteClientById(Long clientId) {
        User userReturned = userRepository.findByUserId(clientId);

        if (userReturned.getUserId() == 0) {
            throw new UserNotFoundException();
        } else if (userReturned.getRoleId() != 3) {
            throw new DomainIncorrectException();
        } else {
            userRepository.delete(userReturned);
            return true;
        }
    }

    public Boolean deleteStaffById(Long staffId) {
        User userReturned = userRepository.findByUserId(staffId);

        if (userReturned.getUserId() == 0) {
            throw new UserNotFoundException();
        } else if (userReturned.getRoleId() != 2) {
            throw new DomainIncorrectException();
        } else {
            userRepository.delete(userReturned);
            return true;
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
