import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-error-page',
  templateUrl: './error-page.component.html',
  styleUrls: ['./error-page.component.scss']
})
export class ErrorPageComponent implements OnInit {

  constructor(
    private router: Router,) { }

  ngOnInit(): void {
  }

  goBack(){
    const role = sessionStorage.getItem('role')
    switch(role){
      case "ADMIN":
        this.router.navigate(["admin"]); 
        break;
      case "DIRECTOR":
        this.router.navigate(["director"]);
        break;
      case "LEADER":
        this.router.navigate(["leader"]);
        break;
      case "WORKER":
        this.router.navigate(["worker"]);
        break;
      default:
          this.router.navigate(["auth/login"])
      } 
  }

}
