import { Injectable } from '@angular/core';
import { ApiService } from '../services/core/api.service'; 
import { ConfigService } from '../services/core/config.service'; 
import { _throw } from 'rxjs/observable/throw'; 
import {  map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MachineMaintenanceService {

  constructor(
    private apiService: ApiService, 
    private config: ConfigService,) {}

    createMachineMaintenance(body : any) { 
      
      return this.apiService.post(this.config.machineMaintenance_url, JSON.stringify(body))
        .pipe(map(() => {
          console.log('Creating machine maintenance success');
        }));
    }

    findAllByAuthor() { 

      return this.apiService.get(this.config.machineMaintenance_url + `/findAllByAuthor`)
      .pipe(map(maintenances => { 
        return maintenances;
      }));
    } 
  
    findAllByDirector() { 

      return this.apiService.get(this.config.machineMaintenance_url + `/findAllByDirector`)
      .pipe(map(maintenances => { 
        return maintenances;
      }));
    } 

    generateMaintenancePdf(year : number){

      return this.apiService.get(this.config.machineMaintenance_url + `/generateMaintenancePdf/${year}`)
      .pipe(map(path => { 
        return path;
      }));
    }
}
