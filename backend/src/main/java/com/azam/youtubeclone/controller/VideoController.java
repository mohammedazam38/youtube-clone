package com.azam.youtubeclone.controller;

import com.azam.youtubeclone.dto.UploadVideoResponse;
import com.azam.youtubeclone.dto.VideoDto;
import com.azam.youtubeclone.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor

public class VideoController {


    private final VideoService videoService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UploadVideoResponse uploadVideo(@RequestParam("file") MultipartFile file) {

        return videoService.uploadVideo(file);
    }

    @PostMapping("/thumbnail")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadThumbnail(@RequestParam("file") MultipartFile file,@RequestParam("videoId") String videoId) {
       return videoService.uploadThumbnail(file,videoId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public VideoDto editVideoMetadata(@RequestBody VideoDto videoDto){
    videoService.editVideo(videoDto);
    return videoDto;
    }

}
