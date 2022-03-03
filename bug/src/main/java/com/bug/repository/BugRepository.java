package com.bug.repository;

import com.bug.entity.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository("bugRepository")
public interface BugRepository extends JpaRepository<Bug, Integer> {
    List<Bug> findByProjectId(int projectId);

    Bug findByBugId(Long bugId);
}
