package com.azam.youtubeclone.dto;


import com.azam.youtubeclone.model.VideoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {

    private String id;
    private String title;
    private String description;
    private Set<String> tags;
    private VideoStatus status;
    private String thumbnailUrl;
    private String videoUrl;
    private Integer likeCount;
    private Integer disLikeCount;
}
