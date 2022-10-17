import { Injectable } from '@angular/core';
import { FileSystemFileEntry } from 'ngx-file-drop';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import{UploadVideoResponse} from './upload-video/UploadVideoResponse';
import { VideoDto } from './video-dto';
@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private httpClient: HttpClient) { }
  uploadVideo(fileEntry: File):Observable<UploadVideoResponse>{
  //https post call
   const formData = new FormData()
            formData.append('file', fileEntry, fileEntry.name);
  return this.httpClient.post<UploadVideoResponse>("http://localhost:8080/api/videos",formData);

  }
   uploadThumbnail(fileEntry: File,videoId:string):Observable<string>{
  //https post call
   const formData = new FormData();
            formData.append('file', fileEntry, fileEntry.name);
            formData.append('videoId',videoId);
  return this.httpClient.post("http://localhost:8080/api/videos/thumbnail",formData,{responseType:'text'});

  }
  getVideoDetails(videoId:string):Observable<VideoDto>{
    return this.httpClient.get<VideoDto>("http://localhost:8080/api/videos/"+videoId);
  }
  saveVideo(videoMetaData:VideoDto):Observable<VideoDto>{
 return this.httpClient.put<VideoDto>("http://localhost:8080/api/videos",videoMetaData);
  }
  getAllVideos():Observable<Array<VideoDto>>{
    return this.httpClient.get<Array<VideoDto>>("http://localhost:8080/api/videos");
  }
likeVideo(videoId:string):Observable<VideoDto>{
 return this.httpClient.post<VideoDto>("http://localhost:8080/api/videos/"+videoId+"/like",null);
}
disLikeVideo(videoId:string):Observable<VideoDto>{
 return this.httpClient.post<VideoDto>("http://localhost:8080/api/videos/"+videoId+"/dislike",null);
}
}
