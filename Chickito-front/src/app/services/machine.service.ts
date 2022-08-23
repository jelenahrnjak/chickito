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
  
}
