import { Injectable } from '@angular/core';
import { ApiService } from '../services/core/api.service'; 
import { ConfigService } from '../services/core/config.service'; 
import { _throw } from 'rxjs/observable/throw'; 
import {  map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class VacationService {

  constructor(
    private apiService: ApiService, 
    private config: ConfigService, 
  ) {
  }
  
  createVacationRequest(body : any) { 

    return this.apiService.post(this.config.vacationRequests_url, JSON.stringify(body))
      .pipe(map(() => {
        console.log('Creating vacation request success');
      }));
  }

  approveVacationRequest(id : any) { 
      
    return this.apiService.post(this.config.vacationRequests_url + `/${id}/approve`, {})
      .pipe(map(() => {
        console.log('Approving order success');
      }));
  }

  rejectVacationRequest(id : number, reason : string) { 
    
    var body={
      "reasonForRejection" : reason
    }
    
    return this.apiService.post(this.config.vacationRequests_url + `/${id}/reject` , JSON.stringify(body))
      .pipe(map(() => {
        console.log('Rejectiing vacation success');
      }));
  }

  findAllByDirector() { 

    return this.apiService.get(this.config.vacationRequests_url )
    .pipe(map(requests => { 
      return requests;
    }));
  } 

  findAllByUser() { 

    return this.apiService.get(this.config.vacationRequests_url + `/findAllByUser`)
    .pipe(map(requests => { 
      return requests;
    }));
  } 

  

  delete(id : any) { 

    return this.apiService.delete(this.config.vacationRequests_url + "/" + id)
      .pipe(map(() => {
        console.log('Deleting request success');
      }));
  }


}
