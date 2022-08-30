import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OrderService, } from '../../../services/order.service';  
import { ToastrService } from 'ngx-toastr';  
import OrderItem from '../../../model/order-item'

@Component({
  selector: 'app-new-order',
  templateUrl: './new-order.component.html',
  styleUrls: ['./new-order.component.scss']
})
export class NewOrderComponent implements OnInit {

  form!: FormGroup;
  orderItems : OrderItem[] = []
  
  display = "none";
  selectedTechnicalTask : any = "";
  selectedMachine : any = ""

  constructor( 
    private toastr: ToastrService,  
    private router: Router, 
    private formBuilder: FormBuilder,
    private orderService: OrderService, ) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      name : ['', Validators.compose([Validators.required])],
      model: [''],
      price: ['', Validators.compose([Validators.required, Validators.pattern('^[1-9]+[0-9]*$')])],
      quantity: ['', Validators.compose([Validators.required, Validators.pattern('^[1-9]+[0-9]*$')])],
      technicalTask: [''],  
    }); 
  }

  onSubmit(){ 

    if(this.form.get('model')?.value == '' && this.form.get('technicalTask')?.value == ''){
      this.toastr.error('Unesite model ili tehnički zadatak!')  
      return
    }
    this.orderItems.push(this.form.value)
    this.form.reset()
  }

  removeItem(name : string, model : string){

    this.orderItems.forEach((element,index)=>{
      if(element.name == name && element.model == model) this.orderItems.splice(index,1);
   });

  }

  createOrder(){

    this.orderService.createOrder(this.orderItems)
    .subscribe(data => { 
      this.toastr.success('Nova narudžbenica uspešno kreirana!')  
      this.router.navigate(['leader/orders']);
    },
      error => { 
        console.log('Adding order error');  
        this.toastr.error(error['error'].message)
      });
  }

}
