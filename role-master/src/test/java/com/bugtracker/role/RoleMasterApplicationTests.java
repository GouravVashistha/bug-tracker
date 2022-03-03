package com.bugtracker.role;

import com.bugtracker.role.entity.Role;
import com.bugtracker.role.repository.RoleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RoleMasterApplicationTests {


	@Autowired
	RoleRepository roleRepository;

	@Test
	@Order(1)
	void roleLoads(){
		assertNotNull(roleRepository.findAll());
	}

	@Test
	@Order(2)
	void getListRoleTest(){
		List<Role> userList=roleRepository.findAll();
		Assertions.assertThat(userList).isNotEmpty();
	}

	@Test
	@Order(3)
	void getClientTest(){
		Role role=roleRepository.findByRoleId(3L);
		Assertions.assertThat(role.getRoleName()).isEqualTo("client");

	}

	@Test
	@Order(4)
	void getStaffTest(){
		Role role=roleRepository.findByRoleId(2L);
		Assertions.assertThat(role.getRoleName()).isEqualTo("staff");

	}

	@Test
	@Order(5)
	void getAdminTest(){
		Role role=roleRepository.findByRoleId(1L);
		Assertions.assertThat(role.getRoleName()).isEqualTo("admin");

	}

}
