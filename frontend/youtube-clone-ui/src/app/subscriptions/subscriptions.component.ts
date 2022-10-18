import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { VideoDto } from '../video-dto';
import { VideoService } from '../video.service';

@Component({
  selector: 'app-subscriptions',
  templateUrl: './subscriptions.component.html',
  styleUrls: ['./subscriptions.component.css']
})
export class SubscriptionsComponent implements OnInit {

   subscribedVideos:Array<VideoDto>=[];
  constructor(private userService:UserService) { }
  ngOnInit(): void {
  
      let userId=this.userService.getUserId();
 this.userService.getAllVideos(userId).subscribe(response=>{
      this.subscribedVideos=response;
    })
   
    
  }
    
    
}

