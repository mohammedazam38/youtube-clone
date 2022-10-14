package com.azam.youtubeclone.service;

import com.azam.youtubeclone.dto.UserInfoDto;
import com.azam.youtubeclone.model.User;
import com.azam.youtubeclone.repo.UserRepo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
public class UserRegService {

     @Value("https://dev-solml3pe.us.auth0.com/userinfo")
    private String userInfoEndPoint;
    private final UserRepo userRepo;
    public void registerUser(String tokenValue){
       HttpRequest httpRequest= HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(userInfoEndPoint))
                .setHeader("Authorization",String.format("Bearer %s",tokenValue))

                .build();
        HttpClient httpClient=HttpClient.newBuilder()
                .version( HttpClient.Version.HTTP_2)
                .build();
        try{
           HttpResponse<String> response= httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
       String body= response.body();
            ObjectMapper objectMapper= new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            UserInfoDto userInfoDTO =  objectMapper.readValue(body, UserInfoDto.class);
            User user= new User();
            user.setFirstName(userInfoDTO.getGivenName());
            user.setLastName(userInfoDTO.getFamilyName());
            user.setFullName(userInfoDTO.getName());
            user.setEmailAddress(userInfoDTO.getEmail());
            user.setSub(userInfoDTO.getSub());
            userRepo.save(user);
        }catch (Exception e){
            throw  new RuntimeException("Exception occured while registering user");
        }
    }
}
