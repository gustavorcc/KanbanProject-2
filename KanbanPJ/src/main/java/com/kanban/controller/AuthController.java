package com.kanban.controller;

import com.kanban.dto.LoginDTO;
import com.kanban.services.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO login) {
        String token = tokenService.generateToken(login.username());
        return token;
    }
}