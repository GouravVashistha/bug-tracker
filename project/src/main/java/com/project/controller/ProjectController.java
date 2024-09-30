package com.project.controller;

import com.project.dto.GetAllProjectsDto;
import com.project.dto.ProjectDto;
import com.project.dto.UpdateProjectDto;
import com.project.entity.Project;
import com.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @PostMapping("/addProject")
    public Project addProject(@RequestBody ProjectDto projectDto){

        return projectService.addProject(projectDto);

    }

    @PutMapping("/updateProject")
    public Project updateProject(@RequestBody UpdateProjectDto updateProjectDto) {
        return projectService.updateProject(updateProjectDto);
    }

    @GetMapping("/")
    public List<Project> getAllProjects() {

        return projectService.getAllProjects();
    }

    @GetMapping("/projectsByClientId")
    public List<Project> getAllProjects(@RequestBody GetAllProjectsDto getAllProjectsDto) {

        return projectService.getProjectByClientId(getAllProjectsDto.getClientId(),getAllProjectsDto.getAccessToken());
    }

    @GetMapping("/projectById/{id}")
    public Project getProjectById(@PathVariable("id") Long projectId) {
        return projectService.getProjectById(projectId);
    }

    @DeleteMapping("/deleteById/{id}")
    public Boolean deleteProjectById(@PathVariable("id") Long projectId) {
        return projectService.deleteProjectById(projectId);
    }
}
