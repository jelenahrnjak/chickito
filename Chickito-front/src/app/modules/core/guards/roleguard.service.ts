import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router } from '@angular/router';
import { AuthguardService } from './authguard.service';

@Injectable({
  providedIn: 'root'
})
export class RoleguardService {

  constructor(public authguardService : AuthguardService, public router: Router) {}
  canActivate(route: ActivatedRouteSnapshot): boolean {

    const expectedRole = route.data['expectedRole'];
    const token = sessionStorage.getItem('user');
    let currentRole : any = "";
    
    if (token != null){
      let jwtData = token.split('.')[1]
      let decodedJwtJsonData = window.atob(jwtData)
      let decodedJwtData = JSON.parse(decodedJwtJsonData)

      currentRole = sessionStorage.getItem('role')
    }


    if (!this.authguardService.gettoken() || !currentRole.includes(expectedRole)) {
      let role =  sessionStorage.getItem('role') 


      switch(role){
        case "ADMIN":
          this.router.navigate(["admin-home"]); 
          break;
        case "DIRECTOR":
          this.router.navigate(["director-home"]);
          break;
        case "LEADER":
          this.router.navigate(["leader-home"]);
          break;
        case "WORKER":
          this.router.navigate(["worker-home"]);
          break;
        default:
            this.router.navigate(["auth/login"])
        } 
      
      return false;
    }
    return true;
  }
  
}
