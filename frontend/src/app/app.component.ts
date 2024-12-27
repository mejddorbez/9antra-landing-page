import { Component, OnInit } from '@angular/core';

import * as $ from 'jquery';

declare function script(jQuery:any): void;
declare function plugins(jQuery:any): void;
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  ngOnInit(): void {
    $(() => {
      script($);
      plugins($);
      
      console.log("jQuery is working!");
    });
  }
}