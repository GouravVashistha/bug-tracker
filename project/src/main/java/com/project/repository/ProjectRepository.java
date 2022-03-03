package com.project.repository;

import com.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("projectRepository")
public interface ProjectRepository  extends JpaRepository<Project,Long> {
    Project findByProjectId(Long projectId);

    List<Project> findByClientId(Long clientId);

    Boolean deleteByProjectId(Long projectId);
}
