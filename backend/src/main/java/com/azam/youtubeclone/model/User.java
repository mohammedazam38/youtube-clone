package com.azam.youtubeclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Document(value="User")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
@Id
    private String id;
private String firstName;
private String lastName;
private String fullName;
private String emailAddress;
private String sub;
private Set<String> subscribedToUsers;
private Set<String> subscribers;
private List<String> videoHistory;
private Set<String> likeVideos= ConcurrentHashMap.newKeySet();
private Set<String> disLikedVideos=ConcurrentHashMap.newKeySet();

    public void addToLikedVideo(String videoId) {
     likeVideos.add(videoId);
    }
    public void removeFromLikedVideo(String videoId){
        likeVideos.remove(videoId);
    }

    public void removeFromDislikedVideo(String videoId) {
        disLikedVideos.remove(videoId);
    }

    public void addToDisliked(String videoId) {
        disLikedVideos.add(videoId);
    }
}
