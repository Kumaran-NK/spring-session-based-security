package com.example.demo.controller;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");
        Authentication authentication = service.login(email, password);
        return "Logged in: " + authentication.getName();
    }

    @GetMapping("/me")
    public String me(Authentication authentication) {
        return "Logged in user: " + authentication.getName();
    }

    @GetMapping("/session")
    public String getSessionId(HttpSession session) {
        System.out.println("Session ID: " + session.getId());
        return "Session ID: " + session.getId();
    }

}