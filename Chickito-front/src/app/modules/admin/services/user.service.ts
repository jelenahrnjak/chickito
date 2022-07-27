import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { ApiService } from '../../core/services/api.service'; 
import { ConfigService } from '../../core/services/config.service'; 
import { _throw } from 'rxjs/observable/throw'; 
import {  map } from 'rxjs/operators';

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
      'Content-Type': 'application/json'
    });

    return this.apiService.post(this.config.user_url, JSON.stringify(user), headers)
      .pipe(map(() => {
        console.log('Creating user success');
      }));
  }

}
