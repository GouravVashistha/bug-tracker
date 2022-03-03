package com.bug.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BugWithoutImage {

    private Long bugId;
    private String bugNote;
    private String severity;
    private String status;
    private String imageName;
    private Long staffId;
    private boolean isStaffAssigned;
    private boolean isBugSolved;
    private Long projectId;
}
