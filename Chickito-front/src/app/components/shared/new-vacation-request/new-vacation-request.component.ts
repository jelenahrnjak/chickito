import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { VacationService } from '../../../services/vacation.service';  
import { UserService } from '../../../services/user.service';  
import { ToastrService } from 'ngx-toastr';  
import User from '../../../model/user';

@Component({
  selector: 'app-new-vacation-request',
  templateUrl: './new-vacation-request.component.html',
  styleUrls: ['./new-vacation-request.component.scss']
})
export class NewVacationRequestComponent implements OnInit {


  form: FormGroup = this.formBuilder.group({
    startDate: ['', Validators.compose([Validators.required])],
    endDate: ['', Validators.compose([Validators.required])],
  }); 
  numberOfLeftDays : number = 0
  minDate: Date = new Date(new Date().getTime() + (86400000));
  minDateMax : Date = new Date(); 
  maxDate : Date = new Date(); 

  constructor( 
    private toastr: ToastrService, 
    private router: Router, 
    private formBuilder: FormBuilder,
    private userService : UserService,
    private vacationService : VacationService) {}

  ngOnInit(): void {
 
    
    this.userService.getCurrentUser().subscribe((data : User) => {
      this.numberOfLeftDays = data.availableVacationDays; 
    }); 
  } 

  checkDates(){

    if(this.form.get('startDate')?.value >= this.form.get('endDate')?.value){
      this.form.get('endDate')?.setValue("")

    }

    var currenttimestamp = (new Date(this.form.get('startDate')?.value)).getTime(); 
    var maxDays = currenttimestamp + ((this.numberOfLeftDays - 1)*86400000);

    this.maxDate = new Date(maxDays)
    this.minDateMax = new Date(currenttimestamp) 
  }

  onSubmit(){     

    const request = {
      'startDate' : this.form.get('startDate')?.value, 
      'endDate' : this.form.get('endDate')?.value, 
    } 

    this.vacationService.createVacationRequest(request)
    .subscribe(data => { 
      this.toastr.success('Uspešno kreiran zahtev za godišnji odmor!')  
      this.router.navigate(["my-vacation-requests"]); 
    },
      error => { 
        console.log('Creating vacation request error');  
        this.toastr.error(error['error'].message)
      });
  }
}
