package com.project.service;

import com.project.constant.Constant;
import com.project.vo.User;
import com.project.dto.ProjectDto;
import com.project.dto.UpdateProjectDto;
import com.project.entity.Project;
import com.project.exception.ProjectNotFoundException;
import com.project.exception.UserNotFoundException;
import com.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class ProjectService{

    @Autowired
    MailService mailService;

    @Autowired
    ProjectRepository projectRepository;

    public User authenticateUser(Long clientId, String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        try {
            ResponseEntity<User> user= new RestTemplate().exchange(RequestEntity.get(new URI("http://localhost:9002/users/getClientById/" + clientId)).headers(headers).build(), User.class);

            if(user.getBody()== null) {
                throw new UserNotFoundException();
            }else {
                return user.getBody();
            }
        } catch (URISyntaxException uriSyntaxException) {
            throw new UserNotFoundException();
        }
    }

    public Project addProject(ProjectDto projectDto) {

        User userReturned= authenticateUser(projectDto.getClientId(), projectDto.getAccessToken());

        Project project=new Project();

        project.setProjectInfo(projectDto.getProjectInfo());
        project.setProjectName(projectDto.getProjectName());
        project.setStartedAt(projectDto.getStartedAt());
        project.setClientId(projectDto.getClientId());
        project.setOnGoing(true);

        Project projectReturned= projectRepository.saveAndFlush(project);

        mailService.sendMail(userReturned.getEmail(),
                Constant.EMAIL_START_WARNING + Constant.EMAIL_PROJECT_ID +
                        projectReturned.getProjectId() + Constant.EMAIL_PROJECT_NAME +
                        projectReturned.getProjectName() + Constant.EMAIL_PROJECT_INFO +
                        projectReturned.getProjectInfo() + Constant.EMAIL_CLIENT_ID +
                        projectReturned.getClientId() + Constant.EMAIL_START_DATE +
                        projectReturned.getStartedAt() + Constant.EMAIL_PROJECT_STATUS +
                        projectReturned.isOnGoing()+ Constant.EMAIL_END_WARNING,
                Constant.EMAIL_ADD_PROJECT_SUBJECT);

       return projectReturned;

    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long projectId) {
        return projectRepository.findByProjectId(projectId);
    }

    public Project updateProject(UpdateProjectDto updateProjectDto) {

        Project projectReturned= getProjectById(updateProjectDto.getProjectId());

        User userReturned= authenticateUser(projectReturned.getClientId(), updateProjectDto.getAccessToken());

        if(projectReturned.getProjectId()== null) {
            throw new ProjectNotFoundException();
        }else {
            projectReturned.setProjectInfo(updateProjectDto.getProjectInfo());
            projectReturned.setOnGoing(updateProjectDto.isOnGoing());
            if(!updateProjectDto.isOnGoing()) {
                projectReturned.setEndedOn(updateProjectDto.getEndedOn());

            }

            projectReturned.setProjectName(updateProjectDto.getProjectName());

            Project projectUpdated= projectRepository.save(projectReturned);

            mailService.sendMail(userReturned.getEmail(),
                    Constant.EMAIL_START_WARNING + Constant.EMAIL_PROJECT_ID +
                            projectUpdated.getProjectId() + Constant.EMAIL_PROJECT_NAME +
                            projectUpdated.getProjectName() + Constant.EMAIL_PROJECT_INFO +
                            projectUpdated.getProjectInfo() + Constant.EMAIL_CLIENT_ID +
                            projectUpdated.getClientId() + Constant.EMAIL_START_DATE +
                            projectUpdated.getStartedAt() + Constant.EMAIL_PROJECT_STATUS +
                            projectUpdated.isOnGoing()+ Constant.EMAIL_END_WARNING,
                    Constant.ACCOUNT_UPDATING_SUBJECT);

            return projectUpdated;
        }
    }

    public List<Project> getProjectByClientId(Long clientId,String accessToken) {

        authenticateUser(clientId, accessToken);

        return projectRepository.findByClientId(clientId);
    }

    public Boolean deleteProjectById(Long projectId) {
        return projectRepository.deleteByProjectId(projectId);
    }
}
