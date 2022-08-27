import { Injectable } from '@angular/core';
import { ApiService } from '../services/core/api.service'; 
import { ConfigService } from '../services/core/config.service'; 
import { _throw } from 'rxjs/observable/throw'; 
import {  map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class WorkerOnMachineService {

  constructor(
    private apiService: ApiService, 
    private config: ConfigService,) 
  {}

  addWorkerToMachine(workerOnMachine : any) { 
    
    return this.apiService.post(this.config.workerOnMachines_url, JSON.stringify(workerOnMachine))
      .pipe(map(() => {
        console.log('Adding worker to machine success');
      }));
  }

  findAllWorkersByMachine(machineId : any) { 

    return this.apiService.get(this.config.workerOnMachines_url + `/findAllWorkersByMachine/${machineId}`)
    .pipe(map(workers => { 
      return workers;
    }));
  } 

  findAllMachinesByWorker(workerId : any) { 

    return this.apiService.get(this.config.workerOnMachines_url + `/findAllMachinesByWorker/${workerId}`)
    .pipe(map(machines => { 
      return machines;
    }));
  } 

  changeMainWorker(workerOnMachine : any) { 
    
    return this.apiService.post(this.config.workerOnMachines_url + `/changeMainWorker`, JSON.stringify(workerOnMachine))
      .pipe(map(() => {
        console.log('Changing main worker on machine success');
      }));
  }

  findAllWorkersNotOnMachine(machineId : any) { 

    return this.apiService.get(this.config.workerOnMachines_url + `/findAllWorkersNotOnMachine/${machineId}`)
    .pipe(map(users => { 
      return users;
    }));
  } 
 
  delete(workerId : any, machineId : any) { 

    return this.apiService.delete(this.config.workerOnMachines_url + `/${machineId}/${workerId}`)
      .pipe(map(() => {
        console.log('Deleting worker from machine success');
      }));
  }


}
