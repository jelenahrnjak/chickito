import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { ApiService } from './api.service'; 
import { ConfigService } from './config.service';
import { catchError, map } from 'rxjs/operators';
import { Router } from '@angular/router';
import { of } from 'rxjs/internal/observable/of';
import { Observable } from 'rxjs';
import { _throw } from 'rxjs/observable/throw';
import jwt_decode from "jwt-decode";

@Injectable()
export class AuthService {

  constructor(
    private apiService: ApiService, 
    private config: ConfigService,
    private router: Router
  ) {
  }

  private access_token = null;

  login(user : any)  {
    const loginHeaders = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    });
    
    const body = {
      'username': user.username,
      'password': user.password
    };
    return this.apiService.post(this.config.login_url, JSON.stringify(body), loginHeaders)
      .pipe(map((res) => {
        console.log('Login success'); 
        this.access_token = res.accessToken;
        let decoded: any = jwt_decode(res.accessToken) 
        sessionStorage.setItem("user", decoded.sub)
        sessionStorage.setItem("role", decoded.role) 
        sessionStorage.setItem("jwt", res.accessToken);
        sessionStorage.setItem("refreshToken", res.expiresIn);  
      }));
  } 

  logout() { 
    this.access_token = null; ;
    sessionStorage.clear(); 
    this.router.navigate(['/login']);
  }

  tokenIsPresent() {
    return this.access_token != undefined && this.access_token != null;
  }

  getToken() {
    return this.access_token;
  }

}
