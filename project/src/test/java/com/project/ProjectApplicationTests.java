package com.project;

import com.project.entity.Project;
import com.project.repository.ProjectRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProjectApplicationTests {

	@Autowired
	ProjectRepository projectRepository;

	@Test
	@Order(1)
	void projectLoads(){
		assertNotNull(projectRepository.findAll());
	}

	@Test
	@Order(2)
	void saveProjectTest(){

		Project project= new Project();
			project.setProjectId(1L);
			project.setProjectName("TestProject");
			project.setProjectInfo("This is a test project");
			project.setOnGoing(true);
			project.setStartedAt(Date.valueOf(LocalDate.now()));
			project.setClientId(0L);

		projectRepository.save(project);
		Assertions.assertThat(project.getProjectId()).isPositive();
	}

	@Test
	@Order(3)
	void getListProjectTest(){
		List<Project> projectList= projectRepository.findAll();
		Assertions.assertThat(projectList).isNotEmpty();
	}

	@Test
	@Order(4)
	void editProject(){
		Project project= projectRepository.findByProjectId(1L);
		project.setProjectInfo("This is a test project");
		Assertions.assertThat(projectRepository.save(project).getProjectInfo()).isEqualTo("This is a test project");
	}

	@Test
	@Order(5)
	void getProjectTest(){
		Project project= projectRepository.findByProjectId(1L);
		Assertions.assertThat(project.getProjectId()).isEqualTo(1L);

	}
}
