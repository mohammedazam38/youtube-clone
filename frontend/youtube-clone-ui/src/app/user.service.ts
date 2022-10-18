import { _getFocusedElementPierceShadowDom } from '@angular/cdk/platform';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { stringToKeyValue } from '@angular/flex-layout/extended/style/style-transforms';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userid:string='';
  constructor(private httpClient: HttpClient) { }

  subscribeToUser(userId:string):Observable<boolean>{
    console.log("here from inside method"+userId);
   return this.httpClient.post<boolean>("http://localhost:8080/api/user/subscribe/"+userId,null);

  }
  unSubscribeToUser(userId:string):Observable<boolean>{
   return this.httpClient.post<boolean>("http://localhost:8080/api/user/unSubscribe/"+userId,null);

  }
  registerUser(){
     this.httpClient.get("http://localhost:8080/api/user/register",{responseType: 'text'}).subscribe(data=>{
      this.userid=data;
  console.log("user id is"+this.userid);
     });

     
  }
  getUserId(){
      return this.userid;
     }
     getAllVideos(userId:string):Observable<any>{
     
       console.log("user id"+userId);
      return this.httpClient.get("http://localhost:8080/api/user/subscriptions/"+userId);
     }

}
