import { Component, OnInit } from '@angular/core';
import Machine from '../../../model/machine'
import { MachineService } from '../../../services/machine.service';

@Component({
  selector: 'app-machines-view',
  templateUrl: './machines-view.component.html',
  styleUrls: ['./machines-view.component.scss']
})
export class MachinesViewComponent implements OnInit {

  allMachines : Machine[] = []
  displayDescription = 'none'
  selectedMachine : any = ''

  constructor(
    private machineService : MachineService
  ) { }

  ngOnInit(): void {
    this.getAllMachines()
  }

  getAllMachines(){
    this.allMachines = []
    this.machineService.findAllByLeader().subscribe((data : Machine[]) => {
      this.allMachines = data;
    }); 
  }

  showWorkers(machine : Machine){
    this.selectedMachine = machine;
  }
}
