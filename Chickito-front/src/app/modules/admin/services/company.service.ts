import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { ApiService } from '../../core/services/api.service'; 
import { ConfigService } from '../../core/services/config.service'; 
import { _throw } from 'rxjs/observable/throw'; 
import {  map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {


  constructor(
    private apiService: ApiService, 
    private config: ConfigService, 
  ) {
  }

  createCompany(company : any) { 

    return this.apiService.post(this.config.company_url, JSON.stringify(company))
      .pipe(map(() => {
        console.log('Creating company success');
      }));
  }
}
