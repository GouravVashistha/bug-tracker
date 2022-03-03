package com.bug.vo;

import com.bug.entity.Bug;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplateVO {

    private Bug bug;
    private Project project;
    private User user;

}
