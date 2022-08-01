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

  getAll() { 

    return this.apiService.get(this.config.company_url)
    .pipe(map(companies => { 
      return companies;
    }));
  }

  deleteCompany(id : any) { 

    return this.apiService.delete(this.config.company_url + "/" + id)
      .pipe(map(() => {
        console.log('Deleting company success');
      }));
  }
}
