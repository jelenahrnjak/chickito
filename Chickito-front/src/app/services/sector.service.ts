import { Injectable } from '@angular/core';
import { ApiService } from '../services/core/api.service'; 
import { ConfigService } from '../services/core/config.service'; 
import { _throw } from 'rxjs/observable/throw'; 
import {  map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SectorService {

  constructor(
    private apiService: ApiService, 
    private config: ConfigService,) {}

    findByCompanyAndType(companyId : any, sectorType: any) { 

      return this.apiService.get(this.config.sector_url + `/findByCompanyAndType/${companyId}/${sectorType}` )
      .pipe(map(sector => { 
        return sector;
      }));
    }
}
