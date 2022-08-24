import { Component, OnInit } from '@angular/core';
import Machine from '../../../model/machine'
import { MachineService } from '../../../services/machine.service';

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

  showPrerada = false;
  showKlanica = false;
  showKontrola = false;
  showOdrzavanje = false;
  constructor(
    private machineService : MachineService
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

  showWorkers(machine : Machine){
    this.selectedMachine = machine;
  }

}
