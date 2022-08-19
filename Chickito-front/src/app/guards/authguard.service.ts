import { Injectable } from '@angular/core';
import {JwtHelperService} from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthguardService {

  constructor(private jwtHelper: JwtHelperService) { }

  gettoken(){  
    if (this.tokenIsPresent() && this.roleIsPresent() && !this.tokenIsExpired()){
      return true;
    }
    return false;
  }
 
  tokenIsPresent() {
    return sessionStorage.getItem("user") != undefined && sessionStorage.getItem("user") != null;
  }

  roleIsPresent(){
    return sessionStorage.getItem("role")!= undefined && sessionStorage.getItem("role") != null;
  }

  tokenIsExpired(){
    if (sessionStorage.getItem("user") != undefined && sessionStorage.getItem("user") != null)  {
      let locStorageToken = sessionStorage.getItem("user") 
      if (!locStorageToken){
        return true;
      } 
      return this.jwtHelper.isTokenExpired(locStorageToken);
    }
    return true;
  }
}
