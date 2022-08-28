import { Component, OnInit } from '@angular/core';
import Machine from '../../../model/machine'
import { MachineService } from '../../../services/machine.service';
import User from '../../../model/user'
import { WorkerOnMachineService } from '../../../services/worker-on-machine.service';

@Component({
  selector: 'app-machines-view-director',
  templateUrl: './machines-view-director.component.html',
  styleUrls: ['./machines-view-director.component.scss']
})
export class MachinesViewDirectorComponent implements OnInit {

  allMachines : Machine[] = []
  preradaMachines : Machine[] = []
  klanicaMachines : Machine[] = []
  kontrolaMachines : Machine[] = []
  odrzavanjeMachines : Machine[] = []
  displayDescription = 'none'
  selectedMachine : any = ''
  sector = -1;
  showWorkersForMachine = false;
  allWorkers : User[] = []
  termUser = ""
  termMachine = ""

  showPrerada = false;
  showKlanica = false;
  showKontrola = false;
  showOdrzavanje = false;
  constructor(
    private machineService : MachineService,
    private workerOnMachineService : WorkerOnMachineService,
  ) { }

  ngOnInit(): void {
    this.getAllMachines()
  }

  getAllMachines(){
    this.allMachines = []
    this.machineService.findAllByDirector().subscribe((data : Machine[]) => {
      this.allMachines = data;

      this.allMachines.forEach(element => {
        if(element.sector == 'PRERADA'){
          this.preradaMachines.push(element)
        }else if(element.sector == 'KLANICA_I_PAKERAJ'){
          this.klanicaMachines.push(element)
        }else if(element.sector == 'KONTROLA_KVALITETA'){
          this.kontrolaMachines.push(element)
        }else{
          this.odrzavanjeMachines.push(element)
        }
      });
    }); 
  }

  changedSector(){
    this.allMachines = [] 
    this.showWorkersForMachine = false
    this.selectedMachine = ''
    
    if(this.sector == 0){ 
      this.allMachines = this.odrzavanjeMachines
    }else if(this.sector == 1){
      this.allMachines = this.klanicaMachines
    }else if(this.sector == 2){
      this.allMachines = this.preradaMachines
    } else if(this.sector == 3){
      this.allMachines = this.kontrolaMachines
    }
  }

  showWorkers(machine : Machine){ 
    this.showWorkersForMachine = true
    this.selectedMachine = machine;

    this.workerOnMachineService.findAllWorkersByMachine(machine.id).subscribe((data : User[]) => {
      console.dir(data)
      this.allWorkers = []
      this.allWorkers = data.sort((a, b) => Number(b.mainWorker) - Number(a.mainWorker));
    });  
  }

}
