package com.bug.controller;

import com.bug.dto.BugWithoutImage;
import com.bug.dto.TagStaffDto;
import com.bug.entity.Bug;
import com.bug.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins= "http://localhost:3000")
@RestController
@RequestMapping("/bugs")
public class BugController {

    @Autowired
    BugService bugService;

    @PostMapping("/addBug")
    @ResponseBody
    public Bug saveBug(@RequestParam String bugDto, @RequestParam("img") MultipartFile file) {

        return bugService.addBugs(bugDto, file);
    }

    @GetMapping("/bugImageById/{id}")
    public ResponseEntity<byte[]> fromDatabaseAsResEntity(@PathVariable("id") Long bugId) {

        return bugService.getImageByBugId(bugId);
    }

    @GetMapping("/bugById/{id}")
    public BugWithoutImage getBugWithImage(@PathVariable("id") Long bugId) {
        return bugService.getBugImageByBugId(bugId);
    }

    @GetMapping("/")
    public List<BugWithoutImage> getAllBugs() {
        return bugService.getAllBugs();
    }

    @PostMapping("/tagStaff")
    public Boolean tagStaff(@RequestBody TagStaffDto tagStaffDto) {
        return bugService.tagStaff(tagStaffDto.getBugId(), tagStaffDto.getStaffId()
                , tagStaffDto.getAccessToken());
    }

    @GetMapping("/solveBug/{id}")
    public Boolean solveBug(@PathVariable("id") Long bugId) {
        return bugService.solveBug(bugId);
    }

    @GetMapping("/transferBug")
    public Boolean solveBug(@RequestBody TagStaffDto tagStaffDto) {
        return bugService.transferBug(tagStaffDto.getBugId(), tagStaffDto.getStaffId()
                , tagStaffDto.getAccessToken());
    }

}
