import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { AuthService } from '../../../services/core/auth.service';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.scss']
})
export class SideBarComponent implements OnInit { 

  role = sessionStorage.getItem('role')
  
  constructor(
    private router: Router,
    private authService : AuthService) { }

  ngOnInit(): void {
  }

  goToHomePage(){
    switch(this.role){
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
          this.router.navigate(["login"])
      } 
  }

  logout(){ 
    this.authService.logout();
  }
}
