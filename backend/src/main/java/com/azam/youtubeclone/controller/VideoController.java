package com.azam.youtubeclone.controller;

import com.azam.youtubeclone.dto.CommentDto;
import com.azam.youtubeclone.dto.UploadVideoResponse;
import com.azam.youtubeclone.dto.VideoDto;
import com.azam.youtubeclone.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor

public class VideoController {


    private final VideoService videoService;

    private Logger log= LoggerFactory.getLogger(getClass());

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

    @GetMapping("/{videoId}")
    @ResponseStatus(HttpStatus.OK)
    public VideoDto getVideoDetails(@PathVariable String videoId){


        return videoService.getVideoDetails(videoId);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public VideoDto editVideoMetadata(@RequestBody VideoDto videoDto){

    videoService.editVideo(videoDto);
    return videoDto;
    }

    @PostMapping("/{videoId}/like")
    @ResponseStatus(HttpStatus.OK)
    public VideoDto likeVideo(@PathVariable String videoId){
        return videoService.likeVideo(videoId);
    }

    @PostMapping("/{videoId}/dislike")
    @ResponseStatus(HttpStatus.OK)
    public VideoDto dislikeVideo(@PathVariable String videoId){
        return videoService.dislikeVideo(videoId);
    }

    @PostMapping("/{videoId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public  void addComment(@PathVariable String videoId, @RequestBody CommentDto commentDto){
       videoService.addComment(videoId,commentDto);
    }
    @GetMapping("/{videoId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllComments(@PathVariable String videoId){
        log.info("video Id is ",videoId);
        List<CommentDto> list= videoService.getAllCommentsById(videoId);

        return list;
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<VideoDto> getAllVideos(){
        List<VideoDto> list= videoService.getAllVideos();

        return list;
    }


}
