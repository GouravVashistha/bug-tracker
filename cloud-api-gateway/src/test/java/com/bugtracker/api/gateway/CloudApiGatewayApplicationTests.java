package com.bugtracker.api.gateway;

import com.bugtracker.api.gateway.constant.Constant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CloudApiGatewayApplication.class)
class CloudApiGatewayApplicationTests {

    @Value("${local.server.port}")
    private int port;

    @Test
    @Order(1)
    void userLoads() {
        Assertions.assertThat(new TestRestTemplate().getForEntity("http://localhost:" + port + "/userServiceFallBack", String.class).getBody()).isEqualTo(Constant.USER_FALLBACK);
    }

    @Test
    @Order(2)
    void roleLoads() {
       Assertions.assertThat(new TestRestTemplate().getForEntity("http://localhost:" + port + "/roleServiceFallBack", String.class).getBody()).isEqualTo(Constant.ROLE_FALLBACK);
    }

    @Test
    @Order(3)
    void projectLoads() {
        Assertions.assertThat(new TestRestTemplate().getForEntity("http://localhost:" + port + "/projectServiceFallBack", String.class).getBody()).isEqualTo(Constant.PROJECT_FALLBACK);
    }

    @Test
    @Order(4)
    void bugLoads() {
        Assertions.assertThat(new TestRestTemplate().getForEntity("http://localhost:" + port + "/bugServiceFallBack", String.class).getBody()).isEqualTo(Constant.BUG_FALLBACK);
    }

    @Test
    @Order(5)
    void commentLoads() {
        Assertions.assertThat(new TestRestTemplate().getForEntity("http://localhost:" + port + "/commentsServiceFallBack", String.class).getBody()).isEqualTo(Constant.COMMENT_FALLBACK);
    }
}
