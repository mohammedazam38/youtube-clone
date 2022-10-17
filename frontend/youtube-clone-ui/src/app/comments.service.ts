import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import { commentDto } from './commentDto';

@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  constructor(private httpClient: HttpClient) {
  }

  postComment(commentDto: any, videoId: string): Observable<any> {
    return this.httpClient.post<any>("http://localhost:8080/api/videos/" + videoId + "/comment", commentDto);
  }

  getAllComments(videoId: string): Observable<Array<commentDto>> {
    return this.httpClient.get<commentDto[]>("http://localhost:8080/api/videos/" + videoId + "/comment");
  }
}
