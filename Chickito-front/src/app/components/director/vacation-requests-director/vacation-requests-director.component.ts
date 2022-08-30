import { Component, OnInit } from '@angular/core';
import VacationRequest from '../../../model/vacation-request' 
import { VacationService } from '../../../services/vacation.service';
import { ToastrService } from 'ngx-toastr';  

@Component({
  selector: 'app-vacation-requests-director',
  templateUrl: './vacation-requests-director.component.html',
  styleUrls: ['./vacation-requests-director.component.scss']
})
export class VacationRequestsDirectorComponent implements OnInit {

  term = ''
  display = 'none'
  selectedRequest : any = ''
  selectedUser : any = ''
  reason = ''

  allRequests : VacationRequest[] = []  
  
  constructor(
    private toastr: ToastrService,  
    private vacationService : VacationService, 
    ) { }

  ngOnInit(): void {
    this.getRequests() 
  } 
 

  getRequests(){
    this.vacationService.findAllByDirector().subscribe((data : VacationRequest[]) => {
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

  displayModal(item, user){
    this.display = 'block'
    this.selectedRequest = item;
    this.selectedUser = user;
  }

  accept(item){ 
    
    if (confirm('Da li ste sigurni da želite da odobrite zahtev?')) { //TODO modal 
  
      this.vacationService.approveVacationRequest(item.id)
      .subscribe(data => { 
        this.toastr.success('Uspešno prihvaćen zahtev!')  
        this.allRequests = []
        this.getRequests()
      },
        error => { 
          console.log('Approving request error');  
          this.toastr.error(error['error'].message)
        });
    }
  }

  decline(){
    
     
    this.vacationService.rejectVacationRequest(this.selectedRequest.id, this.reason)
    .subscribe(data => { 
      this.toastr.success('Uspešno odbijen zahtev!')
      this.display = 'none'  
      this.allRequests = []
      this.getRequests()
    },
      error => { 
        console.log('Declinining request error');  
        this.toastr.error(error['error'].message)
      });  
  }

}
