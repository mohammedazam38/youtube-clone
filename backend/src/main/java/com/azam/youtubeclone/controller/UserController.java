package com.azam.youtubeclone.controller;

import com.azam.youtubeclone.service.UserRegService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

  private final UserRegService userRegService;
    @GetMapping("/register")
    public String register(Authentication authenticatuion){
        Jwt jwt= (Jwt) authenticatuion.getPrincipal();
        userRegService.registerUser(jwt.getTokenValue());
        return "userRegistration Success full";
    }

}
