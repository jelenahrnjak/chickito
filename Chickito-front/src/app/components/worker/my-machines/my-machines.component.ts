import { Component, OnInit } from '@angular/core';
import Machine from '../../../model/machine' 
import { WorkerOnMachineService } from '../../../services/worker-on-machine.service'; 
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

  termMachine = ""

  constructor(
    private workerOnMachineService : WorkerOnMachineService,
    private toastr: ToastrService, 
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
  

}
