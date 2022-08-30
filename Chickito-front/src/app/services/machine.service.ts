import { Injectable } from '@angular/core';
import { ApiService } from '../services/core/api.service'; 
import { ConfigService } from '../services/core/config.service'; 
import { _throw } from 'rxjs/observable/throw'; 
import {  map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MachineService {

  constructor(
    private apiService: ApiService, 
    private config: ConfigService,
  ) {}

  findAllByLeader() { 

    return this.apiService.get(this.config.machine_url + `/findAllByLeader`)
    .pipe(map(machines => { 
      return machines;
    }));
  } 

  findAllByDirector() { 

    return this.apiService.get(this.config.machine_url + `/findAllByDirector`)
    .pipe(map(machines => { 
      return machines;
    }));
  } 

  addDocumentation(machineId: any ,documentation : any) {  

    return this.apiService.post(this.config.machine_url  + `/addDocumentation/${machineId}`, JSON.stringify(documentation))
      .pipe(map(() => {
        console.log('Changing documentation success');
      }));
  }

  editMachine(model : string, quantity : number, id : any) {  

    const body= {
      "model" : model,
      "quantity" : quantity
    }

    return this.apiService.post(this.config.machine_url  + `/${id}`, JSON.stringify(body))
      .pipe(map(() => {
        console.log('Changing documentation success');
      }));
  }
  
}
