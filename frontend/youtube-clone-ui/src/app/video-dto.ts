export interface VideoDto{
       id:string;
      title:string;
      description:string;
    tags:Array<string>;
     status:string;
      thumbnailUrl:string;
      videoUrl:string;
        likeCount:number;
      disLikeCount:number;
      viewCount:number;
}