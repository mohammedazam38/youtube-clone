import { Component, OnInit } from '@angular/core';
import {FormGroup} from '@angular/forms';
import { FormControl } from '@angular/forms';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {MatChipInputEvent} from '@angular/material/chips';
import { ActivatedRoute } from '@angular/router';
import { VideoService } from '../video.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import { VideoDto } from '../video-dto';

@Component({
  selector: 'app-save-video-detail',
  templateUrl: './save-video-detail.component.html',
  styleUrls: ['./save-video-detail.component.css']
})
export class SaveVideoDetailComponent implements OnInit {
saveVideoDetailsForm: FormGroup;
title:FormControl= new FormControl('');
description:FormControl= new FormControl('');
videoStatus:FormControl= new FormControl('');
addOnBlur = true;
selectable=true;
removable=true;

  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  tags: string[] = [];
  selectedFile!:File;
  selectedFileName='';
  videoId='';
  fileUpload=false;
  videoUrl!:string;
  thumbnailUrl!:string;
  likeCount:number=0;
      disLikeCount:number=0;
            viewCount:number=0;

  
constructor(private activatedRoute:ActivatedRoute,private VideoService:VideoService,
  private matSnackBar:MatSnackBar) {
  this.videoId=this.activatedRoute.snapshot.params.videoId;
  this.VideoService.getVideoDetails(this.videoId).subscribe(data=>{
    
      this.videoUrl=data.videoUrl;
  })
    this.saveVideoDetailsForm=new FormGroup({
      title:this.title,
      description:this.description,
      videoStatus: this.videoStatus,

    })
   }

  ngOnInit(): void {
  }
  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.tags.push(value);
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  remove(value: string): void {
    const index = this.tags.indexOf(value);

    if (index >= 0) {
      this.tags.splice(index, 1);
    }
  }
  
  onFileSelected(event :Event ){
    // @ts-ignore: Object is possibly 'null'
this.selectedFile =event.target.files[0];
this.selectedFileName=this.selectedFile.name;
this.fileUpload=true;
  }
  onUpload(){
    
   this.VideoService.uploadThumbnail(this.selectedFile,this.videoId)
   .subscribe(data=>{
    //show a upload success notification
    this.thumbnailUrl=data;
    this.matSnackBar.open("Thumbnail upload successfull","ok");
    
   })
  
  }
  saveVideo(){
    const videoMetaData:VideoDto={
      "id":this.videoId,
      "title":this.saveVideoDetailsForm.get('title')?.value,
      "description":this.saveVideoDetailsForm.get('description')?.value,
      "tags":this.tags,
      "status":this.saveVideoDetailsForm.get('videoStatus')?.value,
      "videoUrl":this.videoUrl,
      "thumbnailUrl":this.thumbnailUrl,
      "likeCount":this.likeCount,
      "disLikeCount":this.disLikeCount,
      "viewCount":this.viewCount

    }
    console.log("thumb nail url is"+this.thumbnailUrl);
   
    this.VideoService.saveVideo(videoMetaData).subscribe(data=>{
      this.matSnackBar.open("Video details updated successfully","OK");
    })
   
  }

}
