import { Injectable } from '@angular/core';
import { FileSystemFileEntry } from 'ngx-file-drop';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import{UploadVideoResponse} from './upload-video/UploadVideoResponse';
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
}
