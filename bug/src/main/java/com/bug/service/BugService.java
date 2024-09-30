package com.bug.service;

import com.bug.exception.*;
import com.bug.vo.Project;
import com.bug.vo.User;
import com.bug.constant.Constant;
import com.bug.dto.BugDto;
import com.bug.dto.BugWithoutImage;
import com.bug.entity.Bug;
import com.bug.repository.BugRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BugService {

    @Autowired
    BugRepository bugRepository;

    public Bug addBugs(String bugDto, MultipartFile file) {

        try {
            BugDto toBugDto = new ObjectMapper().readValue(bugDto, BugDto.class);
            Project project = new RestTemplate()
                    .getForObject("http://localhost:9003/projects/projectById/" + toBugDto.getProjectId(),
                            Project.class);

            if (project == null) {
                throw new ProjectNotFoundException();
            }

            Bug bug = new Bug();

            bug.setBugNote(toBugDto.getBugNote());
            bug.setSeverity(toBugDto.getSeverity());
            bug.setStatus(Constant.BUG_PENDING);
            bug.setImage(file.getBytes());
            bug.setImageName(toBugDto.getProjectId() + file.getOriginalFilename()+project.getClientId()) ;
            bug.setBugSolved(false);
            bug.setProjectId(toBugDto.getProjectId());
            bug.setStaffAssigned(false);

            return bugRepository.saveAndFlush(bug);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new BugNotAddedException();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ImageNotAddedException();
        }

    }

    public ResponseEntity<byte[]> getImageByBugId(Long bugId) {

        Bug bug = bugRepository.findByBugId(bugId);

        if (bug == null) {
            throw new BugNotFoundException();
        }
        byte[] image = bug.getImage();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

    public BugWithoutImage getBugImageByBugId(Long bugId) {

        Bug bug = bugRepository.findByBugId(bugId);

        if (bug == null) {
            throw new BugImageNotFoundException();
        }

        BugWithoutImage bugWithoutImage = new BugWithoutImage();
        bugWithoutImage.setBugId(bug.getBugId());
        bugWithoutImage.setBugNote(bug.getBugNote());
        bugWithoutImage.setBugSolved(bug.isBugSolved());
        bugWithoutImage.setStaffAssigned(bug.isStaffAssigned());
        bugWithoutImage.setSeverity(bug.getSeverity());
        bugWithoutImage.setProjectId(bug.getProjectId());
        bugWithoutImage.setImageName(bug.getImageName());
        bugWithoutImage.setStaffId(bug.getStaffId());
        bugWithoutImage.setStatus(bug.getStatus());

        return bugWithoutImage;
    }


    public List<BugWithoutImage> getAllBugs() {
        List<BugWithoutImage> bugList = new ArrayList<>();
        List<Bug> bugs = bugRepository.findAll();

        for (Bug bug : bugs) {
            BugWithoutImage bugWithoutImage = new BugWithoutImage();
            bugWithoutImage.setBugId(bug.getBugId());
            bugWithoutImage.setBugNote(bug.getBugNote());
            bugWithoutImage.setBugSolved(bug.isBugSolved());
            bugWithoutImage.setStaffAssigned(bug.isStaffAssigned());
            bugWithoutImage.setSeverity(bug.getSeverity());
            bugWithoutImage.setProjectId(bug.getProjectId());
            bugWithoutImage.setImageName(bug.getImageName());
            bugWithoutImage.setStaffId(bug.getStaffId());
            bugWithoutImage.setStatus(bug.getStatus());

            bugList.add(bugWithoutImage);
        }
        return bugList;
    }

    public Boolean tagStaff(Long bugId, Long staffId, String accessToken) {

        Bug bug = bugRepository.findByBugId(bugId);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
//        try {
//            new RestTemplate().exchange(RequestEntity.get(new URI("http://localhost:9002/users/getStaffById/" + staffId)).headers(headers).build(), User.class);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//
//        }

        if (bug == null) {
            throw new BugNotFoundException();
        }
        if (bug.isBugSolved() || bug.isStaffAssigned()) {
            throw new BugStatusException();
        }

        bug.setStaffAssigned(true);
        bug.setStatus(Constant.BUG_ASSIGNED);
        bug.setStaffId(staffId);

        bugRepository.saveAndFlush(bug);

        return true;
    }

    public Boolean solveBug(Long bugId) {
        Bug bug = bugRepository.findByBugId(bugId);

        if (bug == null) {
            throw new BugNotFoundException();
        } else if (!bug.isStaffAssigned() && !bug.getStatus().equals("assigned")) {
            throw new BugStatusException();
        } else if (bug.isBugSolved()) {
            throw new BugStatusException();
        } else {
            bug.setBugSolved(true);
            bug.setStatus(Constant.BUG_SOLVED);
            bugRepository.save(bug);

            return true;
        }

    }

    public Boolean transferBug(Long bugId, Long staffId, String accessToken) {

        Bug bug = bugRepository.findByBugId(bugId);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        try {
            new RestTemplate().exchange(RequestEntity.get(new URI("http://localhost:9002/users/getStaffById/" + staffId)).headers(headers).build(), User.class);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        if (bug == null) {
            throw new BugNotFoundException();
        }
        if (!bug.isStaffAssigned() && !bug.getStatus().equals(Constant.BUG_ASSIGNED)) {
            throw new BugStatusException();
        }
        if (bug.isBugSolved()) {
            throw new BugStatusException();
        }
        if(bug.getStaffId().equals(staffId)) {
            throw new BugTransferException();
        }
        bug.setStaffId(staffId);

        bugRepository.saveAndFlush(bug);

        return true;
    }
}