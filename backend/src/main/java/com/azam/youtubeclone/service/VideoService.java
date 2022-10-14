package com.azam.youtubeclone.service;


import com.azam.youtubeclone.dto.UploadVideoResponse;
import com.azam.youtubeclone.dto.VideoDto;
import com.azam.youtubeclone.model.Video;
import com.azam.youtubeclone.repo.VideoRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
@RequiredArgsConstructor
public class VideoService {
    private final S3Service s3Service;

   private final VideoRepo videoRepo;
    private Logger log= LoggerFactory.getLogger(getClass());
private final UserService userService;

    public UploadVideoResponse uploadVideo(MultipartFile file){
   String videoUrl= s3Service.uploadFile(file);
   var video= new Video();
   video.setVideourl(videoUrl);
videoRepo.save(video);
 return new UploadVideoResponse(video.getId(),videoUrl);
    }

    public VideoDto editVideo(VideoDto videoDto) {
        //Find the video by Id
        //Map videoDto to video

        Video savedVideo= findVideo(videoDto.getId());
        savedVideo.setThumbnailUrl(videoDto.getThumbnailUrl());;
        savedVideo.setTitle(videoDto.getTitle());
        savedVideo.setDescription(videoDto.getDescription());
        savedVideo.setStatus(videoDto.getStatus());
        savedVideo.setTags(videoDto.getTags());
 videoRepo.save(savedVideo);
 return videoDto;
    }
   public Video findVideo(String id){
        return videoRepo.findById(id).orElseThrow(()->new IllegalArgumentException("Can not find vide  by id"+id));
   }
    public String uploadThumbnail(MultipartFile file,String videoId) {
      Video savedVideo=findVideo(videoId);
      String thumbNail=s3Service.uploadFile(file);
      savedVideo.setThumbnailUrl(thumbNail);
      videoRepo.save(savedVideo);
      return thumbNail;
    }

    public VideoDto getVideoDetails(String videoId) {
        Video videoDetail=findVideo(videoId);
        VideoDto videoDto= new VideoDto();
        videoDto.setVideoUrl(videoDetail.getVideourl());
        videoDto.setThumbnailUrl(videoDetail.getThumbnailUrl());
        videoDto.setTags(videoDetail.getTags());
        videoDto.setId(videoDetail.getId());
        videoDto.setDescription(videoDetail.getDescription());
        videoDto.setTitle(videoDetail.getTitle());
        return videoDto;
    }

    public VideoDto likeVideo(String videoId) {
       //Increment like count
        //If user already likes the video, then decrement like
        //like-0 dislike -0
        //like-1 dislike-0
        //like -0 dislike-1
        Video videoDetail=findVideo(videoId);
        if(userService.ifLikedVideoPresnet(videoId)){
            videoDetail.decrementLike();
            userService.removeFromLikedVideo(videoId);
        }
        else if(userService.ifDislikedVideoPresent(videoId)){
            videoDetail.decrementDislike();
   userService.removeFromDisliked(videoId);
            videoDetail.incrementLike();
   userService.addToLikedVideo(videoId);



        }else{
            videoDetail.incrementLike();
            userService.addToLikedVideo(videoId);
        }
        videoRepo.save(videoDetail);

        VideoDto videoDto= new VideoDto();
        videoDto.setVideoUrl(videoDetail.getVideourl());
        videoDto.setThumbnailUrl(videoDetail.getThumbnailUrl());
        videoDto.setTags(videoDetail.getTags());
        videoDto.setId(videoDetail.getId());
        videoDto.setDescription(videoDetail.getDescription());
        videoDto.setTitle(videoDetail.getTitle());
        videoDto.setLikeCount(videoDetail.getLikes().get());
        videoDto.setDisLikeCount(videoDetail.getDisLikes().get());

        return videoDto ;




    }

    public VideoDto dislikeVideo(String videoId) {
        Video videoDetail=findVideo(videoId);

         if(userService.ifDislikedVideoPresent(videoId)){
            videoDetail.decrementDislike();
            userService.removeFromDisliked(videoId);
        }else if(userService.ifLikedVideoPresnet(videoId)){
             videoDetail.decrementLike();
             userService.removeFromLikedVideo(videoId);
             videoDetail.incrementDislike();
             userService.addToDisliked(videoId);
         }
         else{
            videoDetail.incrementDislike();
            userService.addToDisliked(videoId);
        }
        videoRepo.save(videoDetail);

        VideoDto videoDto= new VideoDto();
        videoDto.setVideoUrl(videoDetail.getVideourl());
        videoDto.setThumbnailUrl(videoDetail.getThumbnailUrl());
        videoDto.setTags(videoDetail.getTags());
        videoDto.setId(videoDetail.getId());
        videoDto.setDescription(videoDetail.getDescription());
        videoDto.setTitle(videoDetail.getTitle());
        videoDto.setLikeCount(videoDetail.getLikes().get());
        videoDto.setDisLikeCount(videoDetail.getDisLikes().get());

        return videoDto ;




    }
}
