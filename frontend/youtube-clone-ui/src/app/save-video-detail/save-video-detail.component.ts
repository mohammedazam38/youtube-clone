import { Component, OnInit } from '@angular/core';
import {FormGroup} from '@angular/forms';
import { FormControl } from '@angular/forms';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {MatChipInputEvent} from '@angular/material/chips';

@Component({
  selector: 'app-save-video-detail',
  templateUrl: './save-video-detail.component.html',
  styleUrls: ['./save-video-detail.component.css']
})
export class SaveVideoDetailComponent implements OnInit {
saveVideoDetailsForm: FormGroup;
title:FormControl= new FormControl('');
description:FormControl= new FormControl('');
videoStatus:FormControl= new FormControl('');
addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  tags: string[] = [];
  selectedFile!:File;
constructor() {
    this.saveVideoDetailsForm=new FormGroup({
      title:this.title,
      description:this.description,
      videoStatus: this.videoStatus,

    })
   }

  ngOnInit(): void {
  }
  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.tags.push(value);
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  remove(value: string): void {
    const index = this.tags.indexOf(value);

    if (index >= 0) {
      this.tags.splice(index, 1);
    }
  }
  
  onFileSelected(){


  }
  

}
