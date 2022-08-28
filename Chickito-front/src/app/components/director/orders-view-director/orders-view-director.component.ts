import { Component, OnInit } from '@angular/core';
import { OrderService } from '../../../services/order.service';
import Order from '../../../model/order';
import OrderItem from '../../../model/order-item';
import { ToastrService } from 'ngx-toastr';  


@Component({
  selector: 'app-orders-view-director',
  templateUrl: './orders-view-director.component.html',
  styleUrls: ['./orders-view-director.component.scss']
})
export class OrdersViewDirectorComponent implements OnInit {


  allOrders : Order[] = [] 

  display = "none";
  displayDescription = "none"
  displayReply = "none"
  selectedDocumentation = "";
  selectedMachine = "";
  selectedOrder : any = "";
  orderItems : OrderItem[] = []
  termOrders = ""
  
  constructor(
    private orderService : OrderService,
    private toastr: ToastrService,  
  ) { }

  ngOnInit(): void {
    this.getAllOrders()
  }

  getAllOrders(){
    
    this.orderService.findAllByDirector().subscribe((data : Order[]) => {
      this.allOrders = data;
    }); 
  }

  showMachines(order : Order){ 
    this.display =' block'; 
    this.selectedOrder = order
    this.orderItems = []

    for(let i=0 ; i < this.allOrders.length; i++){  
      
      if(this.allOrders[i].id == order.id){
        
        this.orderItems = this.allOrders[i].orderItems
      }
    }
  }

  replyToOrder(order : Order){ 
    this.displayReply =' block'; 
    this.selectedOrder = order
    this.orderItems = []

    for(let i=0 ; i < this.allOrders.length; i++){  
      
      if(this.allOrders[i].id == order.id){
        
        this.orderItems = this.allOrders[i].orderItems
      }
    }
  }

  approveOrder(){ 
    
    this.orderService.approveOrder(this.selectedOrder.id)
    .subscribe(data => { 
      this.toastr.success('Uspešno odobrena narudžbenica!')  
      this.allOrders = []
      this.getAllOrders() 
    },
      error => { 
        console.log('Approving order error');  
        this.toastr.error(error['error'].message)
      });
  }

  
  declineOrder(){ 
    
    this.orderService.declineOrder(this.selectedOrder.id)
    .subscribe(data => { 
      this.toastr.success('Uspešno odbijena narudžbenica!')  
      this.allOrders = []
      this.getAllOrders() 
    },
      error => { 
        console.log('Declining order error');  
        this.toastr.error(error['error'].message)
      });
  }

    
  generateOrderPdf(id : any){
    
    this.orderService.generateOrderPdf(id).subscribe((data : string) => { 
      this.toastr.success('Narudžbenica broj #' + id + ' je poslata na vašu email adresu.')  
      this.allOrders = []
      this.getAllOrders() 
    },
      error => { 
        console.log('Exporting order error');  
        this.toastr.error(error['error'].message)
      }); 
  }

}
