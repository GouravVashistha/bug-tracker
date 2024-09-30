package com.comments.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="commentid")
    private Long commentId;
    @Column(name="comment")
    private String comment;
    @Column(name="is_user_mentioned")
    private boolean isUserMentioned;
    @Column(name="is_problem_highlighted")
    private boolean isProblemHighlighted;
    private Long postId;
    private Long bugId;
    private Date date;


}
