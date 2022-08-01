import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { ApiService } from '../../core/services/api.service'; 
import { ConfigService } from '../../core/services/config.service'; 
import { _throw } from 'rxjs/observable/throw'; 
import {  map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BuildingService {


  constructor(
    private apiService: ApiService, 
    private config: ConfigService, 
  ) {
  }

  createBuilding(building : any) { 

    return this.apiService.post(this.config.building_url, JSON.stringify(building))
      .pipe(map(() => {
        console.log('Creating building success');
      }));
  }

  findAllByCompany(companyId : any) { 

    return this.apiService.get(this.config.building_url + '/findAllByCompany/' + companyId)
    .pipe(map(buildings => { 
      return buildings;
    }));
  }

  
  deleteBuilding(id : any) { 

    return this.apiService.delete(this.config.building_url + "/" + id)
      .pipe(map(() => {
        console.log('Deleting building success');
      }));
  }

  changeHeadOffice(id : any ){
    return this.apiService.put(this.config.building_url + "/changeHeadOffice/" + id)
    .pipe(map(() => {
      console.log('Updating building success');
    }));
  }
}
