import { Component, OnInit } from '@angular/core';
import { MachineMaintenanceService } from '../../../services/machine-maintenance.service';
import MachineMaintenance from '../../../model/machine-maintenance';
import MachineMaintenanceItem from '../../../model/machine-maintenance-item';
import { ToastrService } from 'ngx-toastr';  

@Component({
  selector: 'app-machine-maintenances-view',
  templateUrl: './machine-maintenances-view.component.html',
  styleUrls: ['./machine-maintenances-view.component.scss']
})
export class MachineMaintenancesViewComponent implements OnInit {

  
  allMaintenances : MachineMaintenance[] = [] 
  years : number[] = []
  displayYear = 'none'
  selectedYear = 0;

  display = "none";
  displayPlan = "none"
  
  selectedItem :any = "";
  selectedMachine : any = ''
  selectedMaintenance : any = "";
  items : MachineMaintenanceItem[] = []

  constructor(
    private mahcineMaintenanceService : MachineMaintenanceService,
    private toastr: ToastrService,  
  ) { }


  ngOnInit(): void {
    this.getAllMaintenances() 
  }

  getAllMaintenances(){
    
    this.mahcineMaintenanceService.findAllByAuthor().subscribe((data : MachineMaintenance[]) => {
      this.allMaintenances = data;
      data.forEach(element => {
        if(!this.years.includes(element.year)){
          this.years.push(element.year)
        }
      }); 
    }); 
  }
 

  showItems(maintenance : MachineMaintenance){ 
    this.display =' block'; 
    this.selectedMaintenance = maintenance
    this.items = []
    this.items = maintenance.items
    console.log('tu')
    console.dir( maintenance)

    // for(let i=0 ; i < this.allMaintenances.length; i++){  
      
    //   if(this.allMaintenances[i].id == id){
        
    //     this.items = this.allMaintenances[i].machineMaintenanceItems
    //   }
    // }
  }

  generateMaintenancePdf(){ 
    
    this.mahcineMaintenanceService.generateMaintenancePdf(this.selectedYear).subscribe((data : string) => { 
      this.toastr.success('Plan održavanja za ' + this.selectedYear.toString() + '. godinu je poslat na vašu email adresu.')   
    },
      error => { 
        console.log('Exporting machine maintenance error');  
        this.toastr.error(error['error'].message)
      });  
  }

}
