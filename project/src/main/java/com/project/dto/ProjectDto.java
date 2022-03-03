package com.project.dto;

import lombok.*;

import java.sql.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {

    private Long clientId;
    private String projectName;
    private Date startedAt;
    private String projectInfo;
    private String accessToken;

}
