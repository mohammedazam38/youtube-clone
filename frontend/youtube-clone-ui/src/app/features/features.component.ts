import { Component, OnInit } from '@angular/core';
import { VideoDto } from '../video-dto';
import { VideoService } from '../video.service';

@Component({
  selector: 'app-features',
  templateUrl: './features.component.html',
  styleUrls: ['./features.component.css']
})
export class FeaturesComponent implements OnInit {
featuredVideos:Array<VideoDto>=[];

  constructor(private videoService:VideoService) { }
  ngOnInit(): void {
    this.videoService.getAllVideos().subscribe(response=>{
      this.featuredVideos=response;
      
    })
    
  }


}
