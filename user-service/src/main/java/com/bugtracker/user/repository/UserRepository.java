package com.bugtracker.user.repository;

import com.bugtracker.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String email);

    Optional<User> findByContactNo(Long contactNo);

    User findByUserId(Long userId);

    List<User> findByRoleId(Long roleId);

}
