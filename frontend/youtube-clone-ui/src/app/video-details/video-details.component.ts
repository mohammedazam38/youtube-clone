import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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
tags!:Array<string>;
videoDescription!:string;
  constructor(private activatedRoute:ActivatedRoute,private VideoService:VideoService) {
      this.videoId=this.activatedRoute.snapshot.params.videoId;
   this.VideoService.getVideoDetails(this.videoId).subscribe(data=>{
  this.videoUrl=data.videoUrl;
  this.videoTitle=data.title;
  this.tags=data.tags;
  this.videoDescription=data.description;
  this.videoAvailabe=true;
  })
   }

  ngOnInit(): void {
  }

}
