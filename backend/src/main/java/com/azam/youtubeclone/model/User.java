package com.azam.youtubeclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
private Set<String> subscribedToUsers= ConcurrentHashMap.newKeySet();
private Set<String> subscribers= ConcurrentHashMap.newKeySet();
private Set<String> videoHistory=ConcurrentHashMap.newKeySet();
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

    public void addToVideoHistory(String id) {
        videoHistory.add(id);
    }

    public void addToSubscribeToUser(String userId) {
       subscribedToUsers.add(userId);
    }

    public void addTOSubscribers(String id) {
        subscribers.add(id);
    }

    public void removeFromSubscribedToUser(String userId) {
        subscribedToUsers.remove(userId);
    }

    public void removeFromSubscribe(String id) {
        subscribers.remove(id);
    }
}
