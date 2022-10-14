import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import{UploadVideoComponent} from './upload-video/upload-video.component';
import {SaveVideoDetailComponent} from './save-video-detail/save-video-detail.component';
import { VideoDetailsComponent } from './video-details/video-details.component';
const routes: Routes = [
{
path:'upload-video',component:UploadVideoComponent,

},
{
path:'save-video-details/:videoId',component:SaveVideoDetailComponent,
},
{
path:'video-details/:videoId',component:VideoDetailsComponent,
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
