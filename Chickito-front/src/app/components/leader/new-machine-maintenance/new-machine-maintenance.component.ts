import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';  
import { Router } from '@angular/router';
import Machine from '../../../model/machine'
import { MachineService } from '../../../services/machine.service';
import MachineMaintenance from '../../../model/machine-maintenance'
import MachineMaintenanceItem from '../../../model/machine-maintenance-item'
import { MachineMaintenanceService } from '../../../services/machine-maintenance.service';

@Component({
  selector: 'app-new-machine-maintenance',
  templateUrl: './new-machine-maintenance.component.html',
  styleUrls: ['./new-machine-maintenance.component.scss']
})
export class NewMachineMaintenanceComponent implements OnInit {

  allMachines : Machine[] = []
  items : MachineMaintenanceItem[] = []
  form!: FormGroup;
  formDates!: FormGroup;
  display = "none";
  selectedItem : any = ''
  selectedMachine : any = ''
  years : number[] = []

  
  minDate: Date = new Date();
  minDateMax : Date = new Date(); 

  constructor(
    private toastr: ToastrService,  
    private router: Router, 
    private formBuilder: FormBuilder,
    private machineService : MachineService,
    private machineMaintenanceService : MachineMaintenanceService
  ) { }


  ngOnInit(): void {
    this.getAllMachines()

    for(var i=0; i<5 ; i++){
      this.years.push((new Date()).getFullYear() + i)
    }

    this.form = this.formBuilder.group({
      machine : ['', Validators.compose([Validators.required])],
      plan: ['', Validators.compose([Validators.required])], 
    }); 

    this.formDates = this.formBuilder.group({ 
      year : ['',Validators.compose([Validators.required])],
    }); 
  }

  getAllMachines(){
    this.allMachines = []
    this.machineService.findAllByLeader().subscribe((data : Machine[]) => {
      this.allMachines = data;
    }); 
  }

  onSubmit(){  
    var newItem : MachineMaintenanceItem = {} as MachineMaintenanceItem
    newItem.plan = this.form.get('plan')?.value

    this.allMachines.forEach(element => {
      if(element.id == this.form.get('machine')?.value){
        newItem.machine = element
        this.removeMachine(element.id)
      }
    });

    this.items.push(newItem)
    this.form.get('plan')?.reset()
    this.form.get('machine')?.setValue('')
  }

  removeMachine(machineId){

    this.allMachines.forEach((element,index)=>{
      if(element.id == machineId) this.allMachines.splice(index,1);
   });


  }

  removeItem(item){

    this.items.forEach((element,index)=>{
      if(element.machine == item.machine && element.plan == item.plan) {
        this.items.splice(index,1);
        this.allMachines.push(element.machine)
      }
   });


  }

  createMachineMaintenance(){

    var body ={
      "items" : this.items,
      "year" : this.formDates.get('year')?.value, 
    }

    this.machineMaintenanceService.createMachineMaintenance(body)
    .subscribe(data => { 
      this.toastr.success('Novi predlog održavanja uspešno kreirana!')  
      this.router.navigate(['leader/machine-maintenances']);
    },
      error => { 
        console.log('Adding machine maintenance error');  
        this.toastr.error(error['error'].message)
      });
  }

  checkDates(){

    if(this.formDates.get('startDate')?.value >= this.formDates.get('endDate')?.value){
      this.formDates.get('endDate')?.setValue("")

    }

    var currenttimestamp = (new Date(this.formDates.get('startDate')?.value)).getTime(); 
    var onedayaftertimestamp=currenttimestamp+(86400000);//1 day=86400000 ms; 

    this.minDateMax = new Date(onedayaftertimestamp) 
  }

}
