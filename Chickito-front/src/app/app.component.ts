import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'Chickito-front'; 
  role = sessionStorage.getItem('role')
  route = this.router.url
  currentComponent = ''

  constructor( 
    private router: Router, 
  ) { }

  ngOnInit() { 
  }

  showHeader(){
    
    return this.router.url !== '/login' && this.currentComponent != 'ErrorPageComponent' && sessionStorage.getItem('role') !== undefined
  } 
  
  public onRouterOutletActivate(event : any) {
    this.currentComponent = event.constructor.name
    console.log(event);
}
}
