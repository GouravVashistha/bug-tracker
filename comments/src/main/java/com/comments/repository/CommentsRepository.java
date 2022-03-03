package com.comments.repository;

import com.comments.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("commentsRepository")
@Transactional
public interface CommentsRepository extends JpaRepository<Comments,Long> {

    @Modifying
    @Query("delete from Comments c where c.commentId=:commentId")
    void deleteByCommentId(@Param("commentId") Long commentId);

    Comments findByCommentId(Long commentId);

    List<Comments> findByBugId(Long bugId);
}
