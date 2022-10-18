import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { OidcSecurityService } from 'angular-auth-oidc-client';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private matSnackBar:MatSnackBar,private oidcSecurityService:OidcSecurityService) { }
isAuthenticated:boolean=false;

  ngOnInit(): void {
    this.oidcSecurityService.isAuthenticated$.subscribe(({isAuthenticated})=>{
this.isAuthenticated=isAuthenticated;
    })
  }
login(){
      this.oidcSecurityService.authorize();
      this.matSnackBar.open("You have been loged in","OK");
}
 logout() {
  this.isAuthenticated=false;
    this.oidcSecurityService.logoffAndRevokeTokens();
    this.oidcSecurityService.logoffLocal();
          this.matSnackBar.open("Logged out","OK");

  }

}
