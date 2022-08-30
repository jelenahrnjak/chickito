import { Component, OnInit } from '@angular/core';  
import { Router} from '@angular/router';
import { AuthService } from '../../../services/core/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  role;

  constructor(
    private router: Router,
    private authService : AuthService) { }

  ngOnInit(): void {
    this.role = sessionStorage.getItem('role')
  }

  logout(){ 
    this.authService.logout();
  }

  myProfile(){
    this.router.navigate(["my-profile"]); 
  }

  vacations(){
    this.router.navigate(["my-vacation-requests"]); 
  }

}
