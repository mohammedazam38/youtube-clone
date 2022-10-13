import { Component, OnInit } from '@angular/core';
import { OidcSecurityService } from 'angular-auth-oidc-client';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private oidcSecurityService:OidcSecurityService) { }
isAuthenticated:boolean=false;

  ngOnInit(): void {
    this.oidcSecurityService.isAuthenticated$.subscribe(({isAuthenticated})=>{
this.isAuthenticated=isAuthenticated;
    })
  }
login(){
      this.oidcSecurityService.authorize();
}
 logout() {
    this.oidcSecurityService.logoffAndRevokeTokens();
  }

}
