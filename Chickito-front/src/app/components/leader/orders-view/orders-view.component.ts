import { Component, OnInit } from '@angular/core';
import { OrderService } from '../../../services/order.service';
import Order from '../../../model/order';

@Component({
  selector: 'app-orders-view',
  templateUrl: './orders-view.component.html',
  styleUrls: ['./orders-view.component.scss']
})
export class OrdersViewComponent implements OnInit {

  allOrders : Order[] = [] 

  constructor(
    private orderService : OrderService,
  ) { }

  ngOnInit(): void {
  }

  getAllOrders(){
    
    this.orderService.findAllByAuthor().subscribe((data : Order[]) => {
      this.allOrders = data;
    }); 
  }

  showMachines(id : any){

  }
}
