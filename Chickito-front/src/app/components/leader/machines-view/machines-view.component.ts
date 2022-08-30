import { Component, OnInit } from '@angular/core';
import Machine from '../../../model/machine'
import { MachineService } from '../../../services/machine.service';
import { WorkerOnMachineService } from '../../../services/worker-on-machine.service';
import User from '../../../model/user'
import { ToastrService } from 'ngx-toastr';   
import Documentation from '../../../model/documentation';
import SparePart from '../../../model/spare-part';

@Component({
  selector: 'app-machines-view',
  templateUrl: './machines-view.component.html',
  styleUrls: ['./machines-view.component.scss']
})
export class MachinesViewComponent implements OnInit {


  newDocumentation : Documentation = {
    workInstructions : '',
    washingInstructions : '',
    maintenanceInstructions : '',
    spareParts : []};

  newSparePart : SparePart = {
    name : '',
    stockNumber : '',
    quantity : 0};

  allMachines : Machine[] = []
  displayDescription = 'none'
  displayEdit = 'none'
  displayNewPart = 'none'
  selectedMachine : any = ''
  selectedDocumentation: any = ''
  showWorkersForMachine = false;
  allWorkers : User[] = []
  displayAddingWorker = 'none'
  workersForAdding : User[] = []
  selectedNewWorker = 0
  isNewWorkerMain = false;

  termMachine = ""
  termUser = ""

  constructor(
    private machineService : MachineService,
    private workerOnMachineService : WorkerOnMachineService,
    private toastr: ToastrService, 
  ) { }

  ngOnInit(): void {
    this.getAllMachines()
  }

  resetNewDocumenation(){
    
    this.newDocumentation = { 
      workInstructions : '',
      washingInstructions : '',
      maintenanceInstructions : '',
      spareParts : []
    }
  }  
  resetSparePart(){
    
    this.newSparePart = {
      name : '',
      stockNumber : '',
      quantity : 0};
  }

  getAllMachines(){
    this.allMachines = []
    this.machineService.findAllByLeader().subscribe((data : Machine[]) => {
      this.allMachines = data;
    }); 
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

  changeMainWorker(worker :any){

    var text = 'Da li ste sigurn da želite da uklonite radnika ' + worker.fullName + ' sa mesta poslovođe na mašini' 
    + this.selectedMachine.name + ' '  + this.selectedMachine.model + '?';

    if(!worker.mainWorker){
     text = 'Da li ste sigurn da želite da postavite radnika ' + worker.fullName + ' na mesto poslovođe na mašini' 
      + this.selectedMachine.name + ' '  + this.selectedMachine.model + '?';
    }

    if (confirm(text)) { //TODO modal 

      var body = { 
       "machineId" : this.selectedMachine.id,
       "userId" : worker.id
      }

      this.workerOnMachineService.changeMainWorker(body).subscribe(data => {  
        this.showWorkers(this.selectedMachine)
        this.toastr.success('Uspešno promenjene poslovođe!')  
      }); 
    }

  }

  getWorkersAvailableForMachine(){
    this.workerOnMachineService.findAllWorkersNotOnMachine(this.selectedMachine.id).subscribe((data : User[]) => {
      console.dir(data)
      this.workersForAdding = []
      this.workersForAdding = data;
      this.displayAddingWorker = 'block'
    }); 
  }

  deleteWorkerFromMachine(worker :any ){
    if (confirm('Da li ste sigurn da želite da uklonite radnika ' + worker.fullName + ' sa mašine ' + this.selectedMachine.name + ' '  + this.selectedMachine.model + '?')) { //TODO modal 
      this.workerOnMachineService.delete(worker.id, this.selectedMachine.id).subscribe(data => { 

        this.showWorkers(this.selectedMachine)
        this.toastr.success('Radnik uspešno obrisan sa mašine!')  
      }); 
    }
  }
  
  addNewUser(){

    var body = { 
      "machineId" : this.selectedMachine.id,
      "userId" : this.selectedNewWorker,
      "mainWorker" : this.isNewWorkerMain
     }
     console.dir(body)
     

    this.workerOnMachineService.addWorkerToMachine(body)
    .subscribe(data => { 
      this.toastr.success('Uspešno dodat radnik!')  
      this.showWorkers(this.selectedMachine)
      this.selectedNewWorker = 0
      this.isNewWorkerMain = false
    },
      error => { 
        console.log('Adding worker on machine error');  
        this.toastr.error(error['error'].message)
      });
  }

  addSparePart(){
    this.newDocumentation.spareParts.push(this.newSparePart)
    this.resetSparePart()
  }

  addDocumentation(){ 

    this.machineService.addDocumentation(this.selectedMachine.id, this.newDocumentation)
    .subscribe(data => { 
      this.toastr.success('Uspešno izmenjena dokumentacija!')  
      this.getAllMachines();
    },
      error => { 
        console.log('Changing documentation error');  
        this.toastr.error(error['error'].message)
      });
  }

  deleteSpare(spare){

    this.newDocumentation.spareParts.forEach((element, index) => {
      if(element.name == spare.name && element.stockNumber == spare.stockNumber && element.quantity == spare.quantity){
        this.newDocumentation.spareParts.splice(index,1)
      }
      
    });
  }
 
}
