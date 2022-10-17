package com.azam.youtubeclone.controller;

import com.azam.youtubeclone.service.UserRegService;
import com.azam.youtubeclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private Logger log= LoggerFactory.getLogger(getClass());

  private final UserRegService userRegService;
  private final UserService userService;
    @GetMapping("/register")
    @ResponseStatus(HttpStatus.OK)

    public String register(Authentication authentication
    ){
        Jwt jwt= (Jwt) authentication.getPrincipal();
        log.info("Token is",jwt.getTokenValue());
       return userRegService.registerUser(jwt.getTokenValue());

    }
    @PostMapping("/subscribe/{userId}")
    @ResponseStatus(HttpStatus.OK)

    public boolean subscribeToUser(@PathVariable String userId){
        log.info("user id is ",userId);
       userService.subscribe(userId);
       return true;
    }
    @PostMapping("/unSubscribe/{userId}")
    @ResponseStatus(HttpStatus.OK)

    public boolean unSubscribe(@PathVariable String userId){
        userService.subscribe(userId);
        return true;
    }

    @GetMapping("/{userId}/history")
    @ResponseStatus(HttpStatus.OK)
    public Set<String> userHistory(@PathVariable String userId){
    return userService.getUserHistory(userId);
    }



}
