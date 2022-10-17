import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../user.service';
import { VideoService } from '../video.service';
@Component({
  selector: 'app-video-details',
  templateUrl: './video-details.component.html',
  styleUrls: ['./video-details.component.css']
})
export class VideoDetailsComponent implements OnInit {
  videoId!:string;
  videoUrl!:string;
  
  videoAvailabe:boolean=false;
videoTitle!:string;
tags:Array<string>=[];
videoDescription!:string;
likeCount:number=0;
      disLikeCount:number=0;
      viewCount:number=0;
      showSubscribeButton:boolean=true;
      showUnSubscribeButton:boolean=false;
  constructor(private activatedRoute:ActivatedRoute,private VideoService:VideoService,private userService:UserService) {
      this.videoId=this.activatedRoute.snapshot.params.videoId;
   this.VideoService.getVideoDetails(this.videoId).subscribe(data=>{
  this.videoUrl=data.videoUrl;
  this.videoTitle=data.title;
  this.tags=data.tags;
  this.videoDescription=data.description;
  this.videoAvailabe=true;
  this.likeCount=data.likeCount;
  this.disLikeCount=data.disLikeCount;
  this.viewCount=data.viewCount;
  })
   }

  ngOnInit(): void {
  }
likeVideo(){
this.VideoService.likeVideo(this.videoId)
.subscribe(data=>{
  this.likeCount=data.likeCount;
  this.disLikeCount=data.disLikeCount;
})
}
disLikeVideo(){
this.VideoService.disLikeVideo(this.videoId)
.subscribe(data=>{
  this.likeCount=data.likeCount;
  this.disLikeCount=data.disLikeCount;
})
}

subscribeToUser(){
  let userId=this.userService.getUserId();
  console.log("user  id is"+userId);
this.userService.subscribeToUser(userId)
.subscribe(data=>{
  this.showSubscribeButton=false;
  this.showUnSubscribeButton=true;
});
}
unSubscribeToUser(){
   let userId=this.userService.getUserId();
this.userService.unSubscribeToUser(userId)
.subscribe(data=>{
  this.showSubscribeButton=true;
  this.showUnSubscribeButton=false;
});
}
}
