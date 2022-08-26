import { Injectable } from '@angular/core';
import { ApiService } from '../services/core/api.service'; 
import { ConfigService } from '../services/core/config.service'; 
import { _throw } from 'rxjs/observable/throw'; 
import {  map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(
    private apiService: ApiService, 
    private config: ConfigService,) {}

    createOrder(orderItems : any) { 
      const body = {
        'orderItems' : orderItems
      }
      return this.apiService.post(this.config.order_url, JSON.stringify(body))
        .pipe(map(() => {
          console.log('Creating order success');
        }));
    }

    findAllByAuthor() { 

      return this.apiService.get(this.config.order_url + `/findAllByAuthor`)
      .pipe(map(orders => { 
        return orders;
      }));
    } 
  
    findAllByDirector() { 

      return this.apiService.get(this.config.order_url + `/findAllByDirector`)
      .pipe(map(orders => { 
        return orders;
      }));
    } 

    approveOrder(id : any) { 
      
      return this.apiService.post(this.config.order_url + `/${id}/approve`, {})
        .pipe(map(() => {
          console.log('Approving order success');
        }));
    }

    declineOrder(id : number) { 
      
      return this.apiService.post(this.config.order_url + `/${id}/decline` , {})
        .pipe(map(() => {
          console.log('Approving order success');
        }));
    }

    generateOrderPdf(id : any){

      return this.apiService.get(this.config.order_url + `/generateOrderPdf/${id}`)
      .pipe(map(path => { 
        return path;
      }));
    }
}
