import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import{UploadVideoComponent} from './upload-video/upload-video.component';
import {SaveVideoDetailComponent} from './save-video-detail/save-video-detail.component';
import { VideoDetailsComponent } from './video-details/video-details.component';
import { HomeComponent } from './home/home.component';
import { SubscriptionsComponent } from './subscriptions/subscriptions.component';
import { HistoryComponent } from './history/history.component';
import { LikedVideosComponent } from './liked-videos/liked-videos.component';
import { FeaturesComponent } from './features/features.component';
const routes: Routes = [
{
path:'upload-video',component:UploadVideoComponent,

},
{
path:'save-video-details/:videoId',component:SaveVideoDetailComponent,
},
{
path:'video-details/:videoId',component:VideoDetailsComponent,
},
{
path:'',component:HomeComponent,
children:[{
path:'subscriptions',component:SubscriptionsComponent,
},
{
path:'history',component:HistoryComponent,
},
{
path:'liked-videos',component:LikedVideosComponent,
},
{
path:'featured',component:FeaturesComponent,
}]
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
