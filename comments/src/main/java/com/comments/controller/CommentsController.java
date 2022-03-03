package com.comments.controller;

import com.comments.dto.CommentsDto;
import com.comments.entity.Comments;
import com.comments.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @PostMapping("/addComment")
    public Comments addComment(@RequestBody CommentsDto commentsDto) {

        return commentsService.addComment(commentsDto);
    }

    @DeleteMapping("/deleteComment/{id}")
    public Boolean deleteComment(@PathVariable("id") Long commentId) {

        return commentsService.deleteComment(commentId);
    }

    @GetMapping("/getByBugId/{id}")
    public List<Comments> getCommentsByBugId(@PathVariable("id") Long bugId) {
        return commentsService.getCommentsByBugId(bugId);
    }
}
