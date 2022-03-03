package com.bugtracker.config;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= CloudConfigServiceApplication.class)
class CloudConfigServiceApplicationTests {

    @Value("${server.port}")
    private int port;

    @Test
    @Order(1)
    void portTest(){
        Assertions.assertThat(port).isEqualTo(9296);
    }

}
