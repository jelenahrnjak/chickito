import { Component, OnInit } from '@angular/core';
import Machine from '../../../model/machine' 
import MachineMaintenanceItem from '../../../model/machine-maintenance-item' 
import MachineMaintenance from '../../../model/machine-maintenance' 
import { WorkerOnMachineService } from '../../../services/worker-on-machine.service'; 
import { MachineMaintenanceService } from '../../../services/machine-maintenance.service'; 
import { ToastrService } from 'ngx-toastr';   

@Component({
  selector: 'app-my-machines',
  templateUrl: './my-machines.component.html',
  styleUrls: ['./my-machines.component.scss']
})
export class MyMachinesComponent implements OnInit {


  allMachines : Machine[] = []
  displayDescription = 'none'
  selectedMachine : any = ''
  display = 'none'
  currYear = new Date().getFullYear();
  items : MachineMaintenanceItem[] = []
  selectedDocumentation : any = ''

  termMachine = ""

  constructor(
    private workerOnMachineService : WorkerOnMachineService,
    private toastr: ToastrService, 
    private machineMaintenanceService : MachineMaintenanceService
  ) { }

  ngOnInit(): void {
    this.getAllMachines()
  }

  getAllMachines(){
    this.allMachines = []
    this.workerOnMachineService.findAllMachinesByWorker(sessionStorage.getItem('user')).subscribe((data : Machine[]) => {
      this.allMachines = data;
    }); 
  }
  
  displayPlan(machine){
    this.machineMaintenanceService.getCurrentPlan(machine.id).subscribe((data : MachineMaintenanceItem[]) => {
      this.items = data;
      this.selectedMachine = machine; 
      this.display = 'block'
    }); 
  }

}
