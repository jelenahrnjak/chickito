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
    const token = sessionStorage.getItem('jwt');
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
      
      return false;
    }
    return true;
  }
  
}
