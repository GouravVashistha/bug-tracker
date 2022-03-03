package com.bugtracker.api.gateway;

import com.bugtracker.api.gateway.constant.Constant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackMethodController {

    @GetMapping("/userServiceFallBack")
    public String userServiceFallBackMethod() {
        return Constant.USER_FALLBACK;
    }

    @GetMapping("/roleServiceFallBack")
    public String roleServiceFallBackMethod() {
        return Constant.ROLE_FALLBACK;
    }

    @GetMapping("/projectServiceFallBack")
    public String projectServiceFallBackMethod() {
        return Constant.PROJECT_FALLBACK;
    }

    @GetMapping("/bugServiceFallBack")
    public String bugServiceFallBackMethod() {
        return Constant.BUG_FALLBACK;
    }

    @GetMapping("/commentsServiceFallBack")
    public String commentsServiceFallBackMethod() {
        return Constant.COMMENT_FALLBACK;
    }
}

