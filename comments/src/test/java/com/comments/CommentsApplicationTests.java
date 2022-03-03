package com.comments;

import com.comments.entity.Comments;
import com.comments.repository.CommentsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CommentsApplicationTests {

    @Autowired
    CommentsRepository commentsRepository;

    @Test
    @Order(1)
    void commentLoads(){
        assertNotNull(commentsRepository.findAll());
    }

    @Test
    @Order(2)
    void saveCommentsTest(){
        Comments comments= new Comments();
            comments.setCommentId(1L);
            comments.setBugId(0L);
            comments.setComment("This is a test comment");
            comments.setUserMentioned(false);
            comments.setProblemHighlighted(false);
            comments.setPostId(0L);

        commentsRepository.save(comments);
        Assertions.assertThat(comments.getCommentId()).isPositive();
    }

    @Test
    @Order(3)
    void getListCommentTest(){
        List<Comments> commentList= commentsRepository.findAll();
        Assertions.assertThat(commentList).isNotEmpty();
    }

    @Test
    @Order(4)
    void editComment(){
        Comments comments= commentsRepository.findByCommentId(1L);
        comments.setComment("This is a test comment");
        Assertions.assertThat(commentsRepository.save(comments).getComment()).isEqualTo("This is a test comment");
    }

    @Test
    @Order(5)
    void getCommentsTest(){
        Assertions.assertThat(commentsRepository.findByCommentId(1L).getCommentId()).isEqualTo(1L);

    }
}
