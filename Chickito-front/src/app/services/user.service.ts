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

  findAllWorkersByLeader() { 

    return this.apiService.get(this.config.user_url + `/findAllWorkersByLeader`)
    .pipe(map(users => { 
      return users;
    }));
  } 

  getCurrentUser(){

    return this.apiService.get(this.config.user_url + `/getCurrentUser`)
    .pipe(map(user => { 
      return user;
    }));

  }

  findAllByDirector() { 

    return this.apiService.get(this.config.user_url + `/findAllByDirector`)
    .pipe(map(users => { 
      return users;
    }));
  } 
 
  findAllByCompany(companyId : any) { 

    return this.apiService.get(this.config.user_url + `/findAllByCompany/${companyId}`)
    .pipe(map(users => { 
      return users;
    }));
  }  

  deleteUser(id : any) { 

    return this.apiService.delete(this.config.user_url + "/" + id)
      .pipe(map(() => {
        console.log('Deleting user success');
      }));
  }

  

  changeVacationDaysPerYear(username : string, numberOfDays : number) {  

    return this.apiService.post(this.config.user_url  + `/changeVacationDays/${username}`, numberOfDays)
      .pipe(map(() => {
        console.log('Changing vacation days per year success');
      }));
  }

}
