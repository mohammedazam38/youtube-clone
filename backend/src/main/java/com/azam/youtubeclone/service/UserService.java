package com.azam.youtubeclone.service;

import com.azam.youtubeclone.model.User;
import com.azam.youtubeclone.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;




    public User getCurrentUser() {
        //get the current user by using the sub from the OAuth2
       String sub= ((Jwt)(SecurityContextHolder.getContext().getAuthentication().getPrincipal())).getClaim("sub");
      return userRepo.findBySub(sub)
              .orElseThrow(()-> new IllegalArgumentException("Can not find the sub"));


       
    }

    public void addToLikedVideo(String videoId) {
        User user= getCurrentUser();
        user.addToLikedVideo(videoId);
        userRepo.save(user);
    }
    public boolean ifLikedVideoPresnet(String videoId){
       return getCurrentUser().getLikeVideos().contains(videoId);

    }
    public boolean ifDislikedVideoPresent(String videoId){
        return getCurrentUser().getDisLikedVideos().contains(videoId);
    }


    public void removeFromLikedVideo(String videoId) {
        User user= getCurrentUser();
                user.removeFromLikedVideo(videoId);
                userRepo.save(user);
    }

    public void removeFromDisliked(String videoId) {
        User user= getCurrentUser();
        user.removeFromDislikedVideo(videoId);
        userRepo.save(user);
    }

    public void addToDisliked(String videoId) {
        User user= getCurrentUser();
        user.addToDisliked(videoId);
        userRepo.save(user);
    }
}
