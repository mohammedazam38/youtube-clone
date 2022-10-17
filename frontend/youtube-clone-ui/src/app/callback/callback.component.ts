import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-callback',
  templateUrl: './callback.component.html',
  styleUrls: ['./callback.component.css']
})
export class CallbackComponent implements OnInit {

  constructor(private router :Router,private userService:UserService) { console.log("here");
    this.userService.registerUser();
    this.router.navigateByUrl('');
  }

  ngOnInit(): void {

  }

}
