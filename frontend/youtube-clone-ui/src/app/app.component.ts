import { Component, OnInit } from '@angular/core';
import { OidcSecurityService } from 'angular-auth-oidc-client';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'youtube-clone-ui';
  constructor(private oidcSecurityService :OidcSecurityService){

  
  }
  isAuthenticated:boolean=false;
    ngOnInit():void{
    this.oidcSecurityService.checkAuth().subscribe(({ isAuthenticated}) => {
      this.isAuthenticated=isAuthenticated;
      console.log('app is authenticated',isAuthenticated);
      /*...*/
    });
  }
}
