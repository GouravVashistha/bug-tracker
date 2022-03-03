package com.comments.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentsDto {

    private Long postId;
    private Long bugId;
    private String comment;
    private String accessToken;
}
