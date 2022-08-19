import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthguardService} from './authguard.service';
import {Router } from '@angular/router';  

@Injectable({
  providedIn: 'root'
})
export class AuthentificationGuard implements CanActivate { 
  constructor(private Authguardservice: AuthguardService, private router: Router) {}  
  canActivate(): boolean {  
      if (!this.Authguardservice.gettoken()) {  
          this.router.navigateByUrl("");   
      }  
      
      return this.Authguardservice.gettoken();  
  }   
}
