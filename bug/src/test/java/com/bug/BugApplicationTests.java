package com.bug;

import com.bug.constant.Constant;
import com.bug.entity.Bug;
import com.bug.repository.BugRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BugApplicationTests {

    @Autowired
    BugRepository bugRepository;

    @Test
    @Order(1)
    void bugLoads(){
        assertNotNull(bugRepository.findAll());
    }

    @Test
    @Order(2)
    void saveBugTest(){
        Bug bug= new Bug();
            bug.setBugId(1L);
            bug.setBugSolved(false);
            bug.setStaffAssigned(false);
            bug.setBugNote("This is a test case for Bug micro-service");
            bug.setSeverity("test");
            bug.setImage(new byte[] { (byte)0xba, (byte)0x8a, 0x0d,
                    0x45, 0x25, (byte)0xad, (byte)0xd0, 0x11, (byte)0x98, (byte)0xa8, 0x08, 0x00,
                    0x36, 0x1b, 0x11, 0x03 });
            bug.setImageName(0L + "testBugImg" +
                    0L + Math.random() * (1000000 - 1000 + 1) + 1000);
            bug.setStatus(Constant.BUG_PENDING);
            bug.setProjectId(0L);

        bugRepository.save(bug);
        Assertions.assertThat(bug.getBugId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    void getListBugTest(){
        List<Bug> bugList=bugRepository.findAll();
        Assertions.assertThat(bugList).isNotEmpty();
    }

    @Test
    @Order(4)
    void editBug(){
        Bug bug=bugRepository.findByBugId(1L);
        bug.setSeverity("test");
        Assertions.assertThat(bugRepository.save(bug).getSeverity()).isEqualTo("test");
    }

    @Test
    @Order(5)
    void getBugTest(){
        Bug bug=bugRepository.findByBugId(1L);
        Assertions.assertThat(bug.getBugId()).isEqualTo(1L);

    }
}
