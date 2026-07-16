package com.ps.user_service.paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class PathConfig {

    @Bean(name = "publicPaths")
    public List<String> publicPaths() {
        return List.of("/user/login",
                "/user/signup",
                "/user/sendOTP",
                "/user/verifyOTP",
                "/user/forgetPassword",
                "/user/activate",
                "/user/refresh");
    }

    @Bean(name = "privatePaths")
    public List<String> privatePaths() {
        return List.of("/user/update",
                "/user/delete",
                "/user/updatePassword",
                "/user/me");
    }

    @Bean(name = "adminPaths")
    public List<String> adminPaths() {
        return List.of("/user/getAll");
    }
}
