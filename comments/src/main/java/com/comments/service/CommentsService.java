package com.comments.service;

import com.comments.dto.CommentsDto;
import com.comments.entity.Comments;
import com.comments.exception.BugNotFoundException;
import com.comments.exception.UserNotFoundException;
import com.comments.repository.CommentsRepository;
import com.comments.vo.Bug;
import com.comments.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Service
public class CommentsService {

    @Autowired
    CommentsRepository commentsRepository;

    public Comments addComment(CommentsDto commentsDto) {

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(commentsDto.getAccessToken());
        try {
        ResponseEntity<User> user=  new  RestTemplate().exchange(RequestEntity.get(new URI("http://localhost:9002/users/getUser/"+ commentsDto.getPostId())).headers(headers).build(), User.class);
            if(user.getBody()== null) {
                throw new UserNotFoundException();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpHeaders headers1 = new HttpHeaders();
        headers1.setBearerAuth(commentsDto.getAccessToken());
        try {
            ResponseEntity<Bug> bug=  new  RestTemplate().exchange(RequestEntity.get(new URI("http://localhost:9004/bugs/bugById/"+  commentsDto.getBugId())).headers(headers1).build(), Bug.class);
            if(bug.getBody()== null) {
                throw new BugNotFoundException();
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        Comments comments= new Comments();
            comments.setComment(commentsDto.getComment());
            comments.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            comments.setPostId(commentsDto.getPostId());
            comments.setBugId(commentsDto.getBugId());

        if(comments.getComment().contains("#")) {
            comments.setProblemHighlighted(true);
        }

        if(comments.getComment().contains("@")) {
            comments.setUserMentioned(true);
        }

        return commentsRepository.saveAndFlush(comments);
    }

    public Boolean deleteComment(Long commentId) {

        Comments comments= commentsRepository.findByCommentId(commentId);

        if(comments!= null) {
            commentsRepository.deleteByCommentId(commentId);
            return true;

        }else {
            return false;
        }
    }

    public List<Comments> getCommentsByBugId(Long bugId) {

        Bug bug= new RestTemplate().getForObject("http://localhost:9004/bugs/bugById/"
                + bugId, Bug.class);

        if(bug== null) {
            throw new BugNotFoundException();
        }

        return commentsRepository.findByBugId(bugId);
    }

    public List<Comments> getAllComments() {

        return commentsRepository.findAll();
    }
}
