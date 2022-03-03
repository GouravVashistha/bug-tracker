package com.bug.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    private Long projectId;
    private String projectName;
    private Date startedAt;
    private String projectInfo;
    private boolean isOnGoing;
    private Date endedOn;
    private Long clientId;
}
