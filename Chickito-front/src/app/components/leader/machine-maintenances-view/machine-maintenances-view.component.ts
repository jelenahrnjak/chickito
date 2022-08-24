import { Component, OnInit } from '@angular/core';
import { MachineMaintenanceService } from '../../../services/machine-maintenance.service';
import MachineMaintenance from '../../../model/machine-maintenance';
import MachineMaintenanceItem from '../../../model/machine-maintenance-item';

@Component({
  selector: 'app-machine-maintenances-view',
  templateUrl: './machine-maintenances-view.component.html',
  styleUrls: ['./machine-maintenances-view.component.scss']
})
export class MachineMaintenancesViewComponent implements OnInit {

  
  allMaintenances : MachineMaintenance[] = [] 

  display = "none";
  displayPlan = "none"
  
  selectedItem :any = "";
  selectedMachine : any = ''
  selectedMaintenance : any = "";
  items : MachineMaintenanceItem[] = []

  constructor(
    private mahcineMaintenanceService : MachineMaintenanceService
  ) { }


  ngOnInit(): void {
    this.getAllMaintenances()
  }

  getAllMaintenances(){
    
    this.mahcineMaintenanceService.findAllByAuthor().subscribe((data : MachineMaintenance[]) => {
      this.allMaintenances = data;
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

}
