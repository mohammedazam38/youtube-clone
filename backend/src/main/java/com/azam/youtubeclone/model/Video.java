package com.azam.youtubeclone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Document(value="Video")

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Video {

    @Id
    private String id;
    private String title;
    private String description;
    private String userId;
    private AtomicInteger likes=new AtomicInteger(0);
    private AtomicInteger disLikes= new AtomicInteger(0);
    // tag for videos so set
    private Set<String> tags;
    private String Videourl;
    private VideoStatus status;
    private Integer viewCount;
    private String thumbnailUrl;
    private List<Comment> commentList;

    public void incrementLike(){
        likes.incrementAndGet();
    }
    public void decrementLike(){
        likes.decrementAndGet();
    }

    public void incrementDislike(){
        disLikes.incrementAndGet();
    }
    public void decrementDislike(){
        disLikes.decrementAndGet();
    }

}
