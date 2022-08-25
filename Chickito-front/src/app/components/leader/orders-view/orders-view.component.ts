import { Component, OnInit } from '@angular/core';
import { OrderService } from '../../../services/order.service';
import Order from '../../../model/order';
import OrderItem from '../../../model/order-item';
import { ToastrService } from 'ngx-toastr';  

@Component({
  selector: 'app-orders-view',
  templateUrl: './orders-view.component.html',
  styleUrls: ['./orders-view.component.scss']
})
export class OrdersViewComponent implements OnInit {

  allOrders : Order[] = [] 

  display = "none";
  displayDescription = "none"
  selectedDocumentation = "";
  selectedMachine = "";
  selectedOrder : any = "";
  orderItems : OrderItem[] = []
  constructor(
    private orderService : OrderService,
    private toastr: ToastrService,  
  ) { }

  ngOnInit(): void {
    this.getAllOrders()
  }

  getAllOrders(){
    
    this.orderService.findAllByAuthor().subscribe((data : Order[]) => {
      this.allOrders = data;
    }); 
  }

  
  exportOrderReport(id : any){
    
    this.orderService.exportOrderReport(id).subscribe((data : string) => { 
      this.toastr.success('Narudžbenica broj #' + id + ' je poslata na vašu email adresu.')  
      this.allOrders = []
      this.getAllOrders() 
    },
      error => { 
        console.log('Exporting order error');  
        this.toastr.error(error['error'].message)
      }); 
  }


  showMachines(id : any){ 
    this.display =' block'; 
    this.selectedOrder = id
    this.orderItems = []

    for(let i=0 ; i < this.allOrders.length; i++){  
      
      if(this.allOrders[i].id == id){
        
        this.orderItems = this.allOrders[i].orderItems
      }
    }
  }
}
