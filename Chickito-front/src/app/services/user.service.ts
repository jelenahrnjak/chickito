import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http'; 
import { _throw } from 'rxjs/observable/throw'; 
import {  map } from 'rxjs/operators';
import { ApiService } from './core/api.service';
import { ConfigService } from './core/config.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
 
  constructor(
    private apiService: ApiService, 
    private config: ConfigService, 
  ) {
  }

  createUser(user : any) {
    const headers = new HttpHeaders({
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      // 'Authorization': 'Bearer ' + sessionStorage.getItem('jwt')
    });

    return this.apiService.post(this.config.user_url, JSON.stringify(user), headers)
      .pipe(map(() => {
        console.log('Creating user success');
      }));
  }

}
