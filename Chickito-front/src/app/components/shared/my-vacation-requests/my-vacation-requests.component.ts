import { Component, OnInit } from '@angular/core';
import VacationRequest from '../../../model/vacation-request' 
import { VacationService } from '../../../services/vacation.service';
import { ToastrService } from 'ngx-toastr';  

@Component({
  selector: 'app-my-vacation-requests',
  templateUrl: './my-vacation-requests.component.html',
  styleUrls: ['./my-vacation-requests.component.scss']
})
export class MyVacationRequestsComponent implements OnInit {


  term = ''
  display = 'none'
  selectedRequest : any = ''

  allRequests : VacationRequest[] = []  
  
  constructor(
    private toastr: ToastrService,  
    private vacationService : VacationService, 
    ) { }

  ngOnInit(): void {
    this.getRequests() 
  } 
 

  getRequests(){
    this.vacationService.findAllByUser().subscribe((data : VacationRequest[]) => {
      this.allRequests = data.sort((a, b) => a.startDate.localeCompare(b.startDate)); 
    }); 

  } 

  delete(request){ 
    if (confirm('Da li ste sigurni da želite da obrišete zahtev za godišnji odmor od ' + request.startDate + " do " + request.endDate + '?')) { //TODO modal 
      this.vacationService.delete(request.id).subscribe(data => { 
        this.getRequests() 
        this.toastr.success('Zahtev uspešno obrisan!')  
      }); 
    }
  }

  displayModal(item){
    this.display = 'block'
    this.selectedRequest = item;
  }

}
