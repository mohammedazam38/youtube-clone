package com.azam.youtubeclone.service;

import com.azam.youtubeclone.model.User;
import com.azam.youtubeclone.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Set;

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

    public void addVideoHistory(String  videoId) {
        User user= getCurrentUser();
        user.addToVideoHistory(videoId);
        userRepo.save(user);
    }

    public void subscribe(String userId) {
        User curUser= getCurrentUser();
        curUser.addToSubscribeToUser(userId);
       User user= userRepo.findById(userId)
               .orElseThrow(()-> new IllegalArgumentException("User can not find with the Id"+ userId)) ;
      user.addTOSubscribers(user.getId());
      userRepo.save(user);
      userRepo.save(curUser);

    }
    public void unSubscribe(String userId) {
        User curUser= getCurrentUser();
        curUser.removeFromSubscribedToUser(userId);
        User user= userRepo.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("User can not find with the Id"+ userId)) ;
        user.removeFromSubscribe(user.getId());
        userRepo.save(user);
        userRepo.save(curUser);

    }

    public Set<String> getUserHistory(String userId) {
        User user= userRepo.findById(userId)
                .orElseThrow(()-> new IllegalArgumentException("User can not find with the Id"+ userId)) ;
       return
               user.getVideoHistory();
    }
}
