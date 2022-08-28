import { Component, OnInit } from '@angular/core';
import { MachineMaintenanceService } from '../../../services/machine-maintenance.service';
import MachineMaintenance from '../../../model/machine-maintenance';
import MachineMaintenanceItem from '../../../model/machine-maintenance-item';
import { ToastrService } from 'ngx-toastr';  

@Component({
  selector: 'app-mahcine-maintenances-director',
  templateUrl: './mahcine-maintenances-director.component.html',
  styleUrls: ['./mahcine-maintenances-director.component.scss']
})
export class MahcineMaintenancesDirectorComponent implements OnInit {

  allMaintenances : MachineMaintenance[] = [] 
  years : number[] = []
  displayYear = 'none'
  selectedYear = 0;
  selectedSector = '';

  display = "none";
  displayPlan = "none"
  termMaintenances = ''
  
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
    
    this.mahcineMaintenanceService.findAllByDirector().subscribe((data : MachineMaintenance[]) => {
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
  }

  generateMaintenancePdf(){ 
    
    this.mahcineMaintenanceService.generateMaintenancePdfDirector(this.selectedYear, this.selectedSector).subscribe((data : string) => { 
      this.toastr.success('Plan održavanja za ' + this.selectedYear.toString() + '. godinu je poslat na vašu email adresu.')   
    },
      error => { 
        console.log('Exporting machine maintenance error');  
        this.toastr.error(error['error'].message)
      });  
  }

}
