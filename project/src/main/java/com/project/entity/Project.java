package com.project.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="project_master")
public class Project {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="projectid")
    private Long projectId;
    @Column(name="projectname")
    private String projectName;
    @Column(name="startedat")
    private Date startedAt;
    @Column(name="projectinfo")
    private String projectInfo;
    @Column(name="isongoing")
    private boolean isOnGoing;
    @Column(name="endedon")
    private Date endedOn;
    private Long clientId;

}
