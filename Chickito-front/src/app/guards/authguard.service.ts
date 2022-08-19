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
    return sessionStorage.getItem("jwt") != undefined && sessionStorage.getItem("jwt") != null;
  }

  roleIsPresent(){
    return sessionStorage.getItem("role")!= undefined && sessionStorage.getItem("role") != null;
  }

  tokenIsExpired(){
    if (sessionStorage.getItem("jwt") != undefined && sessionStorage.getItem("jwt") != null)  {
      let locStorageToken = sessionStorage.getItem("jwt") 
      if (!locStorageToken){
        return true;
      } 
      return this.jwtHelper.isTokenExpired(locStorageToken);
    }
    return true;
  }
}
